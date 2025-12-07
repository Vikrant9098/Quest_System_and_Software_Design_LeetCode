class User(object):
    def __init__(self,id):
        self.posts = {}                 # Stores user's tweets as {tweetId: time}
        self.following = {}             # Stores users this user follows {userId: User}
        self.id = id                    # Save the user's ID

    def addPost(self,tweetId,time):
        self.posts[tweetId] = time      # Add a tweet with its timestamp

    def addFollower(self,userFollowee):
        self.following[userFollowee.id] = userFollowee   # Add the followee to following list

    def deleteFollower(self, userFollowee):
        if userFollowee.id in self.following:            # If already following
            del self.following[userFollowee.id]          # Remove the followee


class Twitter(object):
    def __init__(self):
        self.users = {}                 # Stores all users {userId: User}
        self.ticks = 0                  # Global time counter for tweets

    def postTweet(self, userId, tweetId):
        if userId not in self.users:    # Create user if not exists
            user = User(userId)
            self.users[userId] = user

        self.users[userId].addPost(tweetId,self.ticks)   # Add tweet with current time
        self.ticks+=1                   # Increase global time


    def getNewsFeed(self, userId):
        if userId not in self.users:    # If user doesn't exist return empty feed
            return []

        user = self.users[userId]       # Get the user object
        own_follower_posts = user.posts.copy()  # Copy user’s own posts

        for fol in user.following.values():     # Add posts from all followees
            own_follower_posts.update(fol.posts)

        own_follower_posts = sorted(           # Sort by time (latest first)
            own_follower_posts.items(),
            key=lambda x:x[1],
            reverse=True
        )

        return [k for k,v in own_follower_posts[:10]]    # Return top 10 tweetIds


    def follow(self, followerId, followeeId):
        if followerId != followeeId:     # User cannot follow themselves
            if followerId not in self.users:     # Create follower if missing
                user_follower = User(followerId)
                self.users[followerId] = user_follower

            if followeeId not in self.users:     # Create followee if missing
                user_followee = User(followeeId)
                self.users[followeeId] = user_followee

            self.users[followerId].addFollower(self.users[followeeId])  # Add follow link


    def unfollow(self, followerId, followeeId):
        if followerId not in self.users:          # Create follower if missing
            user_follower = User(followerId)
            self.users[followerId] = user_follower

        if followeeId not in self.users:          # Create followee if missing
            user_followee = User(followeeId)
            self.users[followeeId] = user_followee

        if followeeId in self.users[followerId].following:  # If actually following
            self.users[followerId].deleteFollower(self.users[followeeId])  # Remove followee

        print(self.users[followerId].following)   # Print remaining following list


# Your Twitter object will be instantiated and called as such:
# obj = Twitter()
# obj.postTweet(userId,tweetId)
# param_2 = obj.getNewsFeed(userId)
# obj.follow(followerId,followeeId)
# obj.unfollow(followerId,followeeId)

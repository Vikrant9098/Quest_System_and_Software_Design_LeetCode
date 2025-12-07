import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

class Twitter {

    private static int timeStamp = 0; // global time counter for ordering tweets
    private Map<Integer, Set<Integer>> followMap; // stores users each user follows
    private Map<Integer, List<int[]>> tweetMap;   // stores tweets per user as [time, tweetId]

    public Twitter() {
        followMap = new HashMap<>(); // initialize follow map
        tweetMap = new HashMap<>();  // initialize tweet map
    }

    // Post a new tweet by userId
    public void postTweet(int userId, int tweetId) {
        tweetMap.putIfAbsent(userId, new ArrayList<>()); // create list if not present
        tweetMap.get(userId).add(new int[]{timeStamp++, tweetId}); // store tweet with timestamp
    }

    // Get 10 most recent tweets in user's feed
    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]); // max-heap by time

        // Add user's own tweets
        if (tweetMap.containsKey(userId)) {
            for (int[] tweet : tweetMap.get(userId)) {
                pq.offer(tweet); // push to heap
            }
        }

        // Add tweets of followed users
        if (followMap.containsKey(userId)) {
            for (int followee : followMap.get(userId)) {
                if (tweetMap.containsKey(followee)) { // if followee has tweets
                    for (int[] tweet : tweetMap.get(followee)) {
                        pq.offer(tweet); // push to heap
                    }
                }
            }
        }

        // Collect up to 10 most recent tweets
        List<Integer> res = new ArrayList<>();
        int count = 0;
        while (!pq.isEmpty() && count < 10) {
            res.add(pq.poll()[1]); // get tweetId from heap
            count++;               // count tweets added
        }
        return res; // return final list
    }

    // Make follower follow followee
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) return; // cannot follow yourself
        followMap.putIfAbsent(followerId, new HashSet<>()); // create set if not exists
        followMap.get(followerId).add(followeeId); // add followee to follower's set
    }

    // Make follower unfollow followee
    public void unfollow(int followerId, int followeeId) {
        if (followMap.containsKey(followerId)) { // if follower exists
            followMap.get(followerId).remove(followeeId); // remove followee
        }
    }
}

/**
 * Example usage:
 * Twitter obj = new Twitter();
 * obj.postTweet(1, 5);
 * List<Integer> feed = obj.getNewsFeed(1);
 * obj.follow(1, 2);
 * obj.unfollow(1, 2);
 */

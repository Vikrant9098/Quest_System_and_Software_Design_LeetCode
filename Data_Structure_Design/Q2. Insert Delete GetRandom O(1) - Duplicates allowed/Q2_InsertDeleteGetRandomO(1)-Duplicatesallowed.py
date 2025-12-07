import random  # import random module for getRandom function

class RandomizedCollection(object):

    def __init__(self):
        self.nums = []  # list to store values
        self.idx = {}   # dictionary to store value -> set of indices

    def insert(self, val):
        """
        :type val: int
        :rtype: bool
        """
        res = val not in self.idx  # check if val is new
        if res:
            self.idx[val] = set()  # create a new set if val not seen
        self.idx[val].add(len(self.nums))  # store current index of val
        self.nums.append(val)  # add val to list
        return res  # return True if new, else False

    def remove(self, val):
        """
        :type val: int
        :rtype: bool
        """
        if val not in self.idx or not self.idx[val]:  # if val not found
            return False  # cannot remove
        remove_idx = self.idx[val].pop()  # get index to remove
        last = self.nums[-1]  # get last value in list
        self.nums[remove_idx] = last  # move last value to removed spot
        self.idx[last].add(remove_idx)  # update index of last value
        self.idx[last].discard(len(self.nums) - 1)  # remove old index of last value
        self.nums.pop()  # remove last element
        if not self.idx[val]:  # if val no longer in list
            del self.idx[val]  # delete val from map
        return True  # successfully removed

    def getRandom(self):
        """
        :rtype: int
        """
        return random.choice(self.nums)  # return random element from list

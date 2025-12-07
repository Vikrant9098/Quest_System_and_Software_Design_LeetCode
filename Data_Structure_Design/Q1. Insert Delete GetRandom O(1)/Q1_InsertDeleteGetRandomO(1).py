import random

class RandomizedSet(object):

    def __init__(self):
        # List to store the elements
        self.data = []
        # Dictionary to store element -> index mapping
        self.pos = {}

    def insert(self, val):
        """
        :type val: int
        :rtype: bool
        """
        if val in self.pos:
            return False  # Already exists
        # Add element at the end of list
        self.data.append(val)
        # Store its index in hashmap
        self.pos[val] = len(self.data) - 1
        return True

    def remove(self, val):
        """
        :type val: int
        :rtype: bool
        """
        if val not in self.pos:
            return False  # Doesn't exist

        # Get index of element to remove
        idx = self.pos[val]
        last = self.data[-1]  # Last element

        # Swap with last element
        self.data[idx] = last
        self.pos[last] = idx

        # Remove last element
        self.data.pop()
        del self.pos[val]

        return True

    def getRandom(self):
        """
        :rtype: int
        """
        return random.choice(self.data)

class RangeFreqQuery(object):                     # define the class

    def __init__(self, arr):
        """ :type arr: List[int] """
        
        self.map = {}                              # store value → list of positions

        for i, val in enumerate(arr):              # loop through array with index
            if val not in self.map:                # if value not seen before
                self.map[val] = []                 # create a new list
            self.map[val].append(i)                # add index of the value

    def query(self, left, right, value):
        """
        :type left: int
        :type right: int
        :type value: int
        :rtype: int
        """
        
        if value not in self.map:                  # if value not present in array
            return 0                               # return 0

        arr = self.map[value]                      # get list of indices for value

        l = self.lower_bound(arr, left);           # find first index >= left
        r = self.upper_bound(arr, right);          # find first index > right

        return r - l                               # count indices inside the range

    def lower_bound(self, arr, target):            # binary search: first >= target
        l, r = 0, len(arr)                         # search range
        while l < r:                               # loop until bounds meet
            mid = (l + r) // 2                     # middle index
            if arr[mid] >= target:                 # if mid value is big enough
                r = mid                            # move left
            else:
                l = mid + 1                        # move right
        return l                                   # return position

    def upper_bound(self, arr, target):            # binary search: first > target
        l, r = 0, len(arr)                         # search range
        while l < r:                               # loop until bounds meet
            mid = (l + r) // 2                     # middle index
            if arr[mid] > target:                  # if mid value is greater
                r = mid                            # move left
            else:
                l = mid + 1                        # move right
        return l                                   # return position

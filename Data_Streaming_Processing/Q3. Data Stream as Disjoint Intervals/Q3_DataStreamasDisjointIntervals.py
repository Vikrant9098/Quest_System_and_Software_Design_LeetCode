class SummaryRanges(object):

    def __init__(self):
        # Use a sorted set to store unique numbers
        self.nums = set()

    def addNum(self, value):
        """
        :type value: int
        :rtype: None
        """
        # Add the new number to the set
        self.nums.add(value)

    def getIntervals(self):
        """
        :rtype: List[List[int]]
        """
        # Sort all unique numbers
        sorted_nums = sorted(self.nums)
        res = []

        # Traverse through sorted numbers to form intervals
        for num in sorted_nums:
            # If res is empty or current number is not consecutive to the last interval
            if not res or res[-1][1] + 1 < num:
                res.append([num, num])  # start a new interval
            else:
                res[-1][1] = num  # extend the previous interval

        return res

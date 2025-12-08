class RangeModule(object):

    def __init__(self):
        self.intervals = []  # list to store tracked ranges as [start, end)

    def addRange(self, left, right):
        """
        :type left: int
        :type right: int
        :rtype: None
        """
        new_intervals = []   # to build the updated interval list
        placed = False       # tells if merged interval is already added

        for l, r in self.intervals:  # check each stored interval
            if r < left:
                # current interval lies completely before new range
                new_intervals.append([l, r])
            elif right < l:
                # new range lies before this interval
                if not placed:
                    new_intervals.append([left, right])  # add merged new range
                    placed = True
                new_intervals.append([l, r])  # add current interval
            else:
                # intervals overlap → merge them
                left = min(left, l)
                right = max(right, r)

        if not placed:
            # if merged interval not added yet, add at the end
            new_intervals.append([left, right])

        self.intervals = new_intervals  # update intervals list

    def queryRange(self, left, right):
        """
        :type left: int
        :type right: int
        :rtype: bool
        """
        for l, r in self.intervals:  # check each stored interval
            if l <= left and r >= right:
                # if interval fully covers [left, right)
                return True
        return False  # not fully covered

    def removeRange(self, left, right):
        """
        :type left: int
        :type right: int
        :rtype: None
        """
        new_intervals = []   # new interval list

        for l, r in self.intervals:  # check each stored interval
            if r <= left or l >= right:
                # interval is completely outside the removed range
                new_intervals.append([l, r])
            else:
                # overlapping part to remove
                if l < left:
                    new_intervals.append([l, left])  # keep left part
                if r > right:
                    new_intervals.append([right, r]) # keep right part

        self.intervals = new_intervals  # update intervals

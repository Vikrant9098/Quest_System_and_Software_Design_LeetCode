import heapq

class KthLargest(object):

    def __init__(self, k, nums):
        """
        :type k: int
        :type nums: List[int]
        """
        self.k = k                      # store k
        self.min_heap = []              # create empty min-heap
        
        for n in nums:                  # add all initial numbers
            heapq.heappush(self.min_heap, n)   # push into heap
            if len(self.min_heap) > k:         # if heap too big
                heapq.heappop(self.min_heap)   # remove smallest

    def add(self, val):
        """
        :type val: int
        :rtype: int
        """
        heapq.heappush(self.min_heap, val)     # add new value
        
        if len(self.min_heap) > self.k:        # keep only k elements
            heapq.heappop(self.min_heap)       # remove smallest
        
        return self.min_heap[0]                # kth largest is heap root


# Your KthLargest object will be instantiated and called as such:
# obj = KthLargest(k, nums)
# param_1 = obj.add(val)

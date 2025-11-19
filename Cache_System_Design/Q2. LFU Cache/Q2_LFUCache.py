from collections import defaultdict, OrderedDict

class LFUCache(object):

    def __init__(self, capacity):
        """
        :type capacity: int
        """
        self.capacity = capacity              # Maximum size of cache
        self.minFreq = 0                      # Lowest frequency in cache
        self.keyToNode = {}                   # key -> [value, freq]
        self.freqToList = defaultdict(OrderedDict)  # freq -> OrderedDict of keys

    def get(self, key):
        """
        :type key: int
        :rtype: int
        """
        if key not in self.keyToNode:         # Key not found
            return -1

        value, freq = self.keyToNode[key]     # Get value and current freq
        del self.freqToList[freq][key]        # Remove from old freq list

        if not self.freqToList[freq]:         # If old freq list empty
            del self.freqToList[freq]
            if self.minFreq == freq:          # Update minFreq if needed
                self.minFreq += 1

        newFreq = freq + 1                    # Increase frequency
        self.freqToList[newFreq][key] = True  # Add to new freq list
        self.keyToNode[key] = [value, newFreq]  # Update freq

        return value

    def put(self, key, value):
        """
        :type key: int
        :type value: int
        :rtype: None
        """
        if self.capacity == 0:                # If no space, do nothing
            return

        if key in self.keyToNode:             # If key already exists
            self.keyToNode[key][0] = value    # Update value
            self.get(key)                     # Reuse get() to update freq
            return

        if len(self.keyToNode) == self.capacity:   # Cache full → evict LFU
            freq_list = self.freqToList[self.minFreq]
            evict_key, _ = freq_list.popitem(last=False)  # Remove LRU of LFU freq
            if not freq_list:
                del self.freqToList[self.minFreq]
            del self.keyToNode[evict_key]

        # Insert new key with freq=1
        self.keyToNode[key] = [value, 1]
        self.freqToList[1][key] = True
        self.minFreq = 1                      # Reset minimum frequency

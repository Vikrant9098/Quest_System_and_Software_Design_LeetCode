class AllOne:
    def __init__(self):
        self.myDict = {}                 # store key -> count

    def inc(self, key: str) -> None:
        if key in self.myDict:           # if key already exists
            self.myDict[key] += 1        # increase its count
        else:
            self.myDict[key] = 1         # otherwise set count to 1

    def dec(self, key: str) -> None:
        if key in self.myDict:               # if key exists
            if self.myDict[key] > 1:         # if count is more than 1
                self.myDict[key] -= 1        # reduce count by 1
            else:
                self.myDict.pop(key)         # if count becomes 0, remove key

    def getMaxKey(self) -> str:
        if not self.myDict:                  # if dictionary is empty
            return ""                        # return empty string
        maxVal = max(self.myDict.values())   # find highest count
        for key in self.myDict.keys():       # check every key
            if self.myDict[key] == maxVal:   # return key with highest count
                return key

    def getMinKey(self) -> str:
        if not self.myDict:                  # if dictionary is empty
            return ""                        # return empty string
        minVal = min(self.myDict.values())   # find lowest count
        for key in self.myDict.keys():       # check every key
            if self.myDict[key] == minVal:   # return key with lowest count
                return key

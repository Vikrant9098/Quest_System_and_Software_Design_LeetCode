class Block(object):
    def __init__(self, val=0):
        self.val = val               # count value of this block
        self.keys = set()            # keys that have this count
        self.before = None           # block before this one
        self.after = None            # block after this one

    def remove(self):
        self.before.after = self.after   # link previous block to next block
        self.after.before = self.before  # link next block to previous block
        self.before, self.after = None, None  # remove pointers completely

    def insert_after(self, new_block):
        old_after = self.after           # store next block
        self.after = new_block           # place new_block after current block
        new_block.before = self          # link new_block → current block
        new_block.after = old_after      # link new_block → old next block
        old_after.before = new_block     # link old next block → new_block


class AllOne(object):
    def __init__(self):
        self.begin = Block()             # start sentinel block (value 0)
        self.end = Block()               # end sentinel block (value 0)
        self.begin.after = self.end      # begin points to end
        self.end.before = self.begin     # end points to begin
        self.mapping = {}                # maps key to the block holding it

    def inc(self, key):
        if not key in self.mapping:      # key is new
            current_block = self.begin   # treat as count = 0
        else:
            current_block = self.mapping[key]  # get its block
            current_block.keys.remove(key)     # remove key from old block

        # check if next block exists for count+1
        if current_block.val + 1 != current_block.after.val:
            new_block = Block(current_block.val + 1)   # create new block
            current_block.insert_after(new_block)      # insert it
        else:
            new_block = current_block.after            # use next block

        new_block.keys.add(key)         # add key to new block
        self.mapping[key] = new_block   # update mapping

        # remove old block if empty and not sentinel
        if not current_block.keys and current_block.val != 0:
            current_block.remove()

    def dec(self, key):
        if not key in self.mapping:     # if key not present, do nothing
            return

        current_block = self.mapping[key]   # get block of key
        del self.mapping[key]               # remove mapping
        current_block.keys.remove(key)      # remove from block

        if current_block.val != 1:          # if count won't become zero
            # check if previous block exists for count-1
            if current_block.val - 1 != current_block.before.val:
                new_block = Block(current_block.val - 1)        # create new block
                current_block.before.insert_after(new_block)    # insert it
            else:
                new_block = current_block.before                # use previous block

            new_block.keys.add(key)         # add key to block
            self.mapping[key] = new_block   # update mapping

        if not current_block.keys:          # if old block empty
            current_block.remove()          # remove old block

    def getMaxKey(self):
        if self.end.before.val == 0:        # no real blocks
            return ""
        key = self.end.before.keys.pop()    # pick any key from max block
        self.end.before.keys.add(key)       # put it back
        return key                           # return max key

    def getMinKey(self):
        if self.begin.after.val == 0:       # no real blocks
            return ""
        key = self.begin.after.keys.pop()   # pick any key from min block
        self.begin.after.keys.add(key)      # put it back
        return key                           # return min key

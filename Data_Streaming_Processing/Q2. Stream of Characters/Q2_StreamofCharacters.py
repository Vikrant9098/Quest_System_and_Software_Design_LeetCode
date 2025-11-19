class StreamChecker(object):

    class TrieNode:
        def __init__(self):
            self.children = {}      # store next letters
            self.isWord = False     # mark end of a word

    def __init__(self, words):
        """
        :type words: List[str]
        """
        self.root = self.TrieNode()     # create Trie root
        self.stream = []                # store stream chars in reverse
        self.maxLen = 0                 # longest word length

        for w in words:                 # loop through words
            self.maxLen = max(self.maxLen, len(w))  # track longest word
            self._addWord(w)            # add word to Trie (reversed)

    def _addWord(self, word):
        node = self.root               # start at root
        for ch in reversed(word):      # insert characters in reverse
            if ch not in node.children:           # if no node exists
                node.children[ch] = self.TrieNode()  # create new node
            node = node.children[ch]   # move to next node
        node.isWord = True             # mark end of the word

    def query(self, letter):
        """
        :type letter: str
        :rtype: bool
        """
        self.stream.insert(0, letter)  # add new character at front

        if len(self.stream) > self.maxLen:   # limit size to longest word
            self.stream.pop()                # remove last extra character

        node = self.root               # start searching from Trie root

        for ch in self.stream:         # go through characters
            if ch not in node.children:  # if path does not exist
                return False             # cannot form a word
            node = node.children[ch]     # move to next node
            if node.isWord:              # full word found
                return True

        return False                     # no word matched

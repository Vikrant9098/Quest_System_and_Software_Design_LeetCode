import java.util.*;                      // import utilities

class StreamChecker {

    // Trie node structure
    class TrieNode {
        TrieNode[] children = new TrieNode[26]; // store 26 letters
        boolean isWord = false;                // marks end of word
    }

    private TrieNode root;               // root of the Trie
    private StringBuilder stream;        // keeps characters in reverse order
    private int maxLen;                  // longest word length

    public StreamChecker(String[] words) {
        root = new TrieNode();           // create root node
        stream = new StringBuilder();    // empty stream
        maxLen = 0;                      // start longest length as 0

        for (String w : words) {         // loop through all words
            maxLen = Math.max(maxLen, w.length()); // track longest word
            addWord(w);                  // add this word into reversed Trie
        }
    }

    private void addWord(String word) {
        TrieNode node = root;            // start from root

        for (int i = word.length() - 1; i >= 0; i--) { // go backwards
            int idx = word.charAt(i) - 'a';            // find index
            if (node.children[idx] == null) {          // if node missing
                node.children[idx] = new TrieNode();   // create node
            }
            node = node.children[idx];  // move to next node
        }

        node.isWord = true;             // mark that a word ends here
    }

    public boolean query(char letter) {
        stream.insert(0, letter);        // add new character at front (reverse)

        if (stream.length() > maxLen) {  // keep stream short (maxLen only)
            stream.setLength(maxLen);    // cut extra characters
        }

        TrieNode node = root;            // start checking from root

        for (int i = 0; i < stream.length(); i++) { // walk through stream
            int idx = stream.charAt(i) - 'a';       // convert char to index

            if (node.children[idx] == null) {       // no matching path
                return false;                       // no word possible
            }

            node = node.children[idx];              // move deeper

            if (node.isWord) return true;           // found complete word
        }

        return false;                               // no word matched
    }
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */

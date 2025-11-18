import java.util.*;

class LFUCache {

    // Node to store key, value, and frequency
    private class Node {
        int key, value, freq;
        Node(int key, int value) {
            this.key = key;       // store key
            this.value = value;   // store value
            this.freq = 1;        // new key always starts with freq = 1
        }
    }

    private int capacity;  // max size of cache
    private int minFreq;   // smallest frequency in cache
    private Map<Integer, Node> keyToNode; // map key -> node
    private Map<Integer, LinkedHashSet<Integer>> freqToList; // map freq -> keys with that freq

    public LFUCache(int capacity) {
        this.capacity = capacity;         // set capacity
        this.minFreq = 0;                 // no freq yet
        keyToNode = new HashMap<>();      // store key -> node
        freqToList = new HashMap<>();     // store freq -> set of keys
    }

    public int get(int key) {
        if (!keyToNode.containsKey(key))  // if key not found
            return -1;                    // return -1

        Node node = keyToNode.get(key);   // get the node
        updateFrequency(node);            // increase frequency
        return node.value;                // return value
    }

    public void put(int key, int value) {
        if (capacity == 0) return;        // if size 0, do nothing

        if (keyToNode.containsKey(key)) { // if key already exists
            Node node = keyToNode.get(key); 
            node.value = value;           // update value
            updateFrequency(node);        // update frequency
            return;                       // done
        }

        if (keyToNode.size() == capacity) { // cache full
            LinkedHashSet<Integer> list = freqToList.get(minFreq); // get keys with smallest freq
            int evictKey = list.iterator().next(); // first inserted = LRU
            list.remove(evictKey);       // remove from freq list
            if (list.isEmpty())          // if no keys left in this freq
                freqToList.remove(minFreq); // remove freq entry
            keyToNode.remove(evictKey);  // remove key from map
        }

        Node newNode = new Node(key, value); // create new key
        keyToNode.put(key, newNode);         // store node in map

        freqToList.computeIfAbsent(1, k -> new LinkedHashSet<>()) // get list for freq=1
                    .add(key);               // add new key

        minFreq = 1;                          // reset minimum frequency to 1
    }

    private void updateFrequency(Node node) {
        int oldFreq = node.freq;             // get old frequency
        int newFreq = oldFreq + 1;           // new frequency = old + 1

        LinkedHashSet<Integer> oldList = freqToList.get(oldFreq); // get old freq list
        oldList.remove(node.key);           // remove key from old freq
        if (oldList.isEmpty()) {            // if list empty
            freqToList.remove(oldFreq);     // remove freq entry
            if (minFreq == oldFreq)         // if this was the minimum freq
                minFreq++;                  // increase minimum freq
        }

        freqToList.computeIfAbsent(newFreq, x -> new LinkedHashSet<>()) // get new freq list
                  .add(node.key);           // add key to new freq

        node.freq = newFreq;                // update node's freq
    }
}

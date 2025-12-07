import java.util.*;

class RandomizedSet {
    // List to store the elements (so we can pick random in O(1))
    private List<Integer> data;
    // HashMap to store value -> index mapping (for O(1) insert/remove)
    private Map<Integer, Integer> pos;
    // Random object to generate random indices
    private Random rand;

    // Constructor: initialize the data structures
    public RandomizedSet() {
        data = new ArrayList<>();    // Empty list
        pos = new HashMap<>();       // Empty hashmap
        rand = new Random();         // Random generator
    }
    
    public boolean insert(int val) {
        // If val already exists, return false
        if (pos.containsKey(val)) {
            return false;
        }
        // Add val to the end of the list
        data.add(val);
        // Store its index in the map
        pos.put(val, data.size() - 1);
        return true; // Successfully inserted
    }
    
    public boolean remove(int val) {
        // If val does not exist, return false
        if (!pos.containsKey(val)) {
            return false;
        }
        
        // Get index of the element to remove
        int idx = pos.get(val);
        // Get the last element in the list
        int last = data.get(data.size() - 1);
        
        // Swap the element to remove with the last element
        data.set(idx, last);
        // Update the index of the last element in the map
        pos.put(last, idx);
        
        // Remove the last element from the list
        data.remove(data.size() - 1);
        // Remove val from the map
        pos.remove(val);
        
        return true; // Successfully removed
    }
    
    public int getRandom() {
        // Generate a random index in range [0, data.size())
        int randomIndex = rand.nextInt(data.size());
        // Return the element at that index
        return data.get(randomIndex);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */

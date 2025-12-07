import java.util.*;

class RandomizedCollection {

    // List to store all elements (including duplicates)
    private List<Integer> nums;

    // Map to store each value and all its indices in nums
    private Map<Integer, Set<Integer>> idxMap;

    // Random object for random number generation
    private Random rand;

    public RandomizedCollection() {
        nums = new ArrayList<>();          // Initialize list
        idxMap = new HashMap<>();          // Initialize map
        rand = new Random();               // Initialize random object
    }

    public boolean insert(int val) {
        // Check if val is new (not present before)
        boolean isNew = !idxMap.containsKey(val);

        // Add new index (current end of list) to map
        idxMap.computeIfAbsent(val, k -> new HashSet<>()).add(nums.size());

        // Add val to list
        nums.add(val);

        // Return true if val was not in collection before
        return isNew;
    }

    public boolean remove(int val) {
        // If val not found in collection, return false
        if (!idxMap.containsKey(val) || idxMap.get(val).isEmpty()) {
            return false;
        }

        // Get one index where val exists
        int removeIdx = idxMap.get(val).iterator().next();

        // Get last value in list
        int lastVal = nums.get(nums.size() - 1);

        // Replace the removed value with the last value
        nums.set(removeIdx, lastVal);

        // Update map: remove this index from val
        idxMap.get(val).remove(removeIdx);

        // Add new index for lastVal
        idxMap.get(lastVal).add(removeIdx);

        // Remove old index (the end one) for lastVal
        idxMap.get(lastVal).remove(nums.size() - 1);

        // Remove last element from list
        nums.remove(nums.size() - 1);

        // If val has no more indices, remove it from map
        if (idxMap.get(val).isEmpty()) {
            idxMap.remove(val);
        }

        // Return true (removal successful)
        return true;
    }

    public int getRandom() {
        // Pick a random index and return that value
        return nums.get(rand.nextInt(nums.size()));
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */

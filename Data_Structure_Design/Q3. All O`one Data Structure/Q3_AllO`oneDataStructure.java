import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

class AllOne {
    private Map<String, Integer> count;  // stores key -> its count
    private TreeSet<Pair<Integer, String>> set;  // sorted set of (count, key)

    public AllOne() {
        count = new HashMap<>();  // create map to track counts

        // create TreeSet sorted by count first, then by key
        set = new TreeSet<>((a, b) -> 
            a.getKey().equals(b.getKey()) 
                ? a.getValue().compareTo(b.getValue())  // if counts same, sort by key
                : a.getKey() - b.getKey()               // else sort by count
        );
    }

    public void inc(String key) {
        int n = count.getOrDefault(key, 0);  // get current count (0 if not present)
        count.put(key, n + 1);               // increase count by 1

        set.remove(new Pair<>(n, key));      // remove old (count, key)
        set.add(new Pair<>(n + 1, key));     // add new (count + 1, key)
    }

    public void dec(String key) {
        int n = count.get(key);               // get current count
        set.remove(new Pair<>(n, key));       // remove old (count, key)

        if (n == 1) count.remove(key);        // if count becomes 0, remove key completely
        else {
            count.put(key, n - 1);            // decrease count by 1
            set.add(new Pair<>(n - 1, key));  // add updated (count - 1, key)
        }
    }

    public String getMaxKey() {
        return set.isEmpty() ? "" : set.last().getValue();  // get key with highest count
    }

    public String getMinKey() {
        return set.isEmpty() ? "" : set.first().getValue(); // get key with lowest count
    }
}

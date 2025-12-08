class RangeFreqQuery {                           // define the RangeFreqQuery class

    private Map<Integer, List<Integer>> map;     // map to store value → list of indices

    public RangeFreqQuery(int[] arr) {           // constructor taking input array
        map = new HashMap<>();                   // create the HashMap

        for (int i = 0; i < arr.length; i++) {   // loop through all elements
            map.computeIfAbsent(arr[i],          // if value not in map, create list
                k -> new ArrayList<>())
                .add(i);                         // add index of value
        }
    }
    
    public int query(int left, int right, int value) {   // method to answer queries
        if (!map.containsKey(value)) return 0;           // if value not in array, return 0

        List<Integer> list = map.get(value);             // get index list of the value

        int l = lowerBound(list, left);                  // find first index >= left
        int r = upperBound(list, right);                 // find first index > right

        return r - l;                                    // count indices within range
    }

    private int lowerBound(List<Integer> list, int target) { // binary search lower bound
        int l = 0, r = list.size();                          // search range
        while (l < r) {                                      // continue while valid
            int mid = l + (r - l) / 2;                       // find middle
            if (list.get(mid) >= target) r = mid;            // move left if >= target
            else l = mid + 1;                                // else move right
        }
        return l;                                            // return first ≥ target
    }

    private int upperBound(List<Integer> list, int target) { // binary search upper bound
        int l = 0, r = list.size();                          // search range
        while (l < r) {                                      // continue while valid
            int mid = l + (r - l) / 2;                       // find middle
            if (list.get(mid) > target) r = mid;             // move left if > target
            else l = mid + 1;                                // else move right
        }
        return l;                                            // return first > target
    }
}

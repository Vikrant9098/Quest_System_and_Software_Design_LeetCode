import java.util.*;

class RangeModule {

    TreeMap<Integer, Integer> map;   // stores intervals as: start -> end

    public RangeModule() {
        map = new TreeMap<>();       // create the TreeMap
    }
    
    public void addRange(int left, int right) {

        Integer start = map.floorKey(left);  // find interval that starts before or at 'left'

        if (start != null && map.get(start) >= left) {   // if it overlaps the new range
            left = Math.min(left, start);                // expand left boundary
            right = Math.max(right, map.get(start));     // expand right boundary
            map.remove(start);                           // remove the old interval
        }

        Integer next = map.ceilingKey(left);             // find next interval starting after 'left'
        while (next != null && next <= right) {          // while intervals overlap
            right = Math.max(right, map.get(next));      // merge right boundary
            map.remove(next);                            // remove merged interval
            next = map.ceilingKey(left);                 // go to next
        }

        map.put(left, right);                            // add final merged interval
    }
    
    public boolean queryRange(int left, int right) {

        Integer start = map.floorKey(left);              // find interval starting before 'left'

        if (start == null) return false;                 // if no such interval, range not covered

        return map.get(start) >= right;                  // check if interval fully covers [left, right)
    }
    
    public void removeRange(int left, int right) {

        Integer start = map.floorKey(left);              // find interval < left

        if (start != null && map.get(start) > left) {    // if it overlaps the left boundary
            int end = map.get(start);                    // get its end
            map.remove(start);                           // remove original interval

            if (start < left) map.put(start, left);      // left portion remains
            if (end > right) map.put(right, end);        // right portion remains
        }

        Integer next = map.ceilingKey(left);             // find next interval to check
        while (next != null && next < right) {           // while inside removal area
            int end = map.get(next);                     // get its end
            map.remove(next);                            // remove interval

            if (end > right) {                           // if interval extends beyond 'right'
                map.put(right, end);                     // add back right part
                break;                                   // stop processing
            }

            next = map.ceilingKey(left);                 // move to next
        }
    }
}

/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */

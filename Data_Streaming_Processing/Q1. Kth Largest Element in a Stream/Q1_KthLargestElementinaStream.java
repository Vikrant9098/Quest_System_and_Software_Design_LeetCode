import java.util.PriorityQueue;      // import min-heap structure

class KthLargest {

    private PriorityQueue<Integer> minHeap; // heap to store k largest numbers
    private int k;                          // which kth largest we need

    public KthLargest(int k, int[] nums) {
        this.k = k;                         // store k
        minHeap = new PriorityQueue<>();    // create an empty min-heap

        for (int n : nums) {                // loop through initial numbers
            minHeap.offer(n);               // add number to heap
            if (minHeap.size() > k) {       // if heap has more than k numbers
                minHeap.poll();             // remove smallest number
            }
        }
    }
    
    public int add(int val) {
        minHeap.offer(val);                 // add new value to heap
        
        if (minHeap.size() > k) {           // if heap is larger than k
            minHeap.poll();                 // remove smallest number
        }
        
        return minHeap.peek();              // return kth largest (top of heap)
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */

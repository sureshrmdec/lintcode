import java.util.PriorityQueue;
import java.util.Collections;

public class DataStreamMedian {
    // Data Stream Median
    // http://www.lintcode.com/en/problem/data-stream-median/


    public int[] medianII(int[] nums) {

        PriorityQueue<Integer> large = new PriorityQueue<Integer>();
        PriorityQueue<Integer> small = new PriorityQueue<Integer>(nums.length, Collections.reverseOrder());

        int[] A = new int[nums.length];

        for (int i = 0; i < nums.length; ++i) {
            large.add(nums[i]);
            small.add(large.poll());
            if (large.size() < small.size())
                large.add(small.poll());

            A[i] = (large.size() > small.size()) ? large.peek() : small.peek();
        }

        return A;
    }
}

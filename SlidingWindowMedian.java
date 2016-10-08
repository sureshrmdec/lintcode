import java.util.PriorityQueue;
import java.util.Collections;
import java.util.ArrayList;

public class SlidingWindowMedian {
    // Sliding Window Median
    // http://www.lintcode.com/en/problem/sliding-window-median/

    public class Solution {

        // min queue
        private PriorityQueue<Integer> large;
        // max queue
        private PriorityQueue<Integer> small;

        private void addNum(int v) {
            large.add(v);
            small.add(large.poll());
            if (large.size() < small.size())
                large.add(small.poll());
        }

        private void deleteNum(int v) {
            if (v >= large.peek()) large.remove(v);
            else small.remove(v);

            if (large.size() < small.size()) large.add(small.poll());
        }

        private int median() {
            if (large.size() > small.size()) return large.peek();
            else return small.peek();
        }

        public ArrayList<Integer> medianSlidingWindow(int[] nums, int k) {
            ArrayList<Integer> results = new ArrayList<Integer>();
            if (nums == null || nums.length < k || k == 0) return results;

            large = new PriorityQueue<Integer>();
            small = new PriorityQueue<Integer>(k, Collections.reverseOrder());

            for (int i = 0; i < k; ++i) addNum(nums[i]);

            results.add(median());

            for (int i = k; i <= nums.length - 1; ++i) {
                deleteNum(nums[i - k]);
                addNum(nums[i]);
                results.add(median());
            }

            return results;
        }

    }
}

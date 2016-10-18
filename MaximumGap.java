import java.util.Arrays;
import java.io.IOException;
public class MaximumGap {
    public static class Solution {
        /**
         * @param nums: an array of integers
         * @return: the maximum difference
         */
        public int maximumGap(int[] nums) {

            int n = nums.length;
            if (n < 2) return 0;

            Arrays.sort(nums);
            int max = 0;
            for (int i = 1; i < n; ++i) {
                if (Math.abs(max) < Math.abs(nums[i] - nums[i - 1]))
                    max = Math.abs(nums[i] - nums[i - 1]);
            }

            return max;
        }

        // bucket sort method
        public int maximumGap2(int[] nums) {
            int n = nums.length;
            if (n < 2) return 0;

            int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
            for (int v: nums) {
                max = Math.max(max, v);
                min = Math.min(min, v);
            }

            if (max == min) return 0;
            int gap = (int) Math.ceil((double) (max - min) / (n - 1));
            int[] minbucket = new int[n];
            int[] maxbucket = new int[n];

            Arrays.fill(minbucket, Integer.MAX_VALUE);
            Arrays.fill(maxbucket, Integer.MIN_VALUE);

            for (int v: nums) {
                int k = (v - min) / gap;
                minbucket[k] = Math.min(minbucket[k], v);
                maxbucket[k] = Math.max(maxbucket[k], v);
            }

            int prev = min;
            for (int i = 0; i < n; ++i) {
                if (minbucket[i] == Integer.MAX_VALUE) continue;

                gap = Math.max(gap, minbucket[i] - prev);
                prev = maxbucket[i];
            }

            //System.out.println("maxbucket:");
            //for (int i = 0; i < n; ++i) {
            //    if (minbucket[i] == Integer.MAX_VALUE) continue;
            //    System.out.print(maxbucket[i] + " ");
            //}
            //System.out.println();
            //System.out.println("maxbucket:");
            //System.out.println();

            //System.out.println(gap);
            return gap;
        }
    }

    public static void main(String[] args) {
        try {
            TestcaseReader reader = new TestcaseReader("./9.in");
            int[] array = reader.readArray();

            Solution s = new Solution();
            s.maximumGap2(array);

            System.out.println(Integer.MAX_VALUE);
            System.out.println(Integer.MIN_VALUE);
        }
        catch (IOException e) {
        }
    }
}

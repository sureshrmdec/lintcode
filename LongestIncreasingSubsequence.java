public class LongestIncreasingSubsequence {
    public class Solution {
        /**
         * @param nums: The integer array
         * @return: The length of LIS (longest increasing subsequence)
         */
        public int longestIncreasingSubsequence(int[] nums) {

            if (nums.length == 0) return 0;
            else if (nums.length == 1) return 1;

            int[] f = new int[nums.length];
            int max = 0;
            for (int i = 0; i < nums.length; ++i) {
                f[i] = 1;
                for (int j = 0; j < i; ++j) {
                    if (nums[j] < nums[i])
                        f[i] = f[i] > f[j] ? f[i] : f[j] + 1;
                }

                max = Math.max(f[i], max);
            }

            return max;
        }
    }
}

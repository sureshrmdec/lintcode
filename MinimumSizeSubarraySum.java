public class MinimumSizeSubarraySum {
    public class Solution {
        /**
         * @param nums: an array of integers
         * @param s: an integer
         * @return: an integer representing the minimum size of subarray
         */
        public int minimumSize(int[] nums, int s) {

            int sum = 0;
            int j = 0;
            int ans = Integer.MAX_VALUE;
            for (int i = 0; i < nums.length; ++i) {
                while (sum < s && j < nums.length) {
                    sum += nums[j];
                    j++;
                }

                if (sum >= s)
                    ans = Math.min(ans, j - i);

                sum -= nums[i];
            }

            return Integer.MAX_VALUE == ans ? -1 : ans;
        }
    }
}

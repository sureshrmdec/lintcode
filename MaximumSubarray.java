public class MaximumSubarray {
    public static class Solution {
        /**
         * @param nums: A list of integers
         * @return: A integer indicate the sum of max subarray
         */
        public int maxSubArray(int[] nums) {

            if (nums.length == 0) return 0;

            int i = 0;
            int ans = Integer.MIN_VALUE, sum = 0;
            int j = 0;
            while (i < nums.length) {
                j = i;

                while (j < nums.length) {
                    sum += nums[j];
                    ans = Math.max(ans, sum);
                    if (sum < 0) {
                        i = j + 1;
                        sum = 0;
                        break;
                    }
                    j++;
                }

                if (j == nums.length) break;
            }

            return ans;
        }
    }

    public static void main(String[] args) {
        //int[] array = {-2,2,-3,4,-1,2,1,-5,3};
        int[] array = {-1};

        Solution s = new Solution();
        System.out.println(s.maxSubArray(array));
    }
}

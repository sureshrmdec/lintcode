public class HouseRobberII {
    // http://www.lintcode.com/en/problem/house-robber-ii/

    public class Solution {
        /**
         * @param nums: An array of non-negative integers.
         * return: The maximum amount of money you can rob tonight
         */
        public int houseRobber2(int[] nums) {
            int N = nums.length;

            if (N == 0) return 0;
            else if (N == 1) return nums[0];
            else if (N == 2) return Math.max(nums[0], nums[1]);
            else if (N == 3) return Math.max(nums[0], Math.max(nums[1], nums[2]));

            // case 1: force including nums[0] and excluding nums[-1]
            int[] f = new int[N];
            f[0] = nums[0];
            f[1] = f[0];
            for (int i = 2; i < N; ++i)
                f[i] = Math.max(f[i - 2] + nums[i], f[i - 1]);

            // case 2: force excluding nums[0]
            int[] g = new int[N];
            g[0] = 0;
            g[1] = nums[1];
            for (int i = 2; i < N; ++i)
                g[i] = Math.max(g[i - 2] + nums[i], g[i - 1]);

            return Math.max(g[N - 1], f[N - 2]);
        }
    }
}

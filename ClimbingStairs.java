public class ClimbingStairs {
    public class Solution {
        /**
         * @param n: An integer
         * @return: An integer
         */
        public int climbStairs(int n) {
            if (n == 0) return 1;

            int[] f = new int[n + 1];

            f[0] = 1;
            f[1] = 1;
            for (int i = 2; i <= n; ++i)
                f[i] = f[i - 2] + f[i - 1];

            return f[n];
        }
    }
}

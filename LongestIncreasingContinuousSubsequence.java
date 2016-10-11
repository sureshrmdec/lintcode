public class LongestIncreasingContinuousSubsequence {
    public class Solution {
        /**
         * @param A an array of Integer
         * @return  an integer
         */
        public int longestIncreasingContinuousSubsequence(int[] A) {

            if (A.length == 0) return 0;

            int[] f = new int[A.length];
            f[0] = 1;
            for (int i = 1; i < A.length; ++i) {
                if (A[i] > A[i - 1])
                    f[i] = f[i - 1] + 1;
                else
                    f[i] = 1;
            }

            int[] g = new int[A.length];
            g[A.length - 1] = 1;
            for (int i = A.length - 2; i >= 0; --i) {
                if (A[i] > A[i + 1])
                    g[i] = g[i + 1] + 1;
                else
                    g[i] = 1;
            }

            int max = 0;
            for (int i = 0; i < A.length; ++i) {
                max = Math.max(max, Math.max(f[i], g[i]));
            }

            return max;
        }
    }
}

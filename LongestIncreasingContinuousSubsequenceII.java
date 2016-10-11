public class LongestIncreasingContinuousSubsequenceII {
    public static class Solution {
        /**
         * @param A an integer matrix
         * @return  an integer
         */

        private int m = 0, n = 0;
        private int[][] f;
        private int[] x = {0, 0, -1, 1};
        private int[] y = {-1, 1, 0, 0};

        public int longestIncreasingContinuousSubsequenceII(int[][] A) {
            m = A.length;
            if (m == 0) return 0;
            n = A[0].length;

            //print(A);
            f = new int[m][n];

            for (int i = 0; i < m; ++i)
                for (int j = 0; j < n; ++j)
                    f[i][j] = -1;

            //print(f);

            for (int i = 0; i < m; ++i)
                for (int j = 0; j < n; ++j)
                    f[i][j] = find(i, j, A);

            //print(f);
            int max = 0;
            for (int i = 0; i < m; ++i)
                for (int j = 0; j < n; ++j)
                    max = Math.max(max, f[i][j]);

            return max;
        }

        private int find(int i, int j, int[][] A) {
            if (f[i][j] != -1) return f[i][j];

            for (int k = 0; k < 4; ++k) {
                if (i + x[k] < 0 || i + x[k] == m || j + y[k] < 0 || j + y[k] == n) continue;

                if (A[i + x[k]][j + y[k]] < A[i][j]) f[i][j] = Math.max(f[i][j], find(i + x[k], j + y[k], A) + 1);
            }
            if (f[i][j] == -1) f[i][j] = 1;
            return f[i][j];
        }

        private void print(int[][] A) {
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    System.out.print(A[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1 ,2 ,3 ,4 ,5},
            {16,17,24,23,6},
            {15,18,25,22,7},
            {14,19,20,21,8},
            {13,12,11,10,9}
        };

        Solution s = new Solution();
        System.out.println(s.longestIncreasingContinuousSubsequenceII(matrix));
    }
}

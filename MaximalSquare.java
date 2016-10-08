public class MaximalSquare {
    // http://www.lintcode.com/en/problem/maximal-square/

    public static class Solution {
        /**
         * @param matrix: a matrix of 0 and 1
         * @return: an integer
         */
        public int maxSquare(int[][] matrix) {
            if (matrix.length == 0) return 0;

            int M = matrix.length;
            int N = matrix[0].length;
            int[][] f = new int[M][N];

            for (int i = 0; i < M; ++i) {
                for (int j = 0; j < N; ++j) {
                    f[i][j] = foo(f, matrix[i][j], i, j);
                }
            }

            int max = 0;
            for (int i = 0; i < M; ++i) {
                for (int j = 0; j < N; ++j) {
                    if (f[i][j] > max)
                        max = f[i][j];
                }
            }

            //print(matrix);
            //System.out.println();
            //print(f);
            return max*max;
        }

        private int foo(int[][] f, int v, int i, int j) {
            if (v == 0) return 0;
            if (i == 0 || j == 0) return v;

            return Math.min(f[i - 1][j - 1], Math.min(f[i - 1][j], f[i][j - 1])) + 1;
        }

        private void print(int[][] matrix) {
            int M = matrix.length;
            int N = matrix[0].length;
            for (int i = 0; i < M; ++i) {
                for (int j = 0; j < N; ++j) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        //int[][] matrix = {{1,0,1,0,0},{1,0,1,1,1},{1,1,1,1,1},{1,0,0,1,0}};
        int[][] matrix = {{0,1,1,1,1,1,1,1,1,1},{1,0,1,1,1,1,1,1,1,1},{1,1,0,1,1,1,1,1,1,1},{1,1,1,0,1,1,1,1,1,1},{1,1,1,1,0,1,1,1,1,1},{1,1,1,1,1,0,1,1,1,1},{1,1,1,1,1,1,0,1,1,1},{1,1,1,1,1,1,1,0,1,1},{1,1,1,1,1,1,1,1,0,1},{1,1,1,1,1,1,1,1,1,0}};

        Solution s = new Solution();
        System.out.println("max square area is: " + s.maxSquare(matrix));
    }
}

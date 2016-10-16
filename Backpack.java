public class Backpack {
    public static class Solution {

        private int m;
        private int n;
        public int backPack(int m, int[] A) {

            n = A.length;
            this.m = m;
            if (n == 0) return 0;
            else if (n == 1 && A[0] <= m) return A[0];


            boolean[][] f = new boolean[n][m + 1];
            boolean[][] visited = new boolean[n][m + 1];

            for (int i = 0; i < n; ++i)
                for (int j = 0; j < m + 1; ++j) {
                    f[i][j] = false;
                    visited[i][j] = false;
                }
            //print(f);

            for (int i = 0; i < n; ++i) {
                f[i][0] = true;
                visited[i][0] = true;
            }
            if (A[0] < m + 1) {
                f[0][A[0]] = true;
                visited[0][A[0]] = true;
            }
            //print(f);

            for (int i = 1; i < n; ++i)
                for (int j = 0; j < m + 1; ++j)
                    dp(i, j, f, visited, A);
            //print(f);

            int max = 0;
            for (int i = 0; i < n; ++i)
                for (int j = 0; j < m + 1; ++j)
                    if (f[i][j]) max = Math.max(max, j);

            return max;
        }

        private boolean dp(int i, int j, boolean[][] f, boolean[][] visited, int[] A) {
            if (i < 0 || i >= n || j < 0 || j >= m + 1) return false;
            if (visited[i][j] == true) return f[i][j];

            f[i][j] = dp(i - 1, j - A[i], f, visited, A) || dp(i - 1, j, f, visited, A);
            //System.out.println("i: " + i + ", j: " + j + ", f: " + f[i][j] + ", visited: " + visited[i][j]);
            visited[i][j] = true;
            return f[i][j];
        }

        private void print(boolean[][] f) {
            for (int i = 0; i < f.length; ++i) {
                for (int j = 0; j < f[0].length; ++j)
                    System.out.print(f[i][j] + " ");
                System.out.println();
            }
        }
    }

    public static class SolutionHash {

        public int backPack(int m, int[] A) {

            HashSet
        }

    }

    public static void main(String[] args) {
        //int m = 10;
        //int[] array = {3,4,8,5};
        int m = 90;
        int[] array = {12,3,7,4,5,13,2,8,4,7,6,5,7};
        Solution s = new Solution();
        System.out.println(s.backPack(m, array));
    }
}

import java.util.Arrays;

public class CoinsInALineIII {
    public static class Solution {
        /**
         * @param values: an array of integers
         * @return: a boolean which equals to true if the first player will win
         */
        public boolean firstWillWin(int[] values) {

            int n = values.length;
            if (n == 0) return false;
            if (n == 1) return true;

            int[][] max = new int[n][n];
            int[][] sum = new int[n][n];

            for (int i = 0; i < n; ++i) max[i][i] = values[i];
            
            for (int i = 0; i < n; ++i) {
                sum[i][i] = values[i];
                for (int j = i + 1; j < n; ++j)
                    sum[i][j] = sum[i][j - 1] + values[j];
            }

            for (int k = 1; k < n; ++k) {
                int i = 0;
                int j = k;
                while (j < n) {
                    max[i][j] = Math.max(values[i] + sum[i + 1][j] - max[i + 1][j], values[j] + sum[i][j - 1] - max[i][j - 1]);
                    ++i;
                    ++j;
                }
            }

            int tmp = 0;
            for (int v: values)
                tmp += v;

            return 2 * max[0][n - 1] > tmp;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] A = {3, 2, 2};
        System.out.println(Arrays.toString(A) + ", " + s.firstWillWin(A));
        int[] B = {1, 2, 4};
        System.out.println(Arrays.toString(B) + ", " + s.firstWillWin(B));
        int[] C = {1, 20, 4};
        System.out.println(Arrays.toString(C) + ", " + s.firstWillWin(C));
    }
}

public class CoinsInALineII {
    public static class Solution {
        /**
         * @param values: an array of integers
         * @return: a boolean which equals to true if the first player will win
         */
        public boolean firstWillWin(int[] values) {

            int n = values.length;
            if (n == 0) return false;
            else if (n <= 2) return true;

            int[] sums = new int[n];
            sums[n - 1] = values[n - 1];
            for (int i = n - 2; i >= 0; --i)
                sums[i] = sums[i + 1] + values[i];

            int[] f = new int[n];
            f[n - 1] = sums[n - 1];
            f[n - 2] = sums[n - 2];

            for (int i = n - 3; i >= 0; --i) {
                int v = values[i] + (sums[i + 1] - f[i + 1]);
                int w = values[i] + values[i + 1] + (sums[i + 2] - f[i + 2]);

                f[i] = v > w ? v : w;
            }

            return f[0] > sums[0] / 2;
        }
    }

    public static void main(String[] args) {
        //int[] A = {1, 2, 2};
        int[] A = {1, 2, 4};
        Solution s = new Solution();
        System.out.println(s.firstWillWin(A));
    }
}

public class CoinsInALine {
    public class Solution {
        /**
         * @param n: an integer
         * @return: a boolean which equals to true if the first player will win
         */
        public boolean firstWillWin(int n) {

            if (n == 0) return false;
            if (n <= 2) return true;

            boolean[] f = new boolean[n + 1];

            f[1] = true;
            f[2] = true;

            for (int i = 3; i < n + 1; ++i) {
                if (f[i - 1] == false || f[i - 2] == false)
                    f[i] = true;
                else
                    f[i] = false;
            }
            return f[n];
        }
    }
}

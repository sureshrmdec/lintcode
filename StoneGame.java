import java.util.ArrayList;

public class StoneGame {
    public static class Solution {
        /**
         * @param A an integer array
         * @return an integer
         */

        public int stoneGame(int[] A) {

            int n = A.length;
            if (n <= 1) return 0;
            if (n == 2) return A[0] + A[1];

            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int i = 0; i < n; ++i)
                list.add(A[i]);

            int sum = 0;
            int count = 0;
            while (count++ <= list.size() - 1) {

                int i = 0;
                while (i < list.size() && A[i] == 0) i++;
                //System.out.println("i: " + i);

                int min = Integer.MAX_VALUE, left = 0, right = 0;
                for (; i < list.size();) {
                    int j = i + 1;
                    while (j < list.size() && A[j] == 0) j++;
                    //System.out.println("j: " + j);

                    if (j < list.size()) {
                        if (min > A[i] + A[j]) {
                            min = A[i] + A[j];
                            left = i;
                            right = j;
                        }
                        i = j;
                    }
                    else break;
                }

                if (min != Integer.MAX_VALUE) sum += min;
                A[left] = 0;
                A[right] = min;
                //System.out.println("min: " + min + ", left: " + left + ", right: " + right);
            }

            return sum;
        }
    }

    public static class Solution1 {
        public int stoneGame(int[] A) {

            int n = A.length;
            if (n < 2) return 0;

            int[][] sum = new int[n][n];
            for (int i = 0; i < n; ++i) {
                sum[i][i] = A[i];
                for (int j = i + 1; j < n; ++j)
                    sum[i][j] += sum[i][j - 1] + A[j];
            }

            //print(sum);

            int[][] dp = new int[n][n];
            for (int i = 0; i < n; i++)
                for (int j = i; j < n; j++)
                    if (i == j) dp[i][j] = 0;
                    else dp[i][j] = Integer.MAX_VALUE;

            //print(dp);

            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    dp[i][j] = search(i, j, dp, sum);
            //print(dp);

            return dp[0][n - 1];
        }

        private int search(int start, int end, int[][] dp, int[][] sum) {
            if (dp[start][end] != Integer.MAX_VALUE) return dp[start][end];

            if (start + 1 == end) {
                dp[start][end] = sum[start][end];
                return dp[start][end];
            }

            for (int i = start; i < end; ++i)
                dp[start][end] = Math.min(dp[start][end], search(start, i, dp, sum) + search(i + 1, end, dp, sum) + sum[start][end]);

            return dp[start][end];
        }

        private void print(int[][] A) {
            for (int i = 0; i < A.length; ++i) {
                for (int j = 0; j < A[0].length; ++j)
                    System.out.print(A[i][j] + " ");
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        //int[] array = {1,1,1,1};
        //int[] array = {4,1,1,4};
        int[] array = {4,4,5,9};


        Solution1 s = new Solution1();
        System.out.println(s.stoneGame(array));
    }
}

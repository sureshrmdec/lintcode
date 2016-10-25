import java.util.HashMap;

public class SubmatrixSum {

    public static class Solution {
        /**
         * @param matrix an integer matrix
         * @return the coordinate of the left-up and right-down number
         */
        public int[][] submatrixSum(int[][] matrix) {

            int m = matrix.length;
            int n = matrix[0].length;
            int[][] sum = new int[m][n];

            int[][] ans = new int[2][2];
            for (int i = 0; i < m; ++i)
                for (int j = 0; j < n; ++j) {
                    sum[i][j] = getsum(i, j, sum, matrix);
                }
            //print(sum);



            //System.out.println("find submatrix");
            for (int l = -1; l < m; ++l) {
                for (int h = l + 1; h < m; ++h) {
                    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
                    map.put(0, -1);
                    for (int j = 0; j < n; ++j) {
                        int upsum = (l == -1) ? 0 : sum[l][j];
                        int diff = sum[h][j] - upsum;
                        if (map.containsKey(diff)) {
                            ans[0][0] = l + 1;
                            ans[0][1] = map.get(diff) + 1;
                            ans[1][0] = h;
                            ans[1][1] = j;
                            return ans;
                        }
                        else
                            map.put(diff, j);
                    }
                }
            }

            return ans;
        }

        public int getsum(int i, int j, int[][] sum, int[][] matrix) {
            int left = (i - 1 < 0) ? 0 : sum[i - 1][j];
            int up = (j - 1 < 0) ? 0 : sum[i][j - 1];
            int leftup = (i - 1 < 0 || j - 1 < 0) ? 0 : sum[i - 1][j - 1];
            return left + up + matrix[i][j] - leftup;
        }

        private void print(int[][] matrix) {
            for (int i = 0; i < matrix.length; ++i) {
                for (int j = 0; j < matrix[0].length; ++j)
                    System.out.print(matrix[i][j] + " ");
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {

        Solution s = new Solution();

        //int[][] matrix = {
        //    {1 ,5 ,7},
        //    {3 ,7 ,-8},
        //    {4 ,-8 ,9},
        //};
        int[][] matrix = {{2, -2}, {-4, 4}};

        int[][] ans = s.submatrixSum(matrix);
        s.print(ans);
    }

}

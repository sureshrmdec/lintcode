import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class FindPeakElementII {

    public static class Solution {
        /**
         * @param A: An integer matrix
         * @return: The index of the peak
         */

        // O(n * m)
        public List<Integer> findPeakII(int[][] A) {

            ArrayList<Integer> ans = new ArrayList<Integer>();
            int m = A.length;
            if (m == 0) return ans;
            int n = A[0].length;

            int[] x = {0, 0, -1, 1};
            int[] y = {-1, 1, 0, 0};
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    boolean peak = true;
                    for (int k = 0; k < 4; ++k) {
                        if (i + x[k] < 0 || i + x[k] == m || j + y[k] < 0 || j + y[k] == n) continue;
                        if (A[i][j] <= A[i + x[k]][j + y[k]]) {
                            peak = false;
                            break;
                        }
                    }

                    if (peak) {
                        ans.add(i);
                        ans.add(j);
                        return ans;
                    }
                }
            }

            return ans;
        }

        // official
        public List<Integer> findPeakII(int[][] A) {
            // this is the nlog(n) method
            int low = 1, high = A.length-2;
            List<Integer> ans = new ArrayList<Integer>();
            while(low <= high) {
                int mid = (low + high) / 2;
                int col = find(mid, A);
                if(A[mid][col] < A[mid - 1][col]) {
                    high = mid - 1;
                } else if(A[mid][col] < A[mid + 1][col]) {
                    low = mid + 1;
                } else {
                    ans.add(mid);
                    ans.add(col);
                    break;
                }
            }
            return ans;
        }

        int find(int row, int [][]A) {
            int col = 0;
            for(int i = 0; i < A[row].length; i++) {
                if(A[row][i] > A[row][col]) {
                    col = i;
                }
            }
            return col;
        }
    }

    public static void main(String[] args) {
        //int[][] matrix = { {1, 5, 3}, {4, 10, 9}, {2, 8, 7} };
        //int[][] matrix = {{1,2,3,4,5,6},{14,15,16,17,18,8},{12,13,11,10,9,7}};
        //int[][] matrix = {{1,3,2},{4,6,5},{7,9,8},{13,15,14},{10,12,11}};

        Solution s = new Solution();

        try {
            TestcaseReader reader = new TestcaseReader("./15.in");
            int[][] matrix = reader.readMatrix();
            for (int i = 223; i < 226; ++i) {
                for (int j = 223; j < 226; ++j)
                    System.out.print(matrix[i][j] + " ");
                System.out.println();
            }
            System.out.println("m: " + matrix.length + ", n: " + matrix[0].length);
            System.out.println(s.findPeakII_optimized(matrix));
            System.out.println(s.findPeakII(matrix));
        }
        catch (IOException e) {
        }
    }
}

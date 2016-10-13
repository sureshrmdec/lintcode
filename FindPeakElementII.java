import java.util.ArrayList;
import java.util.List;

public class FindPeakElementII {

    class Solution {
        /**
         * @param A: An integer matrix
         * @return: The index of the peak
         */
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


        // could optimize

    }
}

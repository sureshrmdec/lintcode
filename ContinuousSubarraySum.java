public class ContinuousSubarraySum {
    public class Solution {
        /**
         * @param A an integer array
         * @return  A list of integers includes the index of the first number and the index of the last number
         */
        public ArrayList<Integer> continuousSubarraySum(int[] A) {

            ArrayList<Integer> ans = new ArrayList<Integer>();
            int n = A.length;
            if (n == 0) return ans;
            if (n == 1) {
                ans.add(0);
                ans.add(0);
                return ans;
            }

            int sum = 0;
            int start = 0;
            int max = Integer.MIN_VALUE;
            int maxstart = 0;
            int maxend = 0;
            for (int i = 0; i < n; ++i) {
                sum += A[i];
                if (max < sum) {
                    max = sum;
                    maxstart = start;
                    maxend = i;
                }

                if (sum < 0) {
                    sum = 0;
                    start = i + 1;
                }
            }

            ans.add(maxstart);
            ans.add(maxend);
            return ans;
        }
    }
}

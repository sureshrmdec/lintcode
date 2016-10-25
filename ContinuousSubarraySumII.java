import java.util.ArrayList;
import java.io.IOException;
public class ContinuousSubarraySumII {
    public static class Solution {
        /**
         * @param A an integer array
         * @return  A list of integers includes the index of the first number and the index of the last number
         */
        public ArrayList<Integer> continuousSubarraySumII(int[] A) {

            ArrayList<Integer> ans = new ArrayList<Integer>();
            int n = A.length;
            if (n == 0) return ans;

            int sum = 0;
            for (int i = 0; i < n; ++i)
                sum += A[i];

            int maxtmp = 0;
            int maxstart = 0, maxend = 0, start = 0, max = Integer.MIN_VALUE;
            for (int i = 0; i < n; ++i) {
                maxtmp += A[i];

                if (max < maxtmp) {
                    max = maxtmp;
                    maxstart = start;
                    maxend = i;
                }

                if (maxtmp < 0) {
                    maxtmp = 0;
                    start = i + 1;
                }
            }

            int mintmp = 0;
            int minstart = 0, minend = 0, min = Integer.MIN_VALUE;
            start = 0;
            for (int i = 0; i < n; ++i) {
                mintmp += -A[i];

                if (min < mintmp) {
                    min = mintmp;
                    minstart = start;
                    minend = i;
                }

                if (mintmp < 0) {
                    mintmp = 0;
                    start = i + 1;
                }
            }

            System.out.println("max: " + max + ", min: " + min + ", sum: " + sum);

            if (max < (sum + min) && (minend - minstart) != n - 1) {
                ans.add(minend + 1);
                ans.add(minstart - 1);
            }
            else {
                ans.add(maxstart);
                ans.add(maxend);
            }

            return ans;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        //int[] array = {-5,10,5,-3,1,1,1,-2,3,-4};
        int[] array = {-101,-33,-44,-55,-67,-78,-101,-33,-44,-55,-67,-78,-100,-200,-1000,-22,-100,-200,-1000,-22};
        System.out.println(s.continuousSubarraySumII(array));

        //try {
        //TestcaseReader reader = new TestcaseReader("./11.in");
        //int[] array = reader.readArray();
        //System.out.println(s.continuousSubarraySumII(array));
        //}
        //catch (IOException e) {
        //}
    }
}

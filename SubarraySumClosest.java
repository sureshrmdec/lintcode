import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class SubarraySumClosest {
    public static class Solution {
        /**
         * @param nums: A list of integers
         * @return: A list of integers includes the index of the first number 
         *          and the index of the last number
         */
        public int[] subarraySumClosest(int[] nums) {

            int n = nums.length;
            int[] ans = new int[2];
            if (n == 0) return ans;
            if (n == 1) {
                ans[0] = 0;
                ans[1] = 0;
                return ans;
            }

            int tmp = Integer.MAX_VALUE;
            int start = 0, end = 0;
            for (int i = 0; i < n; ++i) {
                int sum = 0;
                for (int j = i; j < n; ++j) {
                    sum += nums[j];

                    if (Math.abs(sum) < Math.abs(tmp)) {
                        tmp = sum;
                        start = i;
                        end = j;
                    }

                }
            }

            ans[0] = start;
            ans[1] = end;

            return ans;
        }

        private class Node {
            public int index, sum;
            public Node(int i, int s) { sum = s; index = i;}

            public String toString() { return "(" + index + ", " + sum + ")"; }
        }

        public class NodeComparator implements Comparator<Node> {
            public int compare(Node v, Node w) { return v.sum - w.sum; }
        }

        public int[] subarraySumClosest2(int[] nums) {
            int n = nums.length;
            int[] ans = new int[2];
            if (n == 0) return ans;

            ArrayList<Node> sums = new ArrayList<Node>();
            // dummy 0
            sums.add(new Node(-1, 0));

            for (int i = 0; i < n; ++i) {
                sums.add(new Node(i, sums.get(i).sum + nums[i]));
            }

            //System.out.println("sums before sort:\n" + sums);
            Collections.sort(sums, new NodeComparator());
            //System.out.println("sums after sort:\n" + sums);

            int tmp = Integer.MAX_VALUE;
            int start = 0, end = 0;
            for (int i = 0; i < n; ++i) {
                Node v = sums.get(i + 1);
                Node w = sums.get(i);
                if (Math.abs(tmp) > Math.abs(v.sum - w.sum)) {
                    start = Math.min(v.index, w.index) + 1;
                    end = Math.max(v.index, w.index);
                    tmp = v.sum - w.sum;
                }
            }

            ans[0] = start;
            ans[1] = end;
            return ans;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        int[] array = {-3,1,1,-3,5};
        int[] ans = s.subarraySumClosest2(array);
        System.out.println(ans[0] + " " + ans[1]);
    }
}

import java.util.*;

public class ConsistentHashing {
    public static class Solution {
        /**
         * @param n a positive integer
         * @return n x 3 matrix
         */

        public List<List<Integer>> consistentHashing(int n) {

            List<List<Integer>> result = new LinkedList<List<Integer>>();
            if (n == 0) return result;

            List<Integer> interval = new ArrayList<Integer>();
            interval.add(0); interval.add(359); interval.add(1);
            result.add(interval);

            for (int i = 2; i <= n; i++) {
                int max = Integer.MIN_VALUE;
                int machineId = Integer.MAX_VALUE;
                List<Integer> maxInterval = null;
                int index = 0;
                int count = 0;
                for (List<Integer> currentInterval: result) {
                    if (max < currentInterval.get(1) - currentInterval.get(0) ||
                            (max == currentInterval.get(1) - currentInterval.get(0) && currentInterval.get(2) < machineId)) {
                        max = currentInterval.get(1) - currentInterval.get(0);
                        machineId = currentInterval.get(2);
                        index = count;
                        maxInterval = currentInterval;
                    }
                    count++;
                }

                List<Integer> first = new ArrayList<Integer>();
                first.add(maxInterval.get(0));
                first.add((maxInterval.get(0) + maxInterval.get(1)) / 2);
                first.add(maxInterval.get(2));

                List<Integer> second = new ArrayList<Integer>();
                second.add((maxInterval.get(0) + maxInterval.get(1)) / 2 + 1);
                second.add(maxInterval.get(1));
                second.add(i);

                result.add(index + 1, first);
                result.add(index + 2, second);
                result.remove(index);
            }

            return result;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.consistentHashing(1));
        System.out.println(s.consistentHashing(2));
        System.out.println(s.consistentHashing(3));
        System.out.println(s.consistentHashing(4));
        System.out.println(s.consistentHashing(5));
        System.out.println(s.consistentHashing(6));
    }
}

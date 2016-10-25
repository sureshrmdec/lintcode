import java.util.*;

public class ConsistentHashingII {
    public static class Solution {
        // @param n a positive integer
        // @param k a positive integer
        // @return a Solution object
        private static int[] A;
        private static HashSet<Integer> set;
        private static Random rand;
        private static int num;
        public static Solution create(int n, int k) {
            A = new int[n];
            for (int i = 0; i < n; i++)
                A[i] = -1;

            set = new HashSet<Integer>();
            rand = new Random();
            num = k;

            return new Solution();
        }

        // @param machine_id an integer
        // @return a list of shard ids
        public List<Integer> addMachine(int machine_id) {
            int count = 0; 
            ArrayList<Integer> result = new ArrayList<Integer>();
            while (true) {
                int k = rand.nextInt(A.length);
                if (set.contains(k)) continue;

                set.add(k);
                result.add(k);
                A[k] = machine_id;
                if (++count == num) break;
            }
            return result;
        }

        // @param hashcode an integer
        // @return a machine id
        public int getMachineIdByHashCode(int hashcode) {
            int right = hashcode;
            int count = 0;
            while (true) {
                if (right >= A.length) {
                    if (count == 1) break;
                    right = right - A.length;
                    count++;
                }

                if (A[right] != -1) return A[right];
                else right++;
            }

            return -1;
        }
    }
}

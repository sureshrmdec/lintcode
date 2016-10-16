public class SubarraySum {
    public class Solution {
        /**
         * @param nums: A list of integers
         * @return: A list of integers includes the index of the first number 
         * and the index of the last number
         */
        public ArrayList<Integer> subarraySum(int[] nums) {

            ArrayList<Integer> ans = new ArrayList<Integer>();
            int n = nums.length;
            if (n == 0) return ans;

            for (int i = 0; i < n; ++i) {
                int sum = 0;
                for (int j = i; j < n; ++j) {
                    sum += nums[j];
                    if (sum == 0) {
                        ans.add(i);
                        ans.add(j);
                        return ans;
                    }
                }
            }
            return ans;
        }
    }

    public class SolutionOffical {

        public ArrayList<Integer> subarraySum(int[] nums) 

            ArrayList<Integer> ans = new ArrayList<Integer>();
            int n = nums.length;
            if (n == 0) return ans;

            HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

            int sum = 0;
            map.put(0, -1);
            for (int i = 0; i < n; ++i) {
                sum += nums[i];

                if (map.containsKey(sum)) {
                    ans.add(map.get(sum) + 1);
                    ans.add(i);
                    return ans;
                }
            }
            return ans;
        }

    }
}

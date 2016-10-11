import java.util.ArrayList;

public class MaximumProductSubarray {
    public static class Solution {
        /**
         * @param nums: an array of integers
         * @return: an integer
         */
        public int maxProduct(int[] nums) {

            if (nums.length == 0) return 0;
            else if (nums.length == 1) return nums[0];

            ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
            ArrayList<Integer> newList = new ArrayList<Integer>();
            lists.add(newList);
            for (int i = 0; i < nums.length; ++i) {
                if (nums[i] == 0) {
                    newList = new ArrayList<Integer>();
                    lists.add(newList);
                    continue;
                }

                newList.add(nums[i]);
            }

            int max = Integer.MIN_VALUE;
            for (ArrayList<Integer> list: lists) {
                int tmp = maxProduct(list);
                max = Math.max(max, tmp);
            }

            return max;
        }

        private int maxProduct(ArrayList<Integer> list) {
            if (list.size() == 1) return 1;
            else if (list.size() == 1) return list.get(0);

            int count = 0;
            int product = 1;
            for (int v: list) {
                if (v < 0) count++;
                product *= v;
            }

            if (count % 2 == 0) return product;

            int first = 1;
            for (int v: list) {
                if (v < 0) {
                    first *= v;
                    break;
                }
                first *= v;
            }

            int last = 1;
            for (int i = list.size() - 1; i >= 0; --i) {
                int v = list.get(i);
                if (v < 0) {
                    last *= v;
                    break;
                }
                last *= v;
            }

            int v = product / first;
            int w = product / last;
            return v > w ? v : w;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] array = {2, 3, -2, 4};

        System.out.println(s.maxProduct(array));
    }
}

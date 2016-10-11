import java.util.Random;

public class KthLargestElement {
    public static class Solution {
        /*
         * @param k : description of k
         * @param nums : array of nums
         * @return: description of return
         */
        public int kthLargestElement(int k, int[] nums) {

            // shuffle array

            int n = nums.length;
            return quickselect(0, n - 1, k - 1, nums);
        }

        private int quickselect(int start, int end, int k, int[] nums) {

            if (start == end) return nums[start];

            int mid = partition(start, end, nums);
            if (mid == k) return nums[k];
            else if (k < mid) return quickselect(start, mid - 1, k, nums);
            else return quickselect(mid + 1, end, k, nums);
        }

        private int partition(int start, int end, int[] nums) {
            int i = start, j = end + 1;
            int p = nums[start];

            while (i < j) {
                while (nums[++i] > p)
                    if (i == end) break;

                while (nums[--j] < p)
                    if (j == start) break;

                if (i < j) exch(i, j, nums);
                else break;
            }

            exch(start, j, nums);
            return j;
        }

        private void exch(int i, int j, int[] nums) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    };

    public static void main(String[] args) {
        int[] array = {9,3,2,4,8};
        Solution s = new Solution();
        System.out.println(s.kthLargestElement(3, array));

        int[] array2 = {1,2,3,4,5};
        System.out.println(s.kthLargestElement(1, array2));
        System.out.println(s.kthLargestElement(2, array2));
        System.out.println(s.kthLargestElement(3, array2));
    }
}

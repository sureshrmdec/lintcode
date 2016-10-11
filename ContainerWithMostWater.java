public class ContainerWithMostWater {
    // http://www.lintcode.com/en/problem/container-with-most-water/

    public class Solution {
        /**
         * @param heights: an array of integers
         * @return: an integer
         */
        public int maxArea(int[] heights) {

            if (heights.length <= 1) return 0;

            int left = 0, right = heights.length - 1;
            int ans = 0;
            while (left < right) {
                if (heights[left] < heights[right]) {
                    ans = Math.max(heights[left] * (right - left), ans);
                    left++;
                }
                else {
                    ans = Math.max(heights[right] * (right - left), ans);
                    right--;
                }
            }

            return ans;
        }
    }
}

public class TrappingRainWater {
    // Trapping Rain Water
    // http://www.lintcode.com/en/problem/trapping-rain-water/

    public class Solution {
        /**
         * @param heights: an array of integers
         * @return: a integer
         */

        public int trapRainWater(int[] heights) {
            int count = 0;
            if (heights == null || heights.length < 3) return 0;

            int left = 0, right = heights.length - 1;
            int min = 0;
            while (left < right) {
                if (heights[left] < heights[right]) {
                    if (heights[left] <= min)
                        count += min - heights[left++];
                    else
                        min = heights[left++];
                }
                else {
                    if (heights[right] <= min)
                        count += min - heights[right--];
                    else
                        min = heights[right--];
                }
            }

            return count;
        }
    }
}

public class HouseRobber {
    public class Solution {
        /**
         * @param A: An array of non-negative integers.
         * return: The maximum amount of money you can rob tonight
         */


        public long houseRobber(int[] array) {
            if (array.length == 0) return 0;

            long[] result = new long[array.length + 1];
            result[0] = 0;
            result[1] = array[0];

            for (int i = 2; i <= array.length; ++i)
                result[i] = Math.max(result[i - 2] + array[i - 1], result[i - 1]);

            return result[array.length];
        }
    }
}

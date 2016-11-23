class Singleton {
    public static class Solution {
        /**
         * @return: The same instance of this class every time
         */
        private static Solution solution = null;

        public static Solution getInstance() {
            if (solution == null) solution = new Solution();
            return solution;
        }
    };
}

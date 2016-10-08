public class WordSearch {
    // Word Search
    // http://www.lintcode.com/en/problem/word-search/
    public static class Solution {
        /**
         * @param board: A list of lists of character
         * @param word: A string
         * @return: A boolean
         */

        private int m, n;
        private String word;

        private boolean find(char[][] board, int i, int j, int start) {
            if (start == word.length()) return true;

            if (i < 0 || i == m || j < 0 || j == n) return false;

            if (word.charAt(start) != board[i][j]) return false;

            board[i][j] = '#';
            start++;

            boolean rst = find(board, i + 1, j, start) ||
                find(board, i - 1, j, start) ||
                find(board, i, j + 1, start) ||
                find(board, i, j - 1, start);

            board[i][j] = word.charAt(start - 1);
            return rst;
        }

        public boolean exist(char[][] board, String word) {
            if (board == null || board.length == 0 || word.length() == 0) return false;

            m = board.length;
            n = board[0].length;
            this.word = word;

            for (int i = 0; i < m; ++i)
                for (int j = 0; j < n; ++j) {
                    if (word.charAt(0) == board[i][j]) {
                        boolean rst = find(board, i, j, 0);
                        if (rst) return true;
                    }
                }

            return false;
        }
    }
}

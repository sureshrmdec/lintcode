import java.util.PriorityQueue;
import java.util.Comparator;

public class TrappingRainWaterII {
    // Trapping Rain Water II
    // http://www.lintcode.com/en/problem/trapping-rain-water-ii/#

    public static class Solution {
        /**
         * @param heights: a matrix of integers
         * @return: an integer
         */

        private class Cell {
            public int x, y, h;
            public Cell(int x, int y, int h) { this.x = x; this.y = y; this.h = h; }
        }

        private class CellComparator implements Comparator<Cell> {
            public int compare(Cell p, Cell q) { return p.h - q.h; }
        }

        public int trapRainWater(int[][] heights) {
            if (heights == null || heights.length == 0 || heights[0].length == 0) return 0;

            int m = heights.length;
            int n = heights[0].length;

            boolean[][] marked = new boolean[m][n];
            for (int i = 0; i < m; ++i)
                for (int j = 0; j < n; ++j)
                    marked[i][j] = false;

            PriorityQueue<Cell> queue = new PriorityQueue<Cell>(m + n, new CellComparator());
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                        queue.add(new Cell(i, j, heights[i][j]));
                        marked[i][j] = true;
                    }
                }
            }

            int[] x = {-1, 1, 0, 0};
            int[] y = {0, 0, -1, 1};
            int count = 0;
            int min = queue.peek().h;
            while (!queue.isEmpty()) {
                Cell cell = queue.poll();
                for (int i = 0; i < 4; ++i) {
                    if (cell.x + x[i] < 0 || cell.x + x[i] >= m || cell.y + y[i] < 0 || cell.y + y[i] >= n)
                        continue;
                    if (marked[cell.x + x[i]][cell.y + y[i]])
                        continue;
                    int h = heights[cell.x + x[i]][cell.y + y[i]];
                    if (cell.h > h) {
                        count += cell.h - h;
                        queue.add(new Cell(cell.x + x[i], cell.y + y[i], cell.h));
                    }
                    else
                        queue.add(new Cell(cell.x + x[i], cell.y + y[i], h));
                    marked[cell.x + x[i]][cell.y + y[i]] = true;
                }
            }

            return count;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] matrix = {
            {12,13,0,12},
            {13,4,13,12},
            {13,8,10,12},
            {12,13,12,12},
            {13,13,13,13}
        };
        System.out.println(s.trapRainWater(matrix));
    }
}

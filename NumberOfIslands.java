import java.util.HashSet;

public class NumberOfIslands {
    // Number of Islands
    // http://www.lintcode.com/en/problem/number-of-islands/

    public static class Solution {

        private class UF {
            private int[] A;

            public UF(int capacity) {
                A = new int[capacity];
                for (int i = 0; i < capacity; ++i)
                    A[i] = i;
            }

            public void union(int p, int q) {
                A[root(p)] = root(q);
            }

            public int find(int p) { return root(p); }
            
            public boolean isConnected(int p, int q) { return root(p) == root(q); }

            private int root(int p) {
                int next = p;
                while (next != A[next])
                    next = A[next];

                int root = next;
                next = p;
                while (next != A[next]) {
                    A[next] = root;
                    next = A[next];
                }
                
                return root;
            }
        }

        public int numIslands(boolean[][] grid) {
            if (grid.length == 0) return 0;

            int m = grid.length;
            int n = grid[0].length;
            UF uf = new UF(m * n);

            for (int i = 0; i < m; ++i)
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j]) {
                        // right
                        if (j + 1 < n && grid[i][j + 1]) uf.union(i * n + j, i * n + j + 1);

                        // top
                        if (i - 1 >= 0 && grid[i - 1][j]) uf.union(i * n + j, (i - 1) * n + j);
                    }
                }

            HashSet<Integer> set = new HashSet<Integer>();
            for (int i = 0; i < m; ++i)
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j]) {
                        int root = uf.find(i * n + j);
                        if (!set.contains(root)) set.add(root);
                    }
                }

            return set.size();
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        boolean[][] matrix = 
        {
            {true, true, false, false, false},
            {false, true, false, false, true},
            {false, false, false, true, true},
            {false, false, false, false, false},
            {false, false, false, false, true}
        };
        
        System.out.println("Number of islands is: " + s.numIslands(matrix));

    }
}

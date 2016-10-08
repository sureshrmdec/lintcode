import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;

public class NumberOfIslandsII {
     // Definition for a point.
     private class Point {
         int x;
         int y;
         Point() { x = 0; y = 0; }
         Point(int a, int b) { x = a; y = b; }
     }
     
    public static class Solution {
        /**
         * @param n an integer
         * @param m an integer
         * @param operators an array of point
         * @return an integer array
         */

        private class UF {
            private int[] A;
            private int merges;

            public UF(int size) {
                A = new int[size];
                merges = 0;

                for (int i = 0; i < size; ++i)
                    A[i] = i;
            }

            public void union(int i, int j) {
                if (root(i) != root(j)) merges++;
                A[root(i)] = root(j);
            }

            public int root(int p) {
                int tmp = p;
                while (p != A[p])
                    p = A[p];
                int root = p;

                p = tmp;
                while (p != A[p]) {
                    tmp = A[p];
                    A[p] = root;
                    p = tmp;
                }
                return root;
            }

            public int numberOfMerges() { return merges; }
        }

        public List<Integer> numIslands2(int m, int n, Point[] operators) {
            ArrayList<Integer> results = new ArrayList<Integer>();
            if (operators == null) return results;

            HashSet<Integer> set = new HashSet<Integer>();
            UF uf = new UF(m * n);
            
            // up, down, left, right
            int[] x = {0, 0, -1, 1};
            int[] y = {-1, 1, 0, 0};
            for (int i = 0; i < operators.length; ++i) {
                Point p = operators[i];
                set.add(p.x * n + p.y);
                for (int j = 0; j < 4; ++j) {
                    if (p.x + x[j] >= 0 && p.x + x[j] < m && p.y + y[j] >= 0 && p.y + y[j] < n)
                        if (set.contains((p.x + x[j]) * n + (p.y + y[j])))
                            uf.union(p.x * n + p.y, (p.x + x[j]) * n + (p.y + y[j]));
                }

                results.add(set.size() - uf.numberOfMerges());
            }
            return results;
        }
    }
}

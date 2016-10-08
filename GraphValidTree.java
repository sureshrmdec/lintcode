import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GraphValidTree {
    // Graph Valid Tree
    // http://www.lintcode.com/en/problem/graph-valid-tree/

    public static class Solution {
        /**
         * @param n an integer
         * @param edges a list of undirected edges
         * @return true if it's a valid tree, or false
         */

        public class UndirectedGraph {
            private HashMap<Integer, ArrayList<Integer>> hashMap;

            public UndirectedGraph(int n, int[][] edges) {
                hashMap = new HashMap<Integer, ArrayList<Integer>>();
                for (int i = 0; i < n; ++i)
                    hashMap.put(i, new ArrayList<Integer>());
                for (int i = 0; i < edges.length; ++i) {
                    hashMap.get(edges[i][0]).add(edges[i][1]);
                    hashMap.get(edges[i][1]).add(edges[i][0]);
                }
            }

            public ArrayList<Integer> edges(int v) { return hashMap.get(v); }
        }

        private HashSet<Integer> marked = new HashSet<Integer>();
        private HashMap<Integer, HashSet<Integer>> paths = new HashMap<Integer, HashSet<Integer>>();

        private void addEdge(int v, int w) {
            if (paths.containsKey(v))
                paths.get(v).add(w);
            else {
                HashSet<Integer> n2 = new HashSet<Integer>();
                n2.add(w);
                paths.put(v, n2);
            }
        }

        private boolean isEdgeVisited(int v, int w) {
            if (paths.containsKey(v)) 
                return paths.get(v).contains(w);
            else
                return false;
        }

        public boolean validTree(int n, int[][] edges) {

            UndirectedGraph g = new UndirectedGraph(n, edges);

            ArrayDeque<Integer> stack = new ArrayDeque<Integer>();
            stack.addFirst(0);
            marked.add(0);

            while (!stack.isEmpty()) {
                int v = stack.poll();
                for (int w: g.edges(v)) {
                    if (isEdgeVisited(v, w))
                        continue;
                    else {
                        // has cycle
                        if (marked.contains(w)) return false;
                        else marked.add(w);

                        addEdge(v, w);
                        addEdge(w, v);
                        stack.addFirst(w);
                    }
                }
            }

            // not fully connected
            return marked.size() == n;
        }
    }

    public static void main(String[] args) {
    }
}

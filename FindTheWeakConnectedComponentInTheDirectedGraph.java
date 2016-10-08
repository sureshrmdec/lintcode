import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FindTheWeakConnectedComponentInTheDirectedGraph {

    // Definition for Directed graph.
    public class DirectedGraphNode {
        int label;
        ArrayList<DirectedGraphNode> neighbors;
        DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
    };

    public static class Solution {
        /**
         * @param nodes a array of Directed graph node
         * @return a connected set of a directed graph
         */

        private class UF {
            private int[] A;
            private HashMap<Integer, Integer> label2Index = new HashMap<Integer, Integer>();

            public UF(ArrayList<DirectedGraphNode> nodes) { 
                A = new int[nodes.size()];
                for (int i = 0; i < nodes.size(); ++i)
                    A[i] = i;

                int i = 0;
                for (DirectedGraphNode node: nodes) {
                    label2Index.put(node.label, i);
                    i++;
                }

                for (DirectedGraphNode node: nodes) {
                    for (DirectedGraphNode neighbor: node.neighbors) {
                        union(label2Index.get(node.label), label2Index.get(neighbor.label));
                    }
                }
            }

            public void union(int i, int j) { A[root(i)] = root(j); }
            public int find(int label) { return root(label2Index.get(label)); }

            private int root(int p) {
                int root = p, tmp = p;
                while (p != A[p])
                    p = A[p];

                root = p;
                p = tmp;

                while (p != A[p]) {
                    tmp = A[p];
                    A[p] = root;
                    p = tmp;
                }
                return root;
            }
        }

        public List<List<Integer>> connectedSet2(ArrayList<DirectedGraphNode> nodes) {

            UF uf = new UF(nodes);

            HashMap<Integer, ArrayList<Integer>> hashmap = new HashMap<Integer, ArrayList<Integer>>();
            for (DirectedGraphNode node: nodes) {
                int componentId = uf.find(node.label);
                if (!hashmap.containsKey(componentId))
                    hashmap.put(componentId, new ArrayList<Integer>());
                hashmap.get(componentId).add(node.label);
            }

            List<List<Integer>> results = new ArrayList<List<Integer>>();
            for (ArrayList<Integer> set: hashmap.values()) {
                results.add(set);
            }

            return results;
        }
    }
}

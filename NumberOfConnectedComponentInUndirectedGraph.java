import java.util.Comparator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Collections;

public class NumberOfConnectedComponentInUndirectedGraph {

    // Find the Connected Component in the Undirected Graph
    // http://www.lintcode.com/en/problem/find-the-connected-component-in-the-undirected-graph/#

    /**
     * Definition for Undirected graph.
     * class UndirectedGraphNode {
     *     int label;
     *     ArrayList<UndirectedGraphNode> neighbors;
     *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
     * };
     */

    public class UndirectedGraphNode {
        int label;
        ArrayList<UndirectedGraphNode> neighbors;
        UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
    };

    public static class Solution {
        private class UF {
            private int[] A;
            
            public UF(int size) {
                A = new int[size];
                for (int i = 0; i < size; ++i)
                    A[i] = i;
            }

            public int find(int p) { return root(p); }

            public void union(int p, int q) {
                A[root(p)] = root(q);
            } 

            private int root(int p) {
                int next = p, root;
                while (p != A[p])
                    p = A[p];
                root = p;
                while (next != A[next]) {
                    A[next] = root;
                    next = A[next];
                }
                return root;
            }
        }

        public List<List<Integer>> connectedSet(ArrayList<UndirectedGraphNode> nodes) {
            HashMap<Integer, Integer> labelNodeIdMap = new HashMap<Integer, Integer>();
            HashMap<Integer, Integer> nodeIdLabelMap = new HashMap<Integer, Integer>();
            int i = 0;
            for (UndirectedGraphNode node: nodes) {
                nodeIdLabelMap.put(i, node.label);
                labelNodeIdMap.put(node.label, i++);
            }

            UF uf = new UF(nodes.size());
            for (UndirectedGraphNode node: nodes)
                for (UndirectedGraphNode neighbor: node.neighbors)
                    uf.union(labelNodeIdMap.get(node.label), labelNodeIdMap.get(neighbor.label));

            HashMap<Integer, ArrayList<Integer>> rootLabelMap = new HashMap<Integer, ArrayList<Integer>>();
            // could also be ArrayList<List<...>>. The inner List has to be matched.
            List<List<Integer>> results = new ArrayList<List<Integer>>();

            for (UndirectedGraphNode node: nodes) {
                int nodeId = labelNodeIdMap.get(node.label);
                int root = uf.find(nodeId);
                if (rootLabelMap.containsKey(root)) {
                    ArrayList<Integer> labels = rootLabelMap.get(root);
                    labels.add(node.label);
                } else {
                    ArrayList<Integer> labels = new ArrayList<Integer>();
                    labels.add(node.label);
                    rootLabelMap.put(root, labels);
                    results.add(labels);
                }
            }

            for (List<Integer> labels: results)
                Collections.sort(labels);

            return results;
        }
    }

    public static void main(String[] args) {
    }
}

/**
 * Definition of TrieNode:
 * public class TrieNode {
 *     public NavigableMap<Character, TrieNode> children;
 *     public List<Integer> top10;
 *     public TrieNode() {
 *         children = new TreeMap<Character, TrieNode>();
 *         List<Integer> top10 = new ArrayList<Integer>();
 *     }
 * }
 */

import java.util.*;

public class TrieService {
    public class TrieNode {
        public NavigableMap<Character, TrieNode> children;
        public List<Integer> top10;
        public TrieNode() {
            children = new TreeMap<Character, TrieNode>();
            List<Integer> top10 = new ArrayList<Integer>();
        }
    }

    private TrieNode root = null;

    public TrieService() {
        root = new TrieNode();
    }

    public TrieNode getRoot() {
        // Return root of trie root, and 
        // lintcode will print the tree struct.
        return root;
    }

    // @param word a string
    // @param frequency an integer
    public void insert(String word, int frequency) {

        TrieNode node = getRoot();
        for (char c: word.toCharArray()) {
            if (!node.children.containsKey(c))
                node.children.put(c, new TrieNode());

            node = node.children.get(c);
            List<Integer> top10 = node.top10;
            update(top10, frequency);
        }
    }

    private void update(List<Integer> list, int f) {
        if (list.isEmpty()) {
            list.add(f);
            return;
        }

        if (list.size() < 10 || f > list.get(list.size() - 1))
            list.add(f);

        int i = list.size() - 1;
        while (i >= 1 && list.get(i) > list.get(i - 1)) {
            int tmp = list.get(i - 1);
            list.set(i - 1, list.get(i));
            list.set(i, tmp);
            i--;
        }
    }
 }

import java.util.*;

public class TrieSerialization {
    /**
     * Definition of TrieNode:
     * public class TrieNode {
     *     public NavigableMap<Character, TrieNode> children;
     *     public TrieNode() {
     *         children = new TreeMap<Character, TrieNode>();
     *     }
     * }
     */

    private class TrieNode {
        public NavigableMap<Character, TrieNode> children;
        public TrieNode() {
            children = new TreeMap<Character, TrieNode>();
        }
    }

    class Solution {
        /**
         * This method will be invoked first, you should design your own algorithm 
         * to serialize a trie which denote by a root node to a string which
         * can be easily deserialized by your own "deserialize" method later.
         */

        private List<String> words = new ArrayList<String>();

        public String serialize(TrieNode root) {
            if (root == null) return "";

            dfs(root, "");

            StringBuilder sb = new StringBuilder();
            for (String word: words) {
                sb.append(word + " ");
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }

        private void dfs(TrieNode node, String str) {
            if (node.children.isEmpty()) {
                words.add(str);
                return;
            }

            for (char c: node.children.keySet()) {
                TrieNode child = node.children.get(c);
                String s = str + c;
                dfs(child, s);
            }
        }

        /**
         * This method will be invoked second, the argument data is what exactly
         * you serialized at method "serialize", that means the data is not given by
         * system, it's given by your own serialize method. So the format of data is
         * designed by yourself, and deserialize it here as you serialize it in 
         * "serialize" method.
         */
        public TrieNode deserialize(String data) {
            String[] words = data.split(" ");
            TrieNode root = new TrieNode();
            for (String word: words) {
                insert(root, word);
            }

            return root;
        }

        private void insert(TrieNode root, String word) {
            TrieNode node = root;
            for (char c: word.toCharArray()) {
                if (!node.children.containsKey(c)) {
                    node.children.put(c, new TrieNode());
                }

                node = node.children.get(c);
            }
        }
    }
}

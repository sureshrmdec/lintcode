import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.HashSet;

public class WordSearchII {
    // Word Search II
    // http://www.lintcode.com/en/problem/word-search-ii/

    public static class Solution {
        /**
         * @param board: A list of lists of character
         * @param words: A list of string
         * @return: A list of string
         */

        private class TrieNode {
            public HashMap<Character, TrieNode> nodes;
            boolean isWord;

            public TrieNode() { nodes = new HashMap<Character, TrieNode>(); }
        }

        private class Trie {
            public TrieNode root;

            public Trie() {
                root = new TrieNode();
            }

            public void addWord(String word) {
                TrieNode node = root;
                for (int i = 0; i < word.length(); ++i) {
                    char c = word.charAt(i);
                    if (!node.nodes.containsKey(c))
                        node.nodes.put(c, new TrieNode());

                    node = node.nodes.get(c);
                }
                node.isWord = true;
            }
        }

        private int m, n;
        private ArrayList<String> results;
        private HashSet<String> set;
        private int[] x = {-1, 1, 0, 0};
        private int[] y = {0, 0, -1, 1};

        public ArrayList<String> wordSearchII(char[][] board, ArrayList<String> words) {
            results = new ArrayList<String>();
            set = new HashSet<String>();
            if (board == null || board.length == 0 || words == null || words.size() == 0) return results;

            m = board.length;
            n = board[0].length;

            Trie trie = new Trie();
            for (String word: words)
                trie.addWord(word);

            Iterator it = trie.root.nodes.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                char c = (char)pair.getKey();
                TrieNode node = (TrieNode)pair.getValue();
                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < m; ++i) {
                    for (int j = 0; j < n; ++j) {
                        find(node, c, board, i, j, sb);
                    }
                }
            }

            for (String s: set)
                results.add(s);

            return results;
        }

        private void find(TrieNode node, char c, char[][] board, int i, int j, StringBuilder sb) {
            if (i < 0 || i == m || j < 0 || j == n) return;

            //System.out.println("find: " + node + ", " + c + ", " + i + ", " + j + ", " + sb.toString());
            if (board[i][j] == c) {
                board[i][j] = '$';
                traverse(node, c, board, i, j, sb);
                board[i][j] = c;
            }
        }

        private void traverse(TrieNode node, char c, char[][] board, int i, int j, StringBuilder sb) {

            //System.out.println("traverse: " + node + ", " + c + ", " + i + ", " + j + ", " + sb.toString());
            assert (c == board[i][j]);

            sb.append(c);
            if (node.isWord) { 
                //System.out.println("isWord: " + node + ", " + c + ", " + i + ", " + j + ", " + sb.toString());
                set.add(sb.toString());
            }

            for (char childChar: node.nodes.keySet()) {
                TrieNode childNode = node.nodes.get(childChar);
                for (int k = 0; k < 4; ++k) find(childNode, childChar, board, i + x[k], j + y[k], sb);
            }

            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {

        // testcase 1
        //String[] input1 = { "doaf","agai","dcan" };
        //char[][] board = new char[3][4];
        //for (int i = 0; i < board.length; ++i)
            //for (int j = 0; j < board[0].length; ++j)
                //board[i][j] = input1[i].charAt(j);

        //String[] input2 = {"dog","dad","dgdg","can","again"};
        //ArrayList<String> words = new ArrayList<String>();
        //for (int i = 0; i < input2.length; ++i)
            //words.add(input2[i]);

        //Solution s = new Solution();
        //ArrayList<String> results = s.wordSearchII(board, words);
        //for (String str: results)
            //System.out.println(str);

        // testcase 2
        String[] input11 = {"b","a","b","b","a"};
        char[][] board2 = new char[5][1];
        for (int i = 0; i < board2.length; ++i)
            for (int j = 0; j < board2[0].length; ++j)
                board2[i][j] = input11[i].charAt(j);

        //String[] input22 = {"babbab","b","a","ba"};
        String[] input22 = {"babbab"};
        ArrayList<String> words2 = new ArrayList<String>();
        for (int i = 0; i < input22.length; ++i)
            words2.add(input22[i]);

        Solution s = new Solution();
        ArrayList<String> results2 = s.wordSearchII(board2, words2);
        for (String str: results2)
            System.out.println(str);
    }
}

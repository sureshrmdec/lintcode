/**
 * Your Trie object will be instantiated and called as such:
 * Trie trie = new Trie();
 * trie.insert("lintcode");
 * trie.search("lint"); will return false
 * trie.startsWith("lint"); will return true
 */

import java.util.*;

class TrieNode {
    public Map<Character, TrieNode> children;
    boolean isWord;
    public TrieNode() {
        children = new HashMap<Character, TrieNode>();
        isWord = false;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode node = root;
        for (char c: word.toCharArray()) {
            if (!node.children.containsKey(c))
                node.children.put(c, new TrieNode());

            node = node.children.get(c);
        }
        node.isWord = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode node = root;
        for (char c: word.toCharArray()) {
            if (!node.children.containsKey(c)) return false;

            node = node.children.get(c);
        }
        return node.isWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c: prefix.toCharArray()) {
            if (!node.children.containsKey(c)) return false;

            node = node.children.get(c);
        }
        return true;
    }
}

import java.util.ArrayList;

public class WordDictionary {

    // Add and Search Word
    // http://www.lintcode.com/en/problem/add-and-search-word/

    private class Node {
        public Node[] links;
        public boolean isWord;

        public Node() {
            links = new Node[26];
            isWord = false;
        }
    }

    private Node root;

    public WordDictionary() {
        root = new Node();
    }

    // Adds a word into the data structure.
    public void addWord(String word) {
        Node now = root;
        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            if (now.links[c - 'a'] == null) {
                now.links[c - 'a'] = new Node();
            }
            now = now.links[c - 'a'];
        }
        now.isWord = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return search(word, 0, root);
    }

    private boolean search(String word, int start, Node node) {
        if (node == null) return false;

        for (int i = start; i < word.length(); ++i) {
            char c = word.charAt(i);
            if (c != '.') {
                if (node.links[c - 'a'] == null) return false;
                else node = node.links[c - 'a'];
            }
            else {
                for (int j = 0; j < 26; ++j)
                    if (search(word, i + 1, node.links[j]))
                        return true;
                
                return false;
            }
        }

        return node.isWord;
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");

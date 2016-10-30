import java.util.*;


public class Typeahead {
    private HashMap<String, HashSet<String>> map;

    private void insert(String sub, String word) {
        if (!map.containsKey(sub)) map.put(sub, new HashSet<String>());

        map.get(sub).add(word);
    }

    public Typeahead(Set<String> dict) {
        map = new HashMap<String, HashSet<String>>();

        for (String word: dict) {
            if (map.containsKey(word) && map.get(word).contains(word)) continue;

            for (int i = 0; i < word.length(); ++i) {
                for (int j = i; j < word.length(); ++j) {
                    insert(word.substring(i, j + 1), word);
                }
            }
        }
    }

    public List<String> search(String str) {
        if (!map.containsKey(str)) return new ArrayList<String>();

        return new ArrayList<String>(map.get(str));
    }
}

class Typeahead2 {

    private class TrieNode {
        public Map<Character, TrieNode> children;
        public Set<String> words;
        public TrieNode() {
            children = new HashMap<Character, TrieNode>();
            words = new HashSet<String>();
        }
    }

    private void insert(String sub, String word) {
        TrieNode node = root;
        for (char c: sub.toCharArray()) {
            if (!node.children.containsKey(c))
                node.children.put(c, new TrieNode());

            node = node.children.get(c);
            node.words.add(word);
        }
    }

    private TrieNode root;
    // @param dict A dictionary of words dict
    public Typeahead2(Set<String> dict) {
        root = new TrieNode();
        for (String word: dict) {
            for (int i = 0; i < word.length(); ++i) {
                for (int j = i; j < word.length(); ++j) {
                    insert(word.substring(i, j + 1), word);
                }
            }
        }
    }

    // @param str: a string
    // @return a list of words
    public List<String> search(String str) {
        List<String> result = new ArrayList<String>();
        TrieNode node = root;
        for (char c: str.toCharArray()) {
            if (!node.children.containsKey(c)) return result;

            node = node.children.get(c);
        }

        for (String word: node.words)
            result.add(word);

        return result;
    }
}

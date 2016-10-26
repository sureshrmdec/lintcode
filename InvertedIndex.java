import java.util.*;

public class InvertedIndex {
    
    //Definition of Document:
    class Document {
        public int id;
        public String content;
    }
    
    public class Solution {
        /**
         * @param docs a list of documents
         * @return an inverted index
         */

        public Map<String, List<Integer>> invertedIndex(List<Document> docs) {
            Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();

            for (Document doc: docs) {
                String[] words = doc.content.split(" ");

                for (String word: words) {
                    if (word.isEmpty()) continue;

                    if (!map.containsKey(word)) map.put(word, new ArrayList<Integer>());

                    List<Integer> list = map.get(word);
                    if (!list.contains(doc.id)) list.add(doc.id);
                }
            }

            return map;
        }
    }
}

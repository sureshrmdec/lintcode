/**
 * Definition of OutputCollector:
 * class OutputCollector<K, V> {
 *     public void collect(K key, V value);
 *         // Adds a key/value pair to the output buffer
 * }
 * Definition of Document:
 * class Document {
 *     public int id;
 *     public String content;
 * }
 */
 public class TopKFrequentWords {

    private static class Node {
        public String key;
        public int count;
        public Node(String k, int c) { key = k; count = c; }
    }

    private static class NodeComparator implements Comparator<Node> {
        public int compare(Node v, Node w) {
            if (v.count < w.count) return -1;
            else if (v.count > w.count) return 1;
            else return w.key.compareTo(v.key);
        }
    }

    public static class Map {
        public void map(String _, Document value,
                        OutputCollector<String, Integer> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, int value);
            //
            String[] words = value.content.split(" ");
            for (String word: words)
                output.collect(word, 1);
        }
    }

    public static class Reduce {

        private int K;
        private java.util.Map<String, Integer> map;

        public void setup(int k) {
            K = k;
            map = new TreeMap<String, Integer>();
        }   

        public void reduce(String key, Iterator<Integer> values) {
            if (key.length() == 0) return;
            if (!map.containsKey(key)) map.put(key, 0);
            while (values.hasNext())
                map.put(key, map.get(key) + values.next());
        }

        public void cleanup(OutputCollector<String, Integer> output) {
            // Output the top k pairs <word, times> into output buffer.
            // Ps. output.collect(String key, Integer value);

            PriorityQueue<Node> queue = new PriorityQueue<Node>(1, new NodeComparator());

            for (String key: map.keySet()) {
                queue.add(new Node(key, map.get(key)));
            }

            LinkedList<String> sorted = new LinkedList<String>();
            while (!queue.isEmpty()) {
                Node node = queue.poll();
                sorted.addFirst(node.key);
            }

            int count = 0;
            for (String key: sorted) {
                if (++count <= K)
                    output.collect(key, map.get(key));
                else break;
            }
        }
    }
}

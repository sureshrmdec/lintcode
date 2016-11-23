//Definition of OutputCollector:
class OutputCollector<K, V> {
    public void collect(K key, V value);
    // Adds a key/value pair to the output buffer
}

public class Anagram {

    public static class Map {
        public void map(String key, String value,
                OutputCollector<String, String> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, String value);

            String[] words = value.split(" ");
            for (String word: words) {
                char[] str = word.toCharArray();
                Arrays.sort(str);
                output.collect(new String(str), word);
            }
        }
    }

    public static class Reduce {
        public void reduce(String key, Iterator<String> values,
                OutputCollector<String, List<String>> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, List<String> value);

            List<String> list = new LinkedList<String>();
            while (values.hasNext()) {
                list.add(values.next());
            }

            output.collect(key, list);
        }
    }
}

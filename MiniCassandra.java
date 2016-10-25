import java.util.*;


// Definition of Column:

public class MiniCassandra {
    public class Column {
        public int key;
        public String value;
        public Column(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private HashMap<String, TreeMap<Integer, String>> map;

    public MiniCassandra() {
        map = new HashMap<String, TreeMap<Integer, String>>();
    }

    /**
     * @param raw_key a string
     * @param column_start an integer
     * @param column_end an integer
     * @return void
     */
    public void insert(String raw_key, int column_key, String column_value) {
        if (!map.containsKey(raw_key))
            map.put(raw_key, new TreeMap<Integer, String>());

        TreeMap<Integer, String> columns = map.get(raw_key);
        columns.put(column_key, column_value);
    }

    /**
     * @param raw_key a string
     * @param column_start an integer
     * @param column_end an integer
     * @return a list of Columns
     */
    public List<Column> query(String raw_key, int column_start, int column_end) {
        List<Column> result = new ArrayList<Column>();
        if (!map.containsKey(raw_key)) return result;

        TreeMap<Integer, String> columns = map.get(raw_key);
        for (int key: columns.subMap(column_start, true, column_end, true).keySet()) {
            result.add(new Column(key, columns.get(key)));
        }

        return result;
    }
}

import java.util.*;

public class Memcache {

    private HashMap<Integer, Node> cache;

    private class Node {
        public int value, expire;
        public Node(int v, int e) { value = v; expire = e; }
    }

    public Memcache() {
        cache = new HashMap<Integer, Node>();
    }

    public int get(int curtTime, int key) {
        return update(curtTime, key, 0);
    }

    public void set(int curtTime, int key, int value, int ttl) {
        if (!cache.containsKey(key)) cache.put(key, new Node(value, ttl == 0 ? 0 : curtTime + ttl - 1));
        Node node = cache.get(key);
        if (node.expire == 0 || node.expire >= curtTime) {
            node.expire = ttl == 0 ? 0 : curtTime + ttl - 1;
            node.value = value;
        }
        else
            cache.remove(key);
    }

    public void delete(int curtTime, int key) {
        cache.remove(key);
    }

    public int incr(int curtTime, int key, int delta) {
        return update(curtTime, key, delta);
    }

    public int decr(int curtTime, int key, int delta) {
        return update(curtTime, key, -delta);
    }
    
    private int update(int curtTime, int key, int delta) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            if (node.expire == 0 || node.expire >= curtTime) {
                node.value = node.value + delta;
                return node.value;
            }
            else
                cache.remove(key);
        }

        return 2147483647;
    }

    public static void main(String[] args) {
    }
}

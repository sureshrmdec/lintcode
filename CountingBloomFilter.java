import java.util.*;

public class CountingBloomFilter {

    private class HashFunction {
        private int cap, seed;
        public HashFunction(int c, int s) { cap = c; seed = s; }

        public int hash(String word) {
            int ret = 0;
            int n = word.length();
            for (int i = 0; i < n; ++i) {
                ret += seed * ret + word.charAt(i);
                ret = ret % cap;
            }
            return ret;
        }
    }

    private static final int SIZE = 100000;
    private int K;
    private int[] bits;
    private List<HashFunction> functions;

    public CountingBloomFilter(int k) {
        K = k;
        bits = new int[SIZE + k];
        functions = new ArrayList<HashFunction>();
        for (int i = 0; i < k; ++i) {
            functions.add(new HashFunction(SIZE + k, 2 * i + 3));
        }
    }

    public void add(String word) {
        for (int i = 0; i < K; ++i) {
            int hash = functions.get(i).hash(word);
            bits[hash] += 1;
        }
    }

    public void remove(String word) {
        // word has to exist. Otherwise, remove non-existing words can lead to existing word
        // got deleted and then break bloom filter's contract
        for (int i = 0; i < K; ++i) {
            int hash = functions.get(i).hash(word);
            bits[hash] -= 1;
        }
    }

    public boolean contains(String word) {
        for (int i = 0; i < K; ++i) {
            int hash = functions.get(i).hash(word);
            if (bits[hash] <= 0) return false;
        }

        return true;
    }
}

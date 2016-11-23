import java.util.HashSet;
import java.math.BigInteger;


// http://willwhim.wpengine.com/2011/09/03/producing-n-hash-functions-by-hashing-only-once/
//
// https://github.com/jakedouglas/fnv-java

public class StandardBloomFilter {

    private static final BigInteger INIT32  = new BigInteger("811c9dc5",         16);
    private static final BigInteger INIT64  = new BigInteger("cbf29ce484222325", 16);
    private static final BigInteger PRIME32 = new BigInteger("01000193",         16);
    private static final BigInteger PRIME64 = new BigInteger("100000001b3",      16);
    private static final BigInteger MOD32   = new BigInteger("2").pow(32);
    private static final BigInteger MOD64   = new BigInteger("2").pow(64);


    private final int K;
    private HashSet<Integer> set;
    public StandardBloomFilter(int k) {
        K = k;
        set = new HashSet<Integer>();
    }

    public void add(String word) {
        BigInteger hash = fnv1a_64(word.getBytes());
        set.add(hash.intValue());
        for (int i = 1; i < K; ++i) {
            BigInteger a = hash.shiftRight(32).and(BigInteger.valueOf(0xffffffff));
            BigInteger b = hash.and(BigInteger.valueOf(0xff));
            hash = a.add(b.multiply(BigInteger.valueOf(i))).mod(MOD32);
            set.add(hash.intValue());
        }
    }

    public boolean contains(String word) {
        BigInteger hash = fnv1a_64(word.getBytes());
        if (!set.contains(hash.intValue())) return false;

        for (int i = 1; i < K; ++i) {
            BigInteger a = hash.shiftRight(32).and(BigInteger.valueOf(0xffffffff));
            BigInteger b = hash.and(BigInteger.valueOf(0xff));
            hash = a.add(b.multiply(BigInteger.valueOf(i))).mod(MOD32);
            if (!set.contains(hash.intValue())) return false;
        }
        return true;
    }

    private BigInteger fnv1a_64(byte[] data) {
        BigInteger hash = INIT64;

        for (byte b: data) {
            hash = hash.xor(BigInteger.valueOf((int) b & 0xff));
            hash = hash.multiply(PRIME64).mod(MOD64);
        }
        return hash;
    }

    public static void main(String[] args) {
        BigInteger n = new BigInteger("1234567890abcdef", 16);
        System.out.println(n.shiftRight(32).and(BigInteger.valueOf(0xffffffff)).toString(16));
    }
}

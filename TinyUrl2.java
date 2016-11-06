import java.util.*;

public class TinyUrl2 {
    /**
     * @param long_url a long url
     * @param a short key
     * @return a short url starts with http://tiny.url/
     */

    private static final String dict = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private Map<String, String> long2Short;
    private Map<String, String> short2Long;
    private static int count;

    private static final String PREFIX = "http://tiny.url/";
    public TinyUrl2() {
        long2Short = new HashMap<String, String>();
        short2Long = new HashMap<String, String>();
        count = 0;
    }

    String createCustom(String long_url, String short_key) {
        String url = PREFIX + short_key;
        if (long2Short.containsKey(long_url) && long2Short.get(long_url).equals(url))
            return url;

        if (long2Short.containsKey(long_url) || short2Long.containsKey(url))
            return "error";

        long2Short.put(long_url, url);
        short2Long.put(url, long_url);
        return url;
    }

    /**
     * @param long_url a long url
     * @return a short url starts with http://tiny.url/
     */
    public String longToShort(String long_url) {
        if (long2Short.containsKey(long_url))
            return long2Short.get(long_url);

        String url = PREFIX + base62(count++);
        while (short2Long.containsKey(url))
            url = PREFIX + base62(count++);

        long2Short.put(long_url, url);
        short2Long.put(url, long_url);
        return url;
    }

    /**
     * @param short_url a short url starts with http://tiny.url/
     * @return a long url
     */
    public String shortToLong(String short_url) {
        return short2Long.get(short_url);
    }

    private String base62(int num) {
        char[] array = {'a','a','a','a','a','a'};

        int i = 5;
        while (num != 0) {
            char c = dict.charAt(num % 62);
            array[i--] = c;
            num = num / 62;
        }

        return new String(array);
    }
}

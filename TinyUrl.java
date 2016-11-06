import java.util.*;

public class TinyUrl {
    /**
     * @param url a long url
     * @return a short url starts with http://tiny.url/
     */
    private static int count = 0;
    private static String dict = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static String prefix = "http://tiny.url/"
    private Map<String, String> long2Short = new HashMap<String, String>();
    private Map<String, String> short2Long = new HashMap<String, String>();

    public String longToShort(String url) {
        if (long2Short.containsKey(url)) return long2Short.get(url);

        String shortUrl = prefix + base62(count);
        long2Short.put(url, shortUrl);
        short2Long.put(shortUrl, url);
        count++;
        return shortUrl;
    }

    /**
     * @param url a short url starts with http://tiny.url/
     * @return a long url
     */
    public String shortToLong(String url) {
        return short2Long.get(url);
    }

    private String base62(int num) {
        char[] array = {'a', 'a', 'a', 'a', 'a', 'a'};

        int i = 5;
        while (num != 0) {
            char c = dict.charAt(num % 62);
            array[i--] = c;
            num = num / 62;
        }
        return new String(array);
    }

    public static void main(String[] args) {
        for (char c = 'a'; c <= 'z'; c++)
            System.out.print(c);

        for (char c = 'A'; c <= 'Z'; c++)
            System.out.print(c);

        for (char c = '0'; c <= '9'; c++)
            System.out.print(c);

        System.out.println();
    }
}

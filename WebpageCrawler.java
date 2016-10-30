/**
 * public class HtmlHelper {
 *     public static List<String> parseUrls(String url);
 *         // Get all urls from a webpage of given url. 
 * }
*/

import java.net.URL;
import java.net.MalformedURLException;
import java.util.*;
import java.util.concurrent.*;

// sleep version
class WebpageCrawler2 {
    static public class HtmlHelper {
        public static List<String> parseUrls(String url) { return new ArrayList<String>(); }
        // Get all urls from a webpage of given url. 
    }

    static private class CrawlerThread extends Thread {
        public static BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
        public static HashSet<String> set = new HashSet<String>();
        public static List<String> result = new ArrayList<String>();
        public static String root;

        public CrawlerThread() {
        }

        public void run() {
            while (true) {
                if (queue.isEmpty()) {
                    try {
                        Thread.sleep(100);
                    }
                    catch (InterruptedException e) {
                    }
                    continue;
                }
                String url = queue.poll();
                if (set.contains(url)) continue;
                try {
                    URL u = new URL(url);
                    if (!u.getHost().endsWith("wikipedia.org")) continue;
                }
                catch (MalformedURLException e) {
                }

                result.add(url);
                set.add(url);
                queue.addAll(HtmlHelper.parseUrls(url));
            }
        }
    }
    public static class Solution {
        /**
         * @param url a url of root page
         * @return all urls
         */
        public List<String> crawler(String url) {

            CrawlerThread.queue.add(url);
            CrawlerThread[] threads = new CrawlerThread[8];
            for (int i = 0; i < 8; ++i) {
                threads[i] = new CrawlerThread();
            }

            for (int i = 0; i < 8; ++i)
                threads[i].start();

            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
            }

            for (int i = 0; i < 8; ++i)
                threads[i].stop();

            return new ArrayList<String>(CrawlerThread.result);
        }
    }

    public static void main(String[] args) {
        try {
            URL u = new URL("http://www.wikipedia.org");
            u = new URL("http://www.wikipedia.org/about");
            System.out.println(u.getHost());
        }
        catch (MalformedURLException e) {
        }
    }
}

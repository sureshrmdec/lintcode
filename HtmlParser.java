import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

public class HtmlParser {
    // http://www.lintcode.com/en/problem/url-parser/
    /**
     * @param content source code
     * @return a list of links
     */
    public List<String> parseUrls(String content) {
        List<String> result = new ArrayList<String>();
        Pattern p = Pattern.compile(" +href *= *\"([^\"^\\s^#]+)\"", Pattern.CASE_INSENSITIVE);
        //Pattern p = Pattern.compile("(google)");
        Matcher m = p.matcher(content);
        while (m.find()) {
            String url = m.group(1);

            result.add(url);
        }

        return result;
    }

    public static void main(String[] args) {
        HtmlParser parser = new HtmlParser();
        //String html = "<a HREF=\"http://www.google.com\" class=\"text-lg\">Google</a>";
        //String html = "<div id=\"I_logo\"><a href=\"/\" title=\"cplusplus.com\"><div></div></a></div>";
        String html = "<link rel=\"shortcut icon\" type=\"image/x-icon\" href=\"/favicon.ico\">";
        System.out.println(parser.parseUrls(html));
    }
}

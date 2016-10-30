import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;
import java.io.IOException;

public class HtmlParser {
    // http://www.lintcode.com/en/problem/url-parser/
    /**
     * @param content source code
     * @return a list of links
     */
    public List<String> parseUrls(String content) {
        List<String> result = new ArrayList<String>();
        Pattern p = Pattern.compile(" +(?i)href *= *\"?([^\"'>^\\s]*)", Pattern.CASE_INSENSITIVE);
        //Pattern p = Pattern.compile("(google)");
        Matcher m = p.matcher(content);
        while (m.find()) {
            String url = m.group(1);

            if (url.length() == 0 || url.startsWith("#"))
                continue;
            result.add(url);
        }
        
        return result;
    }

    public static void main(String[] args) {
        HtmlParser parser = new HtmlParser();

        String html = "<a HREF=\"http://www.google.com\" class=\"text-lg\">Google</a>";
        System.out.println(parser.parseUrls(html));

        html = "<div id=\"I_logo\"><a href=\"/\" title=\"cplusplus.com\"><div></div></a></div>";
        System.out.println(parser.parseUrls(html));

        html = "<link rel=\"shortcut icon\" type=\"image/x-icon\" href=\"/favicon.ico\">";
        System.out.println(parser.parseUrls(html));

        html = "&lt;li>&lt;a href=&quot;#tabs-1&quot;>Issue Part&lt;/a>&lt;/li>";
        System.out.println(parser.parseUrls(html));

        html = "<tr><td><tt><b>[</b></tt><i>class</i><tt><b>]</b></tt></td><td>character class</td><td>the target character is part of the class (see <a href=\"#character_classes\">character classes</a> below)</td></tr>";
        System.out.println(parser.parseUrls(html));

        //try {
            //TestcaseReader reader = new TestcaseReader("./4.out");
            //String[] array = reader.readStringArray();
            //for (String s: array)
            //    System.out.println(s);

            //System.out.println("====== url is ======");

            //reader = new TestcaseReader("./4.in");
            //String html = reader.readAllLines();
            //for (String s: parser.parseUrls(html))
            //    System.out.println(s);
        //}
        //catch (IOException e) {
        //}
    }
}

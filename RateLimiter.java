import java.util.*;

public class RateLimiter {
    /**
     * @param timestamp the current timestamp
     * @param event the string to distinct different event
     * @param rate the format is [integer]/[s/m/h/d]
     * @param increment whether we should increase the counter
     * @return true or false to indicate the event is limited or not
     */

    private List<Integer> signupEvents;
    private List<Integer> loginEvents;
    private Map<String, List<Integer>> map;

    public RateLimiter() {
        map = new HashMap<String, List<Integer>>();
    }

    public boolean isRatelimited(int timestamp, String event, String rate, boolean increment) {

        int rateSpan = rateSpan(rate);
        int rateMax = rateMax(rate);
        String e = event.toLowerCase();

        if (!map.containsKey(e)) map.put(e, new ArrayList<Integer>());
        List<Integer> list = map.get(e);

        int start = timestamp - rateSpan;
        int end = timestamp;

        int i = list.size() - 1;
        while (i >= 0 && list.get(i) > start)
            i--;

        int num = list.size() - 1 - i;
        if (num + 1 > rateMax)
            return true;
        else {
            if (increment)
                list.add(timestamp);
            return false;
        }
    }

    private int rateSpan(String rate) {
        char c = rate.charAt(rate.length() - 1);
        switch (c) {
            case 's': return 1;
            case 'm': return 60;
            case 'h': return 3600;
            case 'd': return 86400;
            default: return -1;
        }
    }

    private int rateMax(String rate) {
        String str = rate.substring(0, rate.indexOf('/'));
        return Integer.parseInt(str);
    }

    public static void main(String[] args) {
        RateLimiter limiter = new RateLimiter();

        limiter.isRatelimited(1, "login", "3/m", true);
        limiter.isRatelimited(11, "Signin", "3/m", true);
        limiter.isRatelimited(21, "login", "3/h", true);
        limiter.isRatelimited(29, "Signup", "3/h", true);
        limiter.isRatelimited(31, "Signup", "3/h", true);
        limiter.isRatelimited(33, "Signup", "3/h", true);
        limiter.isRatelimited(100, "Signup", "3/h", true); // true
        limiter.isRatelimited(102, "Signup", "3/h", true);
    }
}

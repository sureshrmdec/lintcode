import java.util.*;

public class WebLogger {

    private Queue<Integer> hits;
    public WebLogger() {
        hits = new LinkedList<Integer>();
    }

    /**
     * @param timestamp an integer
     * @return void
     */
    public void hit(int timestamp) {
        hits.add(timestamp);
    }

    /**
     * @param timestamp an integer
     * @return an integer
     */
    public int get_hit_count_in_last_5_minutes(int timestamp) {
        int start = timestamp - 300;
        int end = timestamp;

        while (!hits.isEmpty() && hits.peek() <= start)
            hits.poll();

        return hits.size();
    }
}

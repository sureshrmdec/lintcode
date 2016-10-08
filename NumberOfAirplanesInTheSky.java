public class NumberOfAirplanesInTheSky {
    
    // Definition of Interval:
    public classs Interval {
        int start, end;
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    
    public static class Solution {
        /**
         * @param intervals: An interval array
         * @return: Count of airplanes are in the sky.
         */


        private final ByTime BY_TIME = new ByTime();

        private class ByTime implements Comparator<Event> {
            public int compare(Event v, Event w) { 
                if (v.time == w.time) {
                    if (!v.type && w.type) return -1;
                    else if (v.type && !w.type) return 1;
                    else return 0;
                }
                else return v.time - w.time;
            }
        }

        private class Event {
            public int time;
            public boolean type;
            public Event(int time, boolean type) { this.time = time; this.type = type; }
        }

        public int countOfAirplanes(List<Interval> airplanes) { 

            List<Event> events = new ArrayList<Event>();
            for (Interval interval: airplanes) {
                events.add(new Event(interval.start, true));
                events.add(new Event(interval.end, false));
            }
            Collections.sort(events, BY_TIME);

            int count = 0, max = 0;
            for (Event e: events) {
                if (e.type) count++;
                else count--;

                if (count > max) max = count;
            }

            return max;
        }
    }

    public static void main(String[] args) {

    }
}

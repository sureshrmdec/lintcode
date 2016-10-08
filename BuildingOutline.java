import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

public class BuildingOutline {
    // Building Outline
    // http://www.lintcode.com/en/problem/building-outline/

    public static class Solution {

        private ArrayList<ArrayList<Integer>> results;

        private class Edge {
            public int pos, height;
            public boolean type;
            public Edge(int p, int h, boolean t) {pos = p; height = h; type = t;}
        }

        private class EdgeComparator implements Comparator<Edge> {
            public int compare(Edge e1, Edge e2) {
                if (e1.pos == e2.pos) {
                    if (!e1.type && e2.type) return -1;
                    else if (e1.type && !e2.type) return 1;
                    else return e1.height - e2.height;
                }
                else return e1.pos - e2.pos;
            }
        }

        private void addOutline(int start, int end, int height) {
            if (start == end || height == 0) return;

            if (!results.isEmpty()) {
                ArrayList<Integer> lastOutline = results.get(results.size() - 1);
                int prevStart = lastOutline.get(0);
                int prevEnd = lastOutline.get(1);
                int prevHeight = lastOutline.get(2);
                if (height == prevHeight && prevStart <= start && start <= prevEnd) {

                    //System.out.println("updateOutline: start: " + start + ", end: " + end + ", height: " + height);
                    lastOutline.set(1, end);
                    return;
                }
            }
            ArrayList<Integer> outline = new ArrayList<Integer>();
            outline.add(start);
            outline.add(end);
            outline.add(height);

            //System.out.println("addOutline: start: " + start + ", end: " + end + ", height: " + height);
            results.add(outline);
        }

        public ArrayList<ArrayList<Integer>> buildingOutline(int[][] buildings) {
            results = new ArrayList<ArrayList<Integer>>();
            if (buildings.length == 0) return results;

            PriorityQueue<Edge> queue = new PriorityQueue<Edge>(1, new EdgeComparator());
            for (int i = 0; i < buildings.length; ++i) {
                queue.add(new Edge(buildings[i][0], buildings[i][2], true));
                queue.add(new Edge(buildings[i][1], buildings[i][2], false));
            }

            int prevStart = queue.peek().pos;
            PriorityQueue<Integer> tops = new PriorityQueue<Integer>(1, Collections.reverseOrder());
            tops.add(0);

            while (!queue.isEmpty()) {
                Edge e = queue.poll();
                int curHeight = tops.peek();

                //System.out.println("e.pos: " + e.pos + ", e.height: " + e.height + ", e.type: " + e.type + ", curHeight: " + curHeight + ", prevStart: " + prevStart);
                if (e.type) {
                    if (e.height >= curHeight) {
                        addOutline(prevStart, e.pos, curHeight);
                        prevStart = e.pos;
                    }
                    tops.add(e.height);
                }
                else {
                    if (e.height == curHeight) {
                        addOutline(prevStart, e.pos, e.height);
                        prevStart = e.pos;
                    }
                    tops.remove(e.height);
                }
            }

            return results;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] buildings = {
            {1,3,3},
            {2,4,4},
            {5,6,1}
        };

        int[][] buildings2 = {{1,10,3},{2,5,8},{7,9,8}};

        System.out.println(s.buildingOutline(buildings2));
    }
}

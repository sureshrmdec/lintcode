import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ScrambleString {
    public static class Solution {
        /**
         * @param s1 A string
         * @param s2 Another string
         * @return whether s2 is a scrambled string of s1
         */

        private HashSet<String> set;

        public boolean isScramble(String s1, String s2) {

            set = new HashSet<String>();

            if (s1.length() != s2.length()) return false;

            if (!hasSameChars(s1, s2)) return false;

            if (s1.equals(s2)) return true;

            int n = s1.length();

            HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
            ArrayList<String> list = find(s1, map);
            for (String str: list)
                set.add(str);

            //System.out.println(set);

            if (set.contains(s2)) return true;
            else return false;
        }

        private ArrayList<String> find(String s, HashMap<String, ArrayList<String>> map) {
            if (map.containsKey(s)) return map.get(s);

            ArrayList<String> list = new ArrayList<String>();

            if (s.length() == 1) list.add(s);

            for (int i = 0; i + 1 < s.length(); ++i) {
                String leftStr = s.substring(0, i + 1);
                String rightStr = s.substring(i + 1, s.length());

                ArrayList<String> left = find(leftStr, map);
                ArrayList<String> right = find(rightStr, map);

                for (String lstr: left)
                    for (String rstr: right) {
                        list.add(lstr + rstr);
                        list.add(rstr + lstr);
                    }
            }

            map.put(s, list);
            //System.out.println(map);
            return list;
        }

        private boolean hasSameChars(String s1, String s2) {
            return true;
        }
    }

    public static void main(String[] args) {
        String s1 = "great";
        String s2 = "rgeat";

        Solution s = new Solution();
        System.out.println(s1 + " and " + s2 + ": " + s.isScramble(s1, s2));
    }
}

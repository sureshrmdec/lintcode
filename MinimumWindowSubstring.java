import java.util.HashMap;
import java.util.HashSet;

public class MinimumWindowSubstring {
    public static class Solution {
        /**
         * @param source: A string
         * @param target: A string
         * @return: A string denote the minimum window
         *          Return "" if there is no such a string
         */

        public String minWindow(String source, String target) {

            String ans = new String();
            if (source.isEmpty() || target.isEmpty()) return ans;

            HashMap<Character, Integer> targetMap = new HashMap<Character, Integer>();
            for (int i = 0; i < target.length(); ++i) {
                add(target.charAt(i), targetMap);
            }
            HashMap<Character, Integer> copyTargetMap = (HashMap<Character, Integer>) targetMap.clone();
            HashMap<Character, Integer> sourceMap = new HashMap<Character, Integer>();

            int j = 0;
            int minlen = Integer.MAX_VALUE;
            for (int i = 0; i < source.length(); ++i) {
                while (j < source.length() && !copyTargetMap.isEmpty()) {
                    char c = source.charAt(j);
                    remove(c, copyTargetMap);
                    add(c, sourceMap);
                    j++;
                }

                if (copyTargetMap.isEmpty()) {
                    if (minlen > j - i) {
                        minlen = j - i;
                        ans = source.substring(i, j);
                    }
                }
                //System.out.println("copy: " + copyTargetMap + ", source: " + sourceMap + ", ans: " + ans);

                char c = source.charAt(i);
                remove(c, sourceMap);
                if (targetMap.containsKey(c)) {
                    if (!sourceMap.containsKey(c) || sourceMap.get(c) < targetMap.get(c))
                        add(c, copyTargetMap);
                }
            }

            return ans;
        }

        private void add(char c, HashMap<Character, Integer> map) {
            if (map.containsKey(c)) map.put(c, map.get(c) + 1);
            else map.put(c, 1);
        }

        private void remove(char c, HashMap<Character, Integer> map) {
            if (map.containsKey(c))
                if (map.get(c) == 1) map.remove(c);
                else map.put(c, map.get(c) - 1);
        }
    }

    public static void main(String[] args) {
        String src = "ADOBECODEBANC";
        String target = "ABC";
        Solution so = new Solution();
        System.out.println(so.minWindow(src, target));
    }
}

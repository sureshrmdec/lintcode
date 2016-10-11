import java.util.HashMap;

public class LongestSubstringWithAtMostKDistinctCharacters {
    // http://www.lintcode.com/en/problem/longest-substring-with-at-most-k-distinct-characters/

    public static class Solution {
        /**
         * @param s : A string
         * @return : The length of the longest substring 
         *           that contains at most k distinct characters.
         */
        public int lengthOfLongestSubstringKDistinct(String s, int k) {

            if (s.length() == 0) return 0;

            int j = 0;
            int ans = 0;
            HashMap<Character, Integer> map = new HashMap<Character, Integer>();
            for (int i = 0; i < s.length(); ++i) {
                while (j < s.length() && map.size() <= k) {
                    //System.out.println("j: " + j + ", map.size(): " + map.size() + ", s.charAt[j]: " + s.charAt(j));
                    map.put(s.charAt(j), map.containsKey(s.charAt(j)) ? map.get(s.charAt(j)) + 1 : 1);
                    j++;
                }

                if (map.size() > k)
                    ans = Math.max(ans, j - i - 1);
                else
                    ans = Math.max(ans, j - i);

                //System.out.println("ans: " + ans + ", i: " + i + "s.charAt(i): " + s.charAt(i));

                if (j == s.length()) break;

                if (map.containsKey(s.charAt(i))) {
                    int count = map.get(s.charAt(i));
                    if (count == 1)
                        map.remove(s.charAt(i));
                    else
                        map.put(s.charAt(i), count - 1);
                }
            }

            return ans;
        }
    }

    public static void main(String[] args) {
        String s = "eqgkcwGFvjjmxutystqdfhuMblWbylgjxsxgnoh";
        int k = 16;

        Solution solution = new Solution();
        solution.lengthOfLongestSubstringKDistinct(s, k);
    }
}

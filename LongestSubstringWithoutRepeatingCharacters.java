import java.util.HashSet;

public class LongestSubstringWithoutRepeatingCharacters {
    // http://www.lintcode.com/en/problem/longest-substring-without-repeating-characters/

    public class Solution {
        /**
         * @param s: a string
         * @return: an integer 
         */
        public int lengthOfLongestSubstring(String s) {
            if (s.length() == 0) return 0;

            HashSet<Character> set = new HashSet<Character>();
            int j = 0;
            int ans = 0;
            for (int i = 0; i < s.length(); ++i) {
                while (j < s.length() && !set.contains(s.charAt(j))) {
                    set.add(s.charAt(j));
                    j++;
                }

                ans = Math.max(ans, j - i);

                if (j == s.length()) break;

                set.remove(s.charAt(i));
            }

            return ans;
        }
    }
}

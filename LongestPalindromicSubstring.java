public class LongestPalindromicSubstring {
    public static class Solution {
        /**
         * @param s input string
         * @return the longest palindromic substring
         */
        public String longestPalindrome(String s) {
            int N = s.length();

            String ans = "";
            
            if (N == 0) return "";
            int max = 0;

            for (int i = 0; i < N; ++i) {

                int odd = 0;
                while (i - odd >= 0 && i + odd < N && s.charAt(i - odd) == s.charAt(i + odd))
                    odd++;

                int even = 0;
                while (i - even - 1 >= 0 && i + even < N && s.charAt(i - even - 1) == s.charAt(i + even))
                    even++;

                int tmp = Math.max((odd - 1) * 2 + 1, even == 0 ? 1 : even * 2);
                //System.out.println("odd: " + odd + ", even: " + even + ", i: " + i + ", tmp: " + tmp);
                if (max < tmp) {
                    max = tmp;
                    if (max % 2 == 0) ans = s.substring(i - even, i + even);
                    else ans = s.substring(i - odd + 1, i + odd);
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {

        //String str = "abcdzdcab";
        String str = "bb";
        Solution s = new Solution();

        System.out.println(s.longestPalindrome(str));
    }
}

import java.util.Arrays;

public class TriangleCount {
    //
    // http://www.lintcode.com/en/problem/triangle-count/
    //
    /**
     * @param S: A list of integers
     * @return: An integer
     */

    public static int triangleCount(int S[]) {
        Arrays.sort(S);
        assert(S.length > 2);

        int ans = 0;
        for (int i = 2; i < S.length; ++i) {
            int left = 0;
            int right = i - 1;
            
            while (left < right) {
                if (S[left] + S[right] > S[i]) {
                    ans += right - left;
                    right--;
                } else {
                    left++;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] array1 = {3,4,6,7};
        System.out.println(triangleCount(array1));
        int[] array2 = {4,4,4,4};
        System.out.println(triangleCount(array2));

    }
}

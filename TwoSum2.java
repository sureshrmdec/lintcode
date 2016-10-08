import java.util.Arrays;

public class TwoSum2 {
    /**
     * @param nums: an array of integer
     * @param target: an integer
     * @return: an integer
     */

    public static int twoSum2(int[] nums, int target) {
        Arrays.sort(nums);
        
        int left = 0;
        int right = nums.length - 1;

        int answer = 0;
        while (left < right) {
            if (nums[left] + nums[right] > target) {
                answer += right - left;
                right--;
            }
            else
                left++;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] array1 = {2, 7, 11, 15};
        System.out.println(twoSum2(array1, 24));
    }
}

import java.util.*;

class Solution {
    public String largestNumber(int[] nums) {
        String[] arr = new String[nums.length];

        // Convert integers to strings
        for (int i = 0; i < nums.length; i++) {
            arr[i] = String.valueOf(nums[i]);
        }

        // Sort to form the largest number
        Arrays.sort(arr, (a, b) -> (b + a).compareTo(a + b));

        // Handle cases like [0, 0]
        if (arr[0].equals("0")) {
            return "0";
        }

        // Build the result
        StringBuilder result = new StringBuilder();

        for (String num : arr) {
            result.append(num);
        }

        return result.toString();
    }
}
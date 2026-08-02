import java.util.Arrays;

public class KthSmallest {
    public static void main(String[] args) {
        int[] arr = {12, 3, 5, 7, 19, 1};
        int k = 3;

        Arrays.sort(arr);

        if (k > 0 && k <= arr.length) {
            System.out.println("The " + k + "rd smallest element is: " + arr[k - 1]);
        } else {
            System.out.println("Invalid value of k");
        }
    }
}
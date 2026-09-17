import java.io.*;

public class Main {

    static boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    static int palindromeIndex(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {

            if (s.charAt(left) != s.charAt(right)) {

                // Try removing the left character
                if (isPalindrome(s, left + 1, right)) {
                    return left;
                }

                // Try removing the right character
                if (isPalindrome(s, left, right - 1)) {
                    return right;
                }

                return -1;
            }

            left++;
            right--;
        }

        // Already a palindrome
        return -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );

        int q = Integer.parseInt(br.readLine().trim());

        StringBuilder output = new StringBuilder();

        while (q-- > 0) {
            String s = br.readLine().trim();
            output.append(palindromeIndex(s)).append('\n');
        }

        System.out.print(output);
    }
}

import java.io.*;
import java.util.*;

public class Main {

    static String twoStrings(String s1, String s2) {
        boolean[] seen = new boolean[26];

        // Mark characters present in s1
        for (char c : s1.toCharArray()) {
            seen[c - 'a'] = true;
        }

        // Check whether any character of s2 is in s1
        for (char c : s2.toCharArray()) {
            if (seen[c - 'a']) {
                return "YES";
            }
        }

        return "NO";
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );

        int t = Integer.parseInt(br.readLine().trim());

        StringBuilder output = new StringBuilder();

        while (t-- > 0) {
            String s1 = br.readLine().trim();
            String s2 = br.readLine().trim();

            output.append(twoStrings(s1, s2)).append('\n');
        }

        System.out.print(output);
    }
}

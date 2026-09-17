import java.io.*;
import java.util.*;

public class Main {

    static long solve(String s) {
        int n = s.length();
        int[] z = new int[n];

        int l = 0, r = 0;

        for (int i = 1; i < n; i++) {

            if (i <= r) {
                z[i] = Math.min(r - i + 1, z[i - l]);
            }

            while (i + z[i] < n &&
                   s.charAt(z[i]) == s.charAt(i + z[i])) {
                z[i]++;
            }

            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }

        // Similarity with itself is n
        long answer = n;

        // Similarity with every other suffix
        for (int i = 1; i < n; i++) {
            answer += z[i];
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );

        int t = Integer.parseInt(br.readLine().trim());

        StringBuilder output = new StringBuilder();

        while (t-- > 0) {
            String s = br.readLine().trim();
            output.append(solve(s)).append('\n');
        }

        System.out.print(output);
    }
}

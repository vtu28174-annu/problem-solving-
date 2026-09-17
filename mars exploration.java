import java.io.*;

public class Main {

    static int marsExploration(String s) {
        int changes = 0;

        for (int i = 0; i < s.length(); i++) {
            char expected = "SOS".charAt(i % 3);

            if (s.charAt(i) != expected) {
                changes++;
            }
        }

        return changes;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );

        String s = br.readLine().trim();

        System.out.println(marsExploration(s));
    }
}

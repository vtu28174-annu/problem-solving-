import java.io.*;
import java.util.*;

public class Main {

    // Segment tree for range maximum query
    static class SegmentTree {
        int size;
        int[] tree;

        SegmentTree(int[] arr) {
            int n = arr.length;
            size = 1;

            while (size < n) {
                size <<= 1;
            }

            tree = new int[2 * size];

            for (int i = 0; i < n; i++) {
                tree[size + i] = arr[i];
            }

            for (int i = size - 1; i > 0; i--) {
                tree[i] = Math.max(tree[i << 1],
                                    tree[i << 1 | 1]);
            }
        }

        // Maximum in [left, right], inclusive
        int query(int left, int right) {
            if (left > right) {
                return 0;
            }

            left += size;
            right += size;

            int result = 0;

            while (left <= right) {
                if ((left & 1) == 1) {
                    result = Math.max(result, tree[left++]);
                }

                if ((right & 1) == 0) {
                    result = Math.max(result, tree[right--]);
                }

                left >>= 1;
                right >>= 1;
            }

            return result;
        }
    }

    // Manacher's algorithm on transformed string.
    //
    // Example:
    // "abba"
    // becomes:
    // #a#b#b#a#
    //
    // radius[i] is the palindrome radius measured
    // in transformed-string positions.
    static int[] manacher(String s) {
        int n = s.length();

        char[] transformed = new char[2 * n + 1];

        for (int i = 0; i < transformed.length; i++) {
            if ((i & 1) == 0) {
                transformed[i] = '#';
            } else {
                transformed[i] = s.charAt(i / 2);
            }
        }

        int m = transformed.length;
        int[] radius = new int[m];

        int center = 0;
        int right = 0;

        for (int i = 0; i < m; i++) {

            int mirror = 2 * center - i;

            if (i < right) {
                radius[i] = Math.min(
                    right - i,
                    mirror >= 0 ? radius[mirror] : 0
                );
            }

            while (i - radius[i] - 1 >= 0 &&
                   i + radius[i] + 1 < m &&
                   transformed[i - radius[i] - 1]
                       == transformed[i + radius[i] + 1]) {

                radius[i]++;
            }

            if (i + radius[i] > right) {
                center = i;
                right = i + radius[i];
            }
        }

        return radius;
    }

    static int[] circularPalindromes(String s) {
        int n = s.length();

        // Duplicate the string so every rotation appears
        // as a contiguous substring.
        String doubled = s + s;

        // Manacher on the doubled string.
        int[] radius = manacher(doubled);

        // Build RMQ structure.
        SegmentTree segmentTree = new SegmentTree(radius);

        int[] answer = new int[n];

        /*
         * In the transformed string:
         *
         * original character i -> transformed index 2*i + 1
         *
         * Rotation starting at i contains:
         *
         * original indices [i, i+n-1]
         *
         * transformed indices:
         * L = 2*i + 1
         * R = 2*(i+n-1) + 1
         */
        for (int start = 0; start < n; start++) {

            int L = 2 * start + 1;
            int R = 2 * (start + n - 1) + 1;

            int low = 1;
            int high = n;
            int best = 1;

            /*
             * Binary search for the longest palindrome.
             */
            while (low <= high) {
                int len = (low + high) >>> 1;

                /*
                 * A palindrome of original length len can have
                 * its center anywhere from:
                 *
                 * L + len - 1
                 * through
                 * R - len + 1
                 *
                 * If any such center has radius >= len,
                 * a palindrome of length len exists.
                 */
                int leftCenter = L + len - 1;
                int rightCenter = R - len + 1;

                if (leftCenter <= rightCenter) {

                    int maxRadius =
                        segmentTree.query(leftCenter, rightCenter);

                    if (maxRadius >= len) {
                        best = len;
                        low = len + 1;
                    } else {
                        high = len - 1;
                    }

                } else {
                    high = len - 1;
                }
            }

            answer[start] = best;
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );

        int n = Integer.parseInt(br.readLine().trim());
        String s = br.readLine().trim();

        int[] result = circularPalindromes(s);

        StringBuilder out = new StringBuilder();

        for (int value : result) {
            out.append(value).append('\n');
        }

        System.out.print(out);
    }
}

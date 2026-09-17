class Solution {

    public int strStr(String haystack, String needle) {
        int[] lps = buildLPS(needle);

        int i = 0; // haystack pointer
        int j = 0; // needle pointer

        while (i < haystack.length()) {

            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;

                // Entire needle matched
                if (j == needle.length()) {
                    return i - j;
                }

            } else if (j > 0) {
                // Don't move i back
                j = lps[j - 1];

            } else {
                i++;
            }
        }

        return -1;
    }

    private int[] buildLPS(String pattern) {
        int[] lps = new int[pattern.length()];

        int len = 0;
        int i = 1;

        while (i < pattern.length()) {

            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;

            } else if (len > 0) {
                len = lps[len - 1];

            } else {
                lps[i] = 0;
                i++;
            }
        }

        return lps;
    }
}

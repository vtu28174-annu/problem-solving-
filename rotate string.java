class Solution {

    public boolean rotateString(String s, String goal) {

        // Rotations must have the same length
        if (s.length() != goal.length()) {
            return false;
        }

        // Every rotation of s appears in s + s
        return (s + s).contains(goal);
    }
}

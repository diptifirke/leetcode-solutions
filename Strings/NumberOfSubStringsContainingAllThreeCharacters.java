class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();
        int[] count = new int[3];
        int l = 0;
        int result = 0;

        for (int r = 0; r < n; r++) {
            count[s.charAt(r) - 'a']++;

            while (count[0] > 0 && count[1] > 0 && count[2] > 0) {
                result += n - r;
                count[s.charAt(l) - 'a']--;
                l++;
            }
        }

        return result;
    }
}
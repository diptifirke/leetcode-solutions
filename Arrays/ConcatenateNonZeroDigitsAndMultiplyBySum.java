class Solution {
    public long sumAndMultiply(int n) {
        String s = Integer.toString(n);
        StringBuilder sb = new StringBuilder();
        long digitSum = 0;

        for (char c : s.toCharArray()) {
            if (c != '0') {
                sb.append(c);
                digitSum += (c - '0');
            }
        }

        if (sb.length() == 0) return 0;

        long x = Long.parseLong(sb.toString());
        return x * digitSum;
    }
}
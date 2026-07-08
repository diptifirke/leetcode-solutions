class Solution {
    static final int MOD = 1_000_000_007;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int m = s.length();

        long[] prefixX = new long[m + 1];
        long[] prefixSum = new long[m + 1];
        long[] prefixCnt = new long[m + 1];
        long[] pow10 = new long[m + 1];

        pow10[0] = 1;
        for (int i = 1; i <= m; i++) {
            pow10[i] = pow10[i - 1] * 10 % MOD;
        }

        for (int i = 0; i < m; i++) {
            int d = s.charAt(i) - '0';
            if (d != 0) {
                prefixX[i + 1] = (prefixX[i] * 10 + d) % MOD;
                prefixSum[i + 1] = prefixSum[i] + d;
                prefixCnt[i + 1] = prefixCnt[i] + 1;
            } else {
                prefixX[i + 1] = prefixX[i];
                prefixSum[i + 1] = prefixSum[i];
                prefixCnt[i + 1] = prefixCnt[i];
            }
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            long cnt = prefixCnt[r + 1] - prefixCnt[l];
            long x = (prefixX[r + 1] - prefixX[l] * pow10[(int) cnt] % MOD + MOD) % MOD;
            long sum = prefixSum[r + 1] - prefixSum[l];

            ans[i] = (int) (x * sum % MOD);
        }

        return ans;
    }
}
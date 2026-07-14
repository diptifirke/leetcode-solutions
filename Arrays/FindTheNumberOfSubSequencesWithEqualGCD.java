class Solution {
    static final int MOD = 1_000_000_007;
    int[] nums;
    Long[][][] dp;

    public int subsequencePairCount(int[] nums) {
        this.nums = nums;

        int max = 0;
        for (int x : nums) {
            max = Math.max(max, x);
        }

        dp = new Long[nums.length][max + 1][max + 1];

        return (int) dfs(0, 0, 0);
    }

    private long dfs(int idx, int g1, int g2) {
        if (idx == nums.length) {
            return (g1 == g2 && g1 != 0) ? 1 : 0;
        }

        if (dp[idx][g1][g2] != null) {
            return dp[idx][g1][g2];
        }

        long ans = dfs(idx + 1, g1, g2);

        ans = (ans + dfs(idx + 1,
                g1 == 0 ? nums[idx] : gcd(g1, nums[idx]),
                g2)) % MOD;

        ans = (ans + dfs(idx + 1,
                g1,
                g2 == 0 ? nums[idx] : gcd(g2, nums[idx]))) % MOD;

        return dp[idx][g1][g2] = ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}
class Solution {
    int[] bit;
    int N;

    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        N = 2 * n + 2;
        int off = n + 1;
        bit = new int[N + 1];

        long ans = 0;
        int prefix = 0;
        update(prefix + off);

        for (int i = 0; i < n; i++) {
            prefix += (nums[i] == target) ? 1 : -1;
            ans += query(prefix - 1 + off);
            update(prefix + off);
        }

        return ans;
    }

    void update(int i) {
        for (; i <= N; i += i & (-i)) bit[i]++;
    }

    int query(int i) {
        int s = 0;
        for (; i > 0; i -= i & (-i)) s += bit[i];
        return s;
    }
}
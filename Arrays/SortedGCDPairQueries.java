class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;
        for (int x : nums) {
            max = Math.max(max, x);
        }

        int[] freq = new int[max + 1];
        for (int x : nums) {
            freq[x]++;
        }

        long[] cnt = new long[max + 1];

        for (int g = max; g >= 1; g--) {
            long c = 0;
            for (int j = g; j <= max; j += g) {
                c += freq[j];
            }

            cnt[g] = c * (c - 1) / 2;

            for (int j = g * 2; j <= max; j += g) {
                cnt[g] -= cnt[j];
            }
        }

        long[] prefix = new long[max + 1];
        for (int i = 1; i <= max; i++) {
            prefix[i] = prefix[i - 1] + cnt[i];
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            long k = queries[i] + 1;

            int left = 1, right = max;
            while (left < right) {
                int mid = left + (right - left) / 2;

                if (prefix[mid] >= k) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            ans[i] = left;
        }

        return ans;
    }
}
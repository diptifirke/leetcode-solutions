class Solution {
    public int maximumLength(int[] nums) {
        Map<Long, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put((long) num, freq.getOrDefault((long) num, 0) + 1);
        }

        int ans = 1;

        for (long x : freq.keySet()) {
            if (x == 1) continue;

            long sqrtX = (long) Math.sqrt((double) x);
            if (sqrtX * sqrtX == x && freq.getOrDefault(sqrtX, 0) >= 2) {
                continue;
            }

            long cur = x;
            int chainLen = 0;

            while (cur <= 1_000_000_000L && freq.getOrDefault(cur, 0) >= 2) {
                chainLen++;
                cur = cur * cur;
            }

            int total;

            if (cur <= 1_000_000_000L && freq.getOrDefault(cur, 0) >= 1) {
                total = 2 * chainLen + 1;
            } else {
                if (chainLen == 0) {
                    total = 1;
                } else {
                    total = 2 * chainLen - 1;
                }
            }

            ans = Math.max(ans, total);
        }

        if (freq.containsKey(1L)) {
            int ones = freq.get(1L);
            ans = Math.max(ans, ones % 2 == 1 ? ones : ones - 1);
        }

        return ans;
    }
}
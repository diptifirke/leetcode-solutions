class Solution {
    int n;
    List<long[]>[] adj;
    boolean[] online;

    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        n = online.length;
        this.online = online;
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();

        TreeSet<Long> costSet = new TreeSet<>();
        costSet.add(0L);

        for (int[] e : edges) {
            adj[e[0]].add(new long[]{e[1], e[2]});
            costSet.add((long) e[2]);
        }

        long[] sortedCosts = costSet.stream().mapToLong(Long::longValue).toArray();

        int lo = 0, hi = sortedCosts.length - 1;
        int ans = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            long threshold = sortedCosts[mid];

            if (check(threshold, k)) {
                ans = (int) threshold;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    private boolean check(long minEdge, long k) {
        long[] dp = new long[n];
        Arrays.fill(dp, Long.MAX_VALUE / 2);
        dp[0] = 0;

        int[] inDeg = new int[n];
        for (int u = 0; u < n; u++) {
            for (long[] e : adj[u]) {
                inDeg[(int) e[0]]++;
            }
        }

        Queue<Integer> topo = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDeg[i] == 0) topo.offer(i);
        }

        while (!topo.isEmpty()) {
            int u = topo.poll();
            for (long[] e : adj[u]) {
                int v = (int) e[0];
                long cost = e[1];
                if (--inDeg[v] == 0) topo.offer(v);

                if (cost < minEdge) continue;
                if (v != n - 1 && !online[v]) continue;
                if (dp[u] >= Long.MAX_VALUE / 2) continue;

                dp[v] = Math.min(dp[v], dp[u] + cost);
            }
        }

        return dp[n - 1] <= k;
    }
}
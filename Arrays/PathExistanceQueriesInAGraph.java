class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;
        Arrays.sort(order, (a, b) -> Integer.compare(nums[a], nums[b]));

        int[] sortedVal = new int[n];
        int[] pos = new int[n]; 
        for (int i = 0; i < n; i++) {
            sortedVal[i] = nums[order[i]];
            pos[order[i]] = i;
        }

        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        for (int i = 0; i < n - 1; i++) {
            if (sortedVal[i + 1] - sortedVal[i] <= maxDiff) {
                union(parent, i, i + 1);
            }
        }

        int[] R = new int[n];
        int right = 0;
        for (int i = 0; i < n; i++) {
            if (right < i) right = i;
            while (right + 1 < n && sortedVal[right + 1] - sortedVal[i] <= maxDiff) right++;
            R[i] = right;
        }

        int LOG = 1;
        while ((1 << LOG) < n) LOG++;
        LOG++;

        int[][] up = new int[LOG][n];
        up[0] = R;
        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }

        int q = queries.length;
        int[] answer = new int[q];

        for (int i = 0; i < q; i++) {
            int u = queries[i][0], v = queries[i][1];
            if (u == v) {
                answer[i] = 0;
                continue;
            }
            int su = pos[u], sv = pos[v];
            if (find(parent, su) != find(parent, sv)) {
                answer[i] = -1;
                continue;
            }
            int a = Math.min(su, sv), b = Math.max(su, sv);
            answer[i] = minJumps(a, b, up, LOG);
        }

        return answer;
    }

    private int minJumps(int start, int target, int[][] up, int LOG) {
        int cur = start, jumps = 0;
        for (int k = LOG - 1; k >= 0; k--) {
            if (up[k][cur] < target) {
                cur = up[k][cur];
                jumps += (1 << k);
            }
        }
        if (cur < target) jumps++;
        return jumps;
    }

    private int find(int[] parent, int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    private void union(int[] parent, int a, int b) {
        int ra = find(parent, a), rb = find(parent, b);
        if (ra != rb) parent[ra] = rb;
    }
}
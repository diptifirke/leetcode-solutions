class Solution {
    int[] parent;
    int[] rank;

    public int countCompleteComponents(int n, int[][] edges) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        for (int[] e : edges) {
            union(e[0], e[1]);
        }

        int[] compNodes = new int[n];
        int[] compEdges = new int[n];

        for (int i = 0; i < n; i++) {
            compNodes[find(i)]++;
        }

        for (int[] e : edges) {
            compEdges[find(e[0])]++;
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (find(i) == i) {
                int v = compNodes[i];
                int e = compEdges[i];
                if (e == v * (v - 1) / 2) {
                    ans++;
                }
            }
        }

        return ans;
    }

    int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    void union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra == rb) return;
        if (rank[ra] < rank[rb]) {
            int temp = ra; ra = rb; rb = temp;
        }
        parent[rb] = ra;
        if (rank[ra] == rank[rb]) rank[ra]++;
    }
}
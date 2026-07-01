class Solution {
    int[][] dist;
    int n;
    int[] dr = {0, 0, 1, -1};
    int[] dc = {1, -1, 0, 0};

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        n = grid.size();
        dist = new int[n][n];

        for (int[] row : dist) Arrays.fill(row, -1);

        Queue<int[]> queue = new LinkedList<>();

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid.get(r).get(c) == 1) {
                    dist[r][c] = 0;
                    queue.offer(new int[]{r, c});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0], c = cell[1];
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && dist[nr][nc] == -1) {
                    dist[nr][nc] = dist[r][c] + 1;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        int lo = 0, hi = 2 * n;
        while (lo < hi) {
            int mid = (lo + hi + 1) / 2;
            if (canReach(mid)) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }

        return lo;
    }

    private boolean canReach(int threshold) {
        if (dist[0][0] < threshold || dist[n-1][n-1] < threshold) {
            return false;
        }

        boolean[][] visited = new boolean[n][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0], c = cell[1];

            if (r == n - 1 && c == n - 1) return true;

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (nr >= 0 && nr < n && nc >= 0 && nc < n
                        && !visited[nr][nc] && dist[nr][nc] >= threshold) {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        return false;
    }
}
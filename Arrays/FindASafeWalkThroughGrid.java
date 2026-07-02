class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();

        int startHealth = health - grid.get(0).get(0);
        if (startHealth <= 0) return false;

        int[][] maxHealth = new int[m][n];
        for (int[] row : maxHealth) Arrays.fill(row, -1);
        maxHealth[0][0] = startHealth;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, startHealth});

        int[] dr = {0, 0, 1, -1};
        int[] dc = {1, -1, 0, 0};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1], h = curr[2];

            if (r == m - 1 && c == n - 1) {
                return true;
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;

                int newHealth = h - grid.get(nr).get(nc);

                if (newHealth > 0 && newHealth > maxHealth[nr][nc]) {
                    maxHealth[nr][nc] = newHealth;
                    queue.offer(new int[]{nr, nc, newHealth});
                }
            }
        }

        return false;
    }
}
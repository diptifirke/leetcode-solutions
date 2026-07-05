class Solution {
    static final int MOD = 1_000_000_007;

    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int[][] dp = new int[n][n];
        int[][] cnt = new int[n][n];

        for (int[] row : dp) Arrays.fill(row, -1);

        dp[n - 1][n - 1] = 0;
        cnt[n - 1][n - 1] = 1;

        int[] dr = {1, 0, 1};
        int[] dc = {0, 1, 1};

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == n - 1 && j == n - 1) continue;

                char c = board.get(i).charAt(j);
                if (c == 'X') continue;

                int cellVal = (c == 'E') ? 0 : (c - '0');
                int bestScore = -1;
                int bestCnt = 0;

                for (int d = 0; d < 3; d++) {
                    int ni = i + dr[d];
                    int nj = j + dc[d];
                    if (ni >= n || nj >= n) continue;
                    if (dp[ni][nj] == -1) continue;

                    int score = dp[ni][nj];
                    if (score > bestScore) {
                        bestScore = score;
                        bestCnt = cnt[ni][nj];
                    } else if (score == bestScore) {
                        bestCnt = (bestCnt + cnt[ni][nj]) % MOD;
                    }
                }

                if (bestScore == -1) continue;

                dp[i][j] = bestScore + cellVal;
                cnt[i][j] = bestCnt;
            }
        }

        if (dp[0][0] == -1) return new int[]{0, 0};
        return new int[]{dp[0][0], cnt[0][0]};
    }
}
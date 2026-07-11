class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] adj = new List[numCourses];
        for (int i = 0; i < numCourses; i++) adj[i] = new ArrayList<>();

        int[] inDegree = new int[numCourses];

        for (int[] pre : prerequisites) {
            adj[pre[1]].add(pre[0]);
            inDegree[pre[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) queue.offer(i);
        }

        int processed = 0;

        while (!queue.isEmpty()) {
            int course = queue.poll();
            processed++;

            for (int next : adj[course]) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        return processed == numCourses;
    }
}
package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 286. Walls and Gates
 * https://leetcode.com/problems/walls-and-gates/description/
 */
public class WallAndGates {
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public void wallsAndGates(int[][] rooms) {
        int m = rooms.length, n = rooms[0].length;
        Queue<List<int[]>> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0 || rooms[i][j] == -1) {
                    continue;
                }
                visited[i][j] = true;
                List<int[]> curr = new ArrayList<>();
                curr.add(new int[]{i, j});
                queue.add(curr);
                while (!queue.isEmpty()) {
                    List<int[]> path = queue.poll();
                    int[] node = path.getLast();
                    for (int[] dir : dirs) {
                        int r = node[0] + dir[0];
                        int c = node[1] + dir[1];

                        if (r < 0 || r >= m || c < 0 || c >= n || rooms[r][c] == -1) {
                            continue;
                        } else if (rooms[r][c] != Integer.MAX_VALUE) {
                            for (int x = 0; x < path.size(); x++) {
                                int[] pathNode = path.get(x);
                                rooms[pathNode[0]][pathNode[1]] = path.size() - x + rooms[r][c];
                            }
                            queue.clear();
                        } else if (!visited[i][j]) {
                            List<int[]> updatePath = new ArrayList<>(path);
                            updatePath.add(new int[]{r, c});
                            queue.add(updatePath);
                        }
                    }
                }
            }
        }
    }
}

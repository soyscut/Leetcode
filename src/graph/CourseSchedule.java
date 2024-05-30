package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 207. Course Schedule
 * https://leetcode.com/problems/course-schedule/description/
 */
public class CourseSchedule {
    /**
     * DFS to solve the cyclic check
     * Time: O(V+E), see below for details
     * Space: O(V + E)
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        // O(E)
        for (int[] edge : prerequisites) {
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.get(edge[0]).add(edge[1]);
        }
        // O(V)

        // 这个状态array非常关键，1和2的区别要深刻理解，1代表访问过且它的dfs stack已经结束了
        // 2 代表还在dfs的stack中。因为还在stack中就才可以用来判断环
        // 0 = unvisited, 1= visited, 2= on the path
        int[] status = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (dfs(graph, status, i)) {
                return false;
            }
        }

        return true;
    }
    // DFS: the deepest stack is O(V), in the iteration, we will also iterate all edges with the for
    // loop, so in total O(V + E)
    private boolean dfs(Map<Integer, List<Integer>> graph, int[] status, int curr) {
        // 在stack中还能看到这个节点，出现了环
        if (status[curr] == 2) {
            return true;
        }
        // 进stack
        status[curr] = 2;
        for (int node : graph.getOrDefault(curr, new ArrayList<>())) {
            // 如果已经访问过且不属于当前这个stack才加入dfs的搜索
            if (status[node] != 1 && dfs(graph, status, node)) {
                return true;
            }
        }
        // 出了stack了，可以标记成1了
        status[curr] = 1;

        return false;
    }
}

package graph;

import java.util.*;

/**
 * 210. Course Schedule II
 * https://leetcode.com/problems/course-schedule-ii/
 */
public class CourseScheduleII {
    /**
     * Topological sort
     * Time: O(V+E), Space: O(V+E)
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] result = new int[numCourses];
        if (numCourses == 0) return result;

        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] indegree = new int[numCourses];

        for (int[] edge : prerequisites) {
            graph.putIfAbsent(edge[1], new ArrayList<>());
            graph.get(edge[1]).add(edge[0]);
            indegree[edge[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }
        int index = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            result[index] = curr;
            index++;

            if (!graph.containsKey(curr)) continue;
            for (int next : graph.get(curr)) {
                indegree[next]--;

                if (indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        if (index == numCourses) return result;
        return new int[0];
    }
}

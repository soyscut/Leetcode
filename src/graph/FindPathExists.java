package graph;

import java.util.*;

/**
 * 1791. Find if Path Exists in Graph
 * https://leetcode.com/problems/find-if-path-exists-in-graph/description/
 */
public class FindPathExists {
    /**
     * BFS
     */
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        // Time: O(V + E); Space: O(V+E)
        if (n == 0) return false;
        // Construct the graph from the edges
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(source);
        // 注意visited[]是什么时候被set的，要在discover的时候加，不然就会产生很多
        // duplicate nodes
        visited[source] = true;
        while (!queue.isEmpty()) {
            int i = queue.poll();
            if (i == destination) return true;
            List<Integer> nodes = graph.get(i);
            for (int next : nodes) {
                if (!visited[next]) {
                    queue.add(next);
                    // 注意visited[]是什么时候被set的，要在discover的时候加，不然就会产生很多
                    // duplicate nodes
                    visited[next] = true;
                }
            }
        }
        return false;
    }

    /**
     * DFS
     */
    public boolean validPath2(int n, int[][] edges, int source, int destination) {
        // Time: Typical DFS: O(V + E)
        // Space: O(V+E) = The graph map is (V+E), the visisted is O(V), the dfs stack depth is O(V)
        if (source == destination) return true;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        boolean[] vistied = new boolean[n];
        return dfs(graph, source, destination, vistied);
    }
    private boolean dfs(Map<Integer, List<Integer>> graph, int source, int target, boolean[] visited) {
        // 注意终止条件的set up, source == target 终止
        if (source == target) return true;
        // 注意什么时候set visited[] = true
        visited[source] = true;

        List<Integer> adjNodes = graph.get(source);

        for (int node : adjNodes) {
            if (!visited[node]) {
                if(dfs(graph, node, target, visited)) return true;
            }
        }

        return false;
    }
}

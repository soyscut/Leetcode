package graph;

import java.util.*;

/**
 * 797. All Paths From Source to Target
 * https://leetcode.com/problems/all-paths-from-source-to-target/description/
 */
public class FindAllPaths {
    /**
     * DAG: 有向无环，所以不用visited[] array to track
     * BFS
     */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        // Time: 总共有E条边，所有的路径是这E条边的组合，所以总共有2^E种可能，因为每条边都可以是
        // 选或者不选，而每条边最长有V个点，所以time：O(V * 2^E)
        // Space: 同样有那么种可能，然后每种最长V个点，所以 O(V * 2^E)
        List<List<Integer>> result = new ArrayList<>();

        Queue<List<Integer>> queue = new ArrayDeque<>();
        List<Integer> root = new ArrayList<>();
        root.add(0);
        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer>  path = queue.poll();
            int end = path.getLast();
            if (end == graph.length - 1) {
                result.add(path);
                continue;
            }
            int[] nexts = graph[end];
            for (int next : nexts) {
                List<Integer> newPath = new ArrayList<>(path);
                newPath.add(next);
                queue.add(newPath);
            }
        }

        return result;
    }

    /**
     * DFS
     * 时间和空间复杂度同BFS
     */
    public List<List<Integer>> allPathsSourceTarget2(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();

        dfs(graph, result, new ArrayList<>(), 0, graph.length - 1);
        return result;
    }

    private void dfs(int[][] graph, List<List<Integer>> result, List<Integer> path, int source, int target) {
        path.add(source);
        if (source == target) {
            result.add(new ArrayList<>(path));
            path.removeLast();
            return;
        }
        int[] dests = graph[source];
        for (int next : dests) {
            dfs(graph, result, path, next, target);
        }
        path.removeLast();

    }
}

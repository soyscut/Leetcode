/**
 *  Testing with:
 *         UnionFind uf = new UnionFind(10);
 *         // 1-2-5-6-7 3-8-9 4
 *         uf.union(1, 2);
 *         uf.union(2, 5);
 *         uf.union(5, 6);
 *         uf.union(6, 7);
 *         uf.union(3, 8);
 *         uf.union(8, 9);
 *         System.out.println(uf.connected(1, 5)); // true
 *         System.out.println(uf.connected(5, 7)); // true
 *         System.out.println(uf.connected(4, 9)); // false
 *         // 1-2-5-6-7 3-8-9-4
 *         uf.union(9, 4);
 *         System.out.println(uf.connected(4, 9)); // true
 */
public class UnionFind {
    /**
     * Space: O(N)
     */
    private final int[] root;
    private final int[] rank;
    private int components;

    /**
     *  Time: O(N)
     */
    public UnionFind(int size) {
        root = new int[size];
        rank = new int[size];
        components = size;

        for (int i = 0; i < size; i++) {
            root[i] = i;
            rank[i] = 1;
        }
    }

    /**
     * Time: Average O(1)
     * root[x] = find(root[x]) 这是带有path compression的find，利用recursive把一路上的路径
     * 都优化到直接 = root
     */
    public int find(int x) {
        if (x == root[x]) {
            return x;
        }
        return root[x] = find(root[x]);
    }

    /**
     * Time: Average O(1)
     * 带有rank优化的union，rank就是树的高度，因为两个树都分别有一个root,看哪个树高
     * 就把低的那个树连在高树的root上
     */
    public void union(int x, int y) {
        int rootX = root[x], rootY = root[y];

        if (rootX != rootY) {
            components--;
            if (rank[rootX] < rank[rootY]) {
                root[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                root[rootY] = rootX;
            } else {
                root[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }

    public int getComponents() {
        return components;
    }
}

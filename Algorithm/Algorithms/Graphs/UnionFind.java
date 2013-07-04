package Graphs;

import java.util.Arrays;

public class UnionFind {

    private final int size;
    private int[] id;
    private int[] treeSize;
    private int numComponents;

    public UnionFind(int size) {
        this.size = size;
        id = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
        Arrays.fill(treeSize, 1);
        numComponents = size;
    }

    public void union(int p, int q) {
        check(p);
        check(q);

        int rootQ = find(q);
        int rootP = find(p);

        if (rootQ == rootP) return;

        if (treeSize[rootQ] > treeSize[rootP]) {
            id[rootP] = rootQ;
            treeSize[rootQ] += treeSize[rootP];
        } else {
            id[rootQ] = rootP;
            treeSize[rootP] += treeSize[rootQ];
        }

        numComponents--;
    }

    public int find(int index) {
        check(index);

        int root = index;
        while (root != id[root]) {
            root = id[root];
        }

        while (index != root) {
            int temp = id[index];
            id[index] = root;
            index = temp;
        }

        return root;
    }

    public boolean connected(int p, int q) {
        return (find(p) == find(q));
    }

    public int numberComponents() {
        return numComponents;
    }

    private void check(int index) {
        if (index < 0 || index > size) throw new IllegalArgumentException("Index must be between zero and size.");
    }

}

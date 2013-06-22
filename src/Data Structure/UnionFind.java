import java.util.Arrays;

public class UnionFind {
    private int[] id;
    private int[] size;
    private int components;

    public UnionFind(int N) {
        id = new int[N];
        size = new int[N];
        components = N;

        for (int i = 0; i < N; i++) {
            id[i] = i;
        }

        Arrays.fill(size, 0);
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ) return;

        if (size[rootP] > size[rootQ]) {
            id[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            id[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }

        components--;
    }

    public int find(int p) {
        int root = p;

        while (id[root] != root) {
            root = id[root];
        }

        while (p != root) {
            int newP = id[p];
            id[p] = root;
            p = newP;
        }

        return root;
    }

    public boolean connected(int p, int q) {
        return (find(p) == find(q));
    }

    public int count() {
        return components;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int N = in.readInt();
        UnionFind uf = new UnionFind(N);

        // read in a sequence of pairs of integers (each in the range 0 to N-1),
        // calling find() for each pair: If the members of the pair are not already
        // call union() and print the pair.
        Stopwatch stopwatch = new Stopwatch();
        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            //StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
        System.out.println(stopwatch.elapsedTime() + " seconds for " + N + " elements.");
    }

}

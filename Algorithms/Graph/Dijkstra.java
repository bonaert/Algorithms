import java.util.Arrays;

public class Dijkstra {

    private double[] distTo;
    private int[] edgeTo;
    private IndexMinPriorityQueue<Double> indexMinPQ;
    private static final double INFINITY = Double.POSITIVE_INFINITY;

    public Dijkstra(EdgeWheightedDirectedGraph graph, int source) {

        checkNegativeEdges(graph);

        distTo = new double[graph.vertices()];
        edgeTo = new int[graph.vertices()];

        distTo[source] = 0;

        Arrays.fill(distTo, INFINITY);

        indexMinPQ = new IndexMinPriorityQueue<Double>(graph.edges());

        while (!indexMinPQ.isEmpty()) {
            int vertex = indexMinPQ.deleteMin();
            for (DirectedEdge edge : graph.adjEdges(vertex)) {
                relax(edge);
            }
        }
    }

    private void relax(DirectedEdge edge) {
        int from = edge.source();
        int to = edge.dest();
        if (distTo[to] > edge.wheight() + distTo[from]) {
            distTo[to] = edge.wheight() + distTo[from];
            edgeTo[to] = from;
            if (indexMinPQ.contains(to)) indexMinPQ.decreaseKey(to, distTo[to]);
            else indexMinPQ.insert(to, distTo[to]);
        }
    }

    private void checkNegativeEdges(EdgeWheightedDirectedGraph graph) {
        for (DirectedEdge edge : graph.edgesIterator()) {
            if (edge.wheight() < 0) throw new IllegalArgumentException("Negative edges!");
        }
    }

    public boolean hasPathTo(int vertex) {
        return distTo[vertex] < INFINITY;
    }

    public double distTo(int vertex) {
        return distTo[vertex];
    }

    public Iterable<Integer> pathTo(int vertex) {
        if (!hasPathTo(vertex)) return null;

        Stack<Integer> edges = new Stack<Integer>();

        int node;
        for (node = vertex; distTo[node] != node; node = edgeTo[node]) {
            edges.push(node);
        }

        edges.push(node);
        return edges;

    }
}

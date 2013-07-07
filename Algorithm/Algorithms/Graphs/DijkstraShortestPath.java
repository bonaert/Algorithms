package Graphs;

import DataStructure.IndexMinPriorityQueue;
import DataStructure.Stack;

import java.util.Arrays;

public class DijkstraShortestPath {

    private double[] distTo;
    private int[] edgeTo;
    private IndexMinPriorityQueue<Double> indexMinPQ;
    private static final double INFINITY = Double.POSITIVE_INFINITY;

    /**
     * Uses Dijkstra's Algorithm to find the shortest
     * path from a source to every reachable vertex
     * in a edge weighted directed graph.
     * <p/>
     * If the graph contains edges with a negative weight,
     * an IllegalArgumentException is thrown.
     *
     * @param graph
     * @param source
     */
    public DijkstraShortestPath(EdgeWeightedDirectedGraph graph, int source) {

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
        int from = edge.from();
        int to = edge.to();
        if (distTo[to] > edge.weight() + distTo[from]) {
            distTo[to] = edge.weight() + distTo[from];
            edgeTo[to] = from;
            if (indexMinPQ.contains(to)) indexMinPQ.decreaseKey(to, distTo[to]);
            else indexMinPQ.insert(to, distTo[to]);
        }
    }

    private void checkNegativeEdges(EdgeWeightedDirectedGraph graph) {
        for (DirectedEdge edge : graph.edgesIterator()) {
            if (edge.weight() < 0) throw new IllegalArgumentException("Negative edges!");
        }
    }

    /**
     * Returns true if there is a path from the source
     * to the vertex. Otherwise, returns false.
     *
     * @param vertex
     */
    public boolean hasPathTo(int vertex) {
        return distTo[vertex] < INFINITY;
    }

    /**
     * Returns the distance from the source to
     * the given vertex. If the vertex is
     * unreachable, the distance returned is
     * Double.POSITIVE_INFINITY.
     *
     * @param vertex
     */
    public double distTo(int vertex) {
        return distTo[vertex];
    }

    /**
     * Returns the edges from the source to the given vertex,
     * is such a path exists. Otherwise, it returns null.
     *
     * @param vertex
     */
    public Iterable<Integer> pathTo(int vertex) {
        if (!hasPathTo(vertex)) return null;

        Stack<Integer> edges = new Stack<Integer>();

        int node;
        for (node = vertex; distTo[node] != 0; node = edgeTo[node]) {
            edges.push(node);
        }

        edges.push(node);
        return edges;

    }
}

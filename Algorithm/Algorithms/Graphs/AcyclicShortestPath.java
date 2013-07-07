package Graphs;

import DataStructure.Stack;

import java.util.Arrays;

public class AcyclicShortestPath {

    private double[] distTo;
    private int[] edgeTo;

    /**
     * Finds the shortest path from a source
     * to every reachable vertex in a edge weighted
     * directed graph.
     * <p/>
     * Warning: the graph must be acyclic.
     *
     * @param graph
     * @param source
     */
    public AcyclicShortestPath(EdgeWeightedDirectedGraph graph, int source) {

        TopologicalOrder order = new TopologicalOrder(graph);

        distTo = new double[graph.vertices()];
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        distTo[source] = 0.0;

        edgeTo = new int[graph.vertices()];

        for (Integer vertex : order.topologicalOrder()) {
            for (DirectedEdge edge : graph.adjEdges(vertex)) {
                relax(edge);
            }
        }

    }

    private void relax(DirectedEdge edge) {
        int from = edge.source();
        int to = edge.dest();
        if (distTo[to] > edge.weight() + distTo[from]) {
            distTo[to] = edge.weight() + distTo[from];
            edgeTo[to] = from;
        }
    }

    /**
     * Returns true if there is a path from the source
     * to the vertex. Otherwise, returns false.
     *
     * @param vertex
     */
    public boolean hasPathTo(int vertex) {
        return distTo[vertex] != Double.POSITIVE_INFINITY;
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

    public static void main(String[] args) {
        EdgeWeightedDirectedGraph graph = new EdgeWeightedDirectedGraph(6);
        graph.addEdge(0, 1, 2);
        graph.addEdge(1, 2, 2);
        graph.addEdge(2, 3, 2);
        graph.addEdge(3, 4, 2);
        graph.addEdge(0, 5, 10);
        graph.addEdge(4, 5, 5);

        AcyclicShortestPath path = new AcyclicShortestPath(graph, 0);
        for (Integer vertex : path.pathTo(5)) {
            System.out.print(vertex + " -> ");
        }


    }

}

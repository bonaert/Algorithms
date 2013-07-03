package Graphs;

import DataStructure.IndexMinPriorityQueue;
import DataStructure.Queue;

import java.util.Arrays;

public class EagerPrimMinimumSpanningTree {

    private boolean[] marked;
    private double[] distTo;
    private UndirectedEdge[] edgeTo;
    private IndexMinPriorityQueue<UndirectedEdge> minEdges;
    private double weight;

    /**
     * Given a graph, find the minimum spanning tree in it.
     *
     * @param graph, the provided graph
     */
    public EagerPrimMinimumSpanningTree(EdgeWeightedUndirectedGraph graph) {

        marked = new boolean[graph.vertices()];
        distTo = new double[graph.vertices()];
        edgeTo = new UndirectedEdge[graph.vertices()];
        minEdges = new IndexMinPriorityQueue<UndirectedEdge>(graph.vertices());

        Arrays.fill(distTo, Double.POSITIVE_INFINITY);

        for (int i = 0; i < graph.vertices(); i++) {
            if (!marked[i]) prim(graph, i);
        }
    }

    private void prim(EdgeWeightedUndirectedGraph graph, int source) {
        addEdges(graph, source);

        while (!minEdges.isEmpty()) {
            int dest = minEdges.deleteMin();
            addEdges(graph, dest);
        }
    }

    private void addEdges(EdgeWeightedUndirectedGraph graph, int source) {
        marked[source] = true;
        for (UndirectedEdge edge : graph.adjEdges(source)) {
            int dest = edge.other(source);
            if (marked[dest]) continue;

            if (distTo[dest] > edge.weight()) {
                edgeTo[dest] = edge;
                distTo[dest] = edge.weight();
                if (minEdges.contains(dest)) minEdges.change(dest, edge);
                else minEdges.insert(dest, edge);
            }
        }
    }


    /**
     * Returns the edges of the minimum spanning tree.
     */
    public Iterable<UndirectedEdge> edges() {
        Queue<UndirectedEdge> edges = new Queue<UndirectedEdge>();
        for (UndirectedEdge edge : edgeTo) {
            edges.enqueue(edge);
        }
        return edges;
    }

    /**
     * Returns the weight of the minimum spanning tree.
     */
    public double weight() {
        weight = 0.0;
        for (UndirectedEdge edge : edges()) {
            weight += edge.weight();
        }
        return weight;
    }


}

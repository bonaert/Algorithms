package Graphs;

import DataStructure.MinimumPriorityQueue;
import DataStructure.Queue;

public class LazyPrimMinimumSpanningTree {

    private boolean[] marked;
    private Queue<UndirectedEdge> edges;
    private MinimumPriorityQueue<UndirectedEdge> minEdges;
    private double weight;

    /**
     * Given a graph, find the minimum spanning tree in it.
     *
     * @param graph, the provided graph
     */
    public LazyPrimMinimumSpanningTree(EdgeWeightedUndirectedGraph graph) {

        marked = new boolean[graph.vertices()];
        edges = new Queue<UndirectedEdge>();
        minEdges = new MinimumPriorityQueue<UndirectedEdge>();


        for (int i = 0; i < graph.vertices(); i++) {
            if (!marked[i]) prim(graph, i);
        }
    }

    private void prim(EdgeWeightedUndirectedGraph graph, int source) {
        addEdges(graph, source);

        while (!minEdges.isEmpty()) {

            UndirectedEdge edge = minEdges.deleteMin();
            int v = edge.vertexA();
            int w = edge.vertexB();
            if (marked[v] && marked[w]) continue;

            edges.enqueue(edge);
            weight += edge.weight();

            if (!marked[v]) addEdges(graph, v);
            if (!marked[w]) addEdges(graph, w);
        }
    }

    private void addEdges(EdgeWeightedUndirectedGraph graph, int source) {
        marked[source] = true;
        for (UndirectedEdge undirectedEdge : graph.adjEdges(source)) {
            if (!marked[undirectedEdge.other(source)]) {
                minEdges.insert(undirectedEdge);
            }
        }
    }


    /**
     * Returns the edges of the minimum spanning tree.
     */
    public Iterable<UndirectedEdge> edges() {
        return minEdges;
    }

    /**
     * Returns the wheight of the minimum spanning tree.
     */
    public double weight() {
        return weight;
    }


}

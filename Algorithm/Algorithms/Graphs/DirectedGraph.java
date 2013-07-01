package Graphs;

import DataStructure.Bag;

public class DirectedGraph implements Graph {

    private int edges;
    private final int vertices;
    private Bag<Integer>[] adj;

    /**
     * Build a graph with directed edges, that is, edges from one vertex
     * to another, but not the inverse.
     *
     * @param numberVertices
     */
    public DirectedGraph(int numberVertices) {
        vertices = numberVertices;
        adj = (Bag<Integer>[]) new Bag[numberVertices];
        edges = 0;
    }

    public DirectedGraph(DirectedGraph graph){

        edges = graph.edges;
        vertices = graph.vertices;
        adj = (Bag<Integer>[]) new Bag[graph.vertices];
        for (int i = 0; i < graph.vertices; i++) {
            adj[0] = new Bag(graph.adj(i));
        }


    }

    /**
     * Adds an edge from the source vertex to the destination vertex (source -> destination).
     *
     * @param sourceVertex
     * @param destVertex
     */
    public void addEdge(int sourceVertex, int destVertex) {
        check(sourceVertex);
        check(destVertex);

        adj[sourceVertex].add(destVertex);

        edges++;
    }

    /**
     * Returns the edges immediately reachable from the vertex, that is,
     * the edges that have a directed edge from the vertex.
     */
    public Iterable<Integer> adj(int vertex) {
        return adj[vertex];
    }

    private void check(int node) {
        if (node < 0 || node >= vertices) throw new IllegalArgumentException();
    }

    /**
     * Returns the number of edges in this graph.
     */
    public int edges() {
        return edges;
    }

    /**
     * Returns the number of vertices in this vertices.
     */
    public int vertices() {
        return vertices;
    }

    /**
     * Returns the graph with edges flipped.
     */
    public DirectedGraph reverse() {

        DirectedGraph reverseGraph = new DirectedGraph(vertices);

        for (int source = 0; source < vertices; source++) {
            for (Integer dest : adj(source)) {
                reverseGraph.addEdge(dest, source);
            }
        }

        return reverseGraph;
    }
}

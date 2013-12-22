package Graphs;

import DataStructure.Bag;

public class UndirectedGraph implements Graph{

    private final int vertices;
    private int edges;
    private Bag<Integer>[] adj;

    /**
     * Build an undirected graph with the provided number of vertices.
     *
     * @param numberVertices
     */
    public UndirectedGraph(int numberVertices) {
        vertices = numberVertices;
        edges = 0;
        adj = (Bag<Integer>[]) new Bag[numberVertices];
        for (int i = 0; i < numberVertices; i++) {
            adj[i] = new Bag<Integer>();
        }
    }

    /**
     * Returns the number of vertices in this graph.
     */
    public int vertices() {
        return vertices;
    }

    /**
     * Returns the number of edges in this graph.
     */
    public int edges() {
        return edges;
    }

    /**
     * Returns the edges immediately reacheble from the vertex, that is,
     * the edges that have a undirected edge from the vertex.
     */
    public Iterable<Integer> adj(int vertex) {
        return adj[vertex];
    }

    /**
     * Adds an edge from vertexA to the vertexB (vertexA -> vertexB)
     * and from vertexB to vertexA (vertexB -> vertexA).
     *
     * @param vertexA
     * @param vertexB
     */
    public void addEdge(int vertexA, int vertexB) {
        check(vertexA);
        check(vertexB);

        adj[vertexA].add(vertexB);
        adj[vertexB].add(vertexA);
    }


    private void check(int vertex) {
        if (vertex < 0 || vertex > vertices) throw new IllegalArgumentException("Invalid vertex");
    }
}

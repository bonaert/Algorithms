package Algorithms.Graphs;

public class EdgeWheightedUndirectedGraph implements WheightedGraph {

    private int edges;
    private final int vertices;
    private Bag<UndirectedEdge>[] adj;

    /**
     * Build a graph with wheighted directed edges, that is, edges from one vertex
     * to another, but not the inverse.
     *
     * @param numberVertices
     */
    public EdgeWheightedUndirectedGraph(int numberVertices) {
        vertices = numberVertices;
        adj = (Bag<UndirectedEdge>[]) new Bag[numberVertices];
        edges = 0;
    }

    /**
     * Adds an wheighted edge from the source vertex to the destination vertex (source -> destination).
     *
     * @param vertexA
     * @param vertexB
     * @param wheight
     */
    public void addEdge(int vertexA, int vertexB, int wheight) {
        check(vertexA);
        check(vertexB);

        adj[vertexA].add(new UndirectedEdge(vertexA, vertexB, wheight));
        adj[vertexB].add(new UndirectedEdge(vertexB, vertexA, wheight));

        edges++;
    }

    /**
     * Adds an wheighted edge from the source vertex to the destination vertex (source -> destination).
     *
     * @param edge
     */
    public void addEdge(UndirectedEdge edge) {
        check(edge.vertexA());
        check(edge.vertexB());

        adj[edge.vertexA()].add(edge);
        adj[edge.vertexB()].add(edge);

        edges++;
    }


    /**
     * Returns the wheighted edges immediately reachable from the vertex, that is,
     * the edges that have a directed edge from the vertex.
     */
    public Iterable<UndirectedEdge> adjEdges(int vertex) {
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
}

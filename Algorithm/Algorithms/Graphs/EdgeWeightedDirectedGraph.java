package Graphs;

import DataStructure.Bag;

import java.util.Arrays;

public class EdgeWeightedDirectedGraph implements WheightedGraph {

    private int edges;
    private final int vertices;
    private Bag<DirectedEdge>[] adj;

    /**
     * Build a graph with wheighted directed edges, that is, edges from one vertex
     * to another, but not the inverse.
     *
     * @param numberVertices
     */
    public EdgeWeightedDirectedGraph(int numberVertices) {
        vertices = numberVertices;
        adj = (Bag<DirectedEdge>[]) new Bag[numberVertices];
        edges = 0;
        Arrays.fill(adj, new Bag<DirectedEdge>());
    }

    /**
     * Adds an wheighted edge from the source vertex to the destination vertex (source -> destination).
     *
     * @param sourceVertex
     * @param destVertex
     * @param wheight
     */
    public void addEdge(int sourceVertex, int destVertex, int wheight) {
        check(sourceVertex);
        check(destVertex);

        adj[sourceVertex].add(new DirectedEdge(sourceVertex, destVertex, wheight));

        edges++;
    }

    /**
     * Adds an wheighted edge from the source vertex to the destination vertex (source -> destination).
     *
     * @param sourceVertex
     * @param destVertex
     * @param wheight
     */
    public void addEdge(int sourceVertex, int destVertex, double wheight) {
        check(sourceVertex);
        check(destVertex);

        adj[sourceVertex].add(new DirectedEdge(sourceVertex, destVertex, wheight));

        edges++;
    }

    /**
     * Adds an edge from the source vertex to the destination vertex (source -> destination).
     *
     * @param edge
     */
    public void addEdge(DirectedEdge edge) {
        check(edge.source());
        check(edge.dest());

        adj[edge.source()].add(edge);

        edges++;
    }


    /**
     * Returns the wheighted edges immediately reachable from the vertex, that is,
     * the edges that have a directed edge from the vertex.
     */
    public Iterable<DirectedEdge> adjEdges(int vertex) {
        return adj[vertex];
    }

    /**
     * Returns all the edges in the current graph.
     */
    public Iterable<DirectedEdge> edgesIterator() {
        Bag<DirectedEdge> directedEdges = new Bag<DirectedEdge>();
        for (int i = 0; i < vertices; i++) {
            for (DirectedEdge edge : adj[i]) {
                directedEdges.add(edge);
            }
        }
        return directedEdges;
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
    public EdgeWeightedDirectedGraph reverse() {

        EdgeWeightedDirectedGraph reverseGraph = new EdgeWeightedDirectedGraph(vertices);

        for (int source = 0; source < vertices; source++) {
            for (DirectedEdge edge : adjEdges(source)) {
                reverseGraph.addEdge(edge.dest(), source, edge.weight());
            }
        }

        return reverseGraph;
    }
}

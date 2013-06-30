package Algorithms.Graphs;

public class DirectedEdge implements Edge {
    private int source, dest;
    private double wheight;

    public DirectedEdge(int source, int dest, double wheight) {
        this.source = source;
        this.dest = dest;
        this.wheight = wheight;
    }

    /**
     * Return the source of this edge.
     */
    public int source() {
        return source;
    }

    /**
     * Return the destination of this edge.
     *
     * @return
     */
    public int dest() {
        return dest;
    }

    /**
     * Returns an vertex of this edge.
     *
     * @return
     */
    public int either() {
        return source;
    }

    /**
     * Return the other vertex of this edge.
     */
    public int other(int vertex) {
        if (vertex == source) return dest;
        if (vertex == dest) return source;
        throw new IllegalArgumentException();
    }

    /**
     * Returns the wheight of this edge.
     */
    public double wheight() {
        return wheight;
    }
}

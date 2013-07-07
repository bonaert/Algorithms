package Graphs;

public class DirectedEdge implements Edge, Comparable<DirectedEdge> {
    private int source, dest;
    private double weight;

    public DirectedEdge(int source, int dest, double weight) {
        this.source = source;
        this.dest = dest;
        this.weight = weight;
    }

    /**
     * Return the source of this edge.
     */
    public int source() {
        return source;
    }

    /**
     * Return the destination of this edge.
     */
    public int dest() {
        return dest;
    }

    /**
     * Returns a vertex of this edge.
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
     * Returns the weight of this edge.
     */
    public double weight() {
        return weight;
    }

    /**
     * Return -1 if this edge has a greater weight than
     * the other edge, 0 if both weights are equals and
     * 1 otherwise.
     *
     * @param otherEdge, the other edge
     */
    public int compareTo(DirectedEdge otherEdge) {
        double otherWeight = otherEdge.weight;
        return (weight > otherWeight) ? 1 : (weight < otherWeight ? -1 : 0);
    }

    /**
     * String representation of the directed edge.
     */
    public String toString() {
        return source + " -> " + dest;
    }

}

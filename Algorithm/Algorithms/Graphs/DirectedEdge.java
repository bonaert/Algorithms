package Graphs;

public class DirectedEdge implements Edge, Comparable<DirectedEdge> {
    private int from, to;
    private double weight;

    public DirectedEdge(int from, int to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    /**
     * Return the source of this edge.
     */
    public int from() {
        return from;
    }

    /**
     * Return the destination of this edge.
     */
    public int to() {
        return to;
    }

    /**
     * Returns a vertex of this edge.
     */
    public int either() {
        return from;
    }

    /**
     * Return the other vertex of this edge.
     */
    public int other(int vertex) {
        if (vertex == from) return to;
        if (vertex == to) return from;
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
        return from + " -> " + to;
    }

}

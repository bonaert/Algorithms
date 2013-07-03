package Graphs;

public class UndirectedEdge implements Edge, Comparable<UndirectedEdge> {

    private int vertexA, vertexB;
    private double wheight;

    public UndirectedEdge(int vertexA, int vertexB, double wheight) {
        this.vertexA = vertexA;
        this.vertexB = vertexB;
        this.wheight = wheight;
    }

    /**
     * Return one of the vertex of this edge.
     */
    public int either() {
        return vertexA;
    }

    public int vertexA() {
        return vertexA;
    }

    public int vertexB() {
        return vertexB;
    }

    /**
     * Return the other vertex of this edge.
     */
    public int other(int vertex) {
        if (vertex == vertexA) return vertexB;
        if (vertex == vertexB) return vertexA;
        throw new IllegalArgumentException();
    }

    /**
     * Returns the weight of this edge.
     */
    public double weight() {
        return wheight;
    }

    /**
     * Return -1 if this edge has a greater weight than
     * the other edge, 0 if both weights are equals and
     * 1 otherwise.
     *
     * @param otherEdge, the other edge
     */
    public int compareTo(UndirectedEdge otherEdge) {
        double otherWeight = otherEdge.wheight;
        return (wheight > otherWeight) ? 1 : (wheight < otherWeight ? -1 : 0);
    }


}

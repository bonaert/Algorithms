package Algorithms.Graphs;

public class UndirectedEdge implements Edge {

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
     * Returns the wheight of this edge.
     */
    public double wheight() {
        return wheight;
    }


}

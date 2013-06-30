package Algorithms.Graphs;

public interface WheightedGraph {

    public int edges();

    public int vertices();

    public Iterable adjEdges(int i);

    public void addEdge(int source, int dest, int wheight);


}

public interface Graph {

    public int edges();

    public int vertices();

    public Iterable<Integer> adj(int i);

    public void addEdge(int source, int dest);


}

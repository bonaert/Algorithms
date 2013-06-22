public class DepthFirstSearch {
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;
    private int[] edgeTo;
    private int source;

    /**
     * Uses the Depth First Search algorithm on the provided graph.
     * Through this algorithm, the unreachable vertices are detected
     * and the paths from the source vertex to the reachable vertex are found.
     *
     * @param graph
     * @param source
     */
    public DepthFirstSearch(Graph graph, int source) {
        marked = new boolean[graph.vertices()];
        edgeTo = new int[graph.vertices()];

        bfs(graph, source);
    }

    private void bfs(Graph graph, int source) {

        Stack<Integer> verticesToVisit = new Stack<Integer>();
        verticesToVisit.push(source);

        while (!verticesToVisit.isEmpty()) {
            int currentVertex = verticesToVisit.pop();

            for (Integer adjVertex : graph.adj(currentVertex)) {

                if (!marked[adjVertex]) {
                    edgeTo[adjVertex] = currentVertex;
                    verticesToVisit.push(adjVertex);
                }

            }
        }

    }

    /**
     * Returns {@code true} if there is a path from the source to the vertex.
     * Otherwise, returns {@code false}.
     *
     * @param vertex
     * @return
     */
    public boolean hasPathTo(int vertex) {
        return marked[vertex];
    }

    /**
     * Returns a path from the source vertex to the provided
     * vertex, in that order. The path may not be the shortest
     * path to the vertex. If the vertex is unreachable,
     * return null.
     *
     * @param vertex
     */
    public Iterable<Integer> pathTo(int vertex) {
        if (!marked[vertex]) return null;

        Stack<Integer> path = new Stack<Integer>();

        for (int current = vertex; current != source; current = edgeTo[vertex]) {
            path.push(current);
        }

        path.push(source);

        return path;
    }


}

package Algorithms.Graphs;

import java.util.Arrays;

public class BreadthFirstSearch {
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;

    /**
     * Uses the Breadth First Search algorithm on the provided graph.
     * Through this algorithm, the shortest path to a vertex is found
     * and the unreachable vertices are detected. The shortest distance from the
     * source to the vertex is found.
     *
     * @param graph
     * @param source
     */
    public BreadthFirstSearch(Graph graph, int source) {
        marked = new boolean[graph.vertices()];
        edgeTo = new int[graph.vertices()];
        distTo = new int[graph.vertices()];

        Arrays.fill(distTo, INFINITY);

        bfs(graph, source);
    }

    /**
     * Uses the Breadth First Search algorithm on the provided graph.
     * Through this algorithm, the shortest path to a vertex is found
     * and the unreachable vertices are detected. The shortest distance from the
     * source to the vertex is found.
     *
     * @param graph
     * @param sources
     */
    public BreadthFirstSearch(Graph graph, Iterable<Integer> sources) {
        marked = new boolean[graph.vertices()];
        edgeTo = new int[graph.vertices()];
        distTo = new int[graph.vertices()];

        Arrays.fill(distTo, INFINITY);

        bfs(graph, sources);
    }

    private void bfs(Graph graph, Iterable<Integer> sources) {

        Queue<Integer> verticesToVisit = new Queue<Integer>();

        for (Integer source : sources) {
            distTo[source] = 0;
            verticesToVisit.enqueue(source);
        }

        while (!verticesToVisit.isEmpty()) {
            int currentVertex = verticesToVisit.dequeue();

            for (Integer adjVertex : graph.adj(currentVertex)) {

                if (!marked[adjVertex]) {
                    distTo[adjVertex] = distTo[currentVertex] + 1;
                    edgeTo[adjVertex] = currentVertex;
                    verticesToVisit.enqueue(adjVertex);
                }

            }
        }

    }

    private void bfs(Graph graph, int source) {

        Queue<Integer> verticesToVisit = new Queue<Integer>();
        verticesToVisit.enqueue(source);
        distTo[source] = 0;

        while (!verticesToVisit.isEmpty()) {
            int currentVertex = verticesToVisit.dequeue();

            for (Integer adjVertex : graph.adj(currentVertex)) {

                if (!marked[adjVertex]) {
                    distTo[adjVertex] = distTo[currentVertex] + 1;
                    edgeTo[adjVertex] = currentVertex;
                    verticesToVisit.enqueue(adjVertex);
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
     * Returns the distance from the source to the vertex.
     * The distance returned is the minimum distance along
     * the shortest path to the vertex.
     * Returns Integer.MAX_VALUE if the vertex is unreachable.
     *
     * @param vertex
     */
    public int distTo(int vertex) {
        return distTo[vertex];
    }

    /**
     * Returns the shortest path from the source vertex to the provided
     * vertex, in that order. If the vertex is unreachable,
     * return null.
     *
     * @param vertex
     */
    public Iterable<Integer> pathTo(int vertex) {
        if (!marked[vertex]) return null;

        Stack<Integer> path = new Stack<Integer>();

        int current;
        for (current = vertex; distTo[current] != 0; current = edgeTo[vertex]) {
            path.push(current);
        }

        path.push(current);

        return path;
    }


}

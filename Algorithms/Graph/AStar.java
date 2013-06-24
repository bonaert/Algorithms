import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AStar {

    private Set<Integer> visitedNodes;
    private IndexMinPriorityQueue nodesToVisit;
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private int goal;


    /**
     * Find the shortest path from the source to the goal.
     * Currently, the heuristic return 0, so this algorithm is equal
     * to Dijkstra's algorithm.
     *
     * @param graph
     * @param source
     * @param goal
     */
    public AStar(EdgeWheightedDirectedGraph graph, int source, int goal) {

        distTo = new double[graph.vertices()];
        edgeTo = new DirectedEdge[graph.vertices()];
        visitedNodes = new HashSet<Integer>();
        nodesToVisit = new IndexMinPriorityQueue(graph.vertices());
        this.goal = goal;

        nodesToVisit.insert(source, heuristic(source, goal));

        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        distTo[source] = 0;

        search(graph);
    }

    private void search(EdgeWheightedDirectedGraph graph) {

        while (!nodesToVisit.isEmpty()) {

            int current = nodesToVisit.deleteMin();
            if (current == goal) return;

            visitedNodes.add(current);

            for (DirectedEdge neighborEdge : graph.adjEdges(current)) {

                int neighbor = neighborEdge.dest();
                double score = distTo[current] + neighborEdge.wheight();

                if (visitedNodes.contains(neighbor) && score >= distTo[neighbor]) {
                    continue;
                }

                if (!nodesToVisit.contains(neighbor) && score < distTo[neighbor]) {
                    edgeTo[neighbor] = neighborEdge;
                    distTo[neighbor] = score;
                    nodesToVisit.insert(neighbor, score + heuristic(neighbor, goal));
                }

            }

        }
    }

    /**
     * Fundamental heuristic:
     * - if 0, this algorithms is Dijkstra's algorithm
     * - if bigger than distTo, greedy
     */
    private double heuristic(int nodeA, int nodeB) {
        return 0;
    }

    /**
     * Returns the distance to the goal
     */
    public double distToGoal() {
        return distTo[goal];
    }

    /**
     * Returns all the edges from the source to the goal
     */
    public Iterable<DirectedEdge> pathToGoal() {
        if (distTo[goal] == Double.POSITIVE_INFINITY) return null;

        Stack<DirectedEdge> edges = new Stack<DirectedEdge>();

        for (DirectedEdge edge = edgeTo[goal]; edge != null; edge = edgeTo[edge.source()]) {
            edges.push(edge);
        }

        return edges;
    }

    public static void main(String[] args) {

        // Setup graph
        EdgeWheightedDirectedGraph graph = new EdgeWheightedDirectedGraph(6);
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 2, 5);
        graph.addEdge(2, 3, 3);
        graph.addEdge(3, 4, 1);
        graph.addEdge(4, 5, 1);
        graph.addEdge(2, 5, 6);


        AStar aStar = new AStar(graph, 0, 5);
        for (DirectedEdge directedEdge : aStar.pathToGoal()) {
            System.out.println(directedEdge.source() + " -> " + directedEdge.dest());
        }

        System.out.println(aStar.distToGoal());

    }


}

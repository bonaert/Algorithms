package Graphs;


import DataStructure.Stack;

public class EdgeWeightedGraphCycle {

    private boolean[] marked, onStack;
    private DirectedEdge[] edgeTo;
    private Stack<DirectedEdge> cycle;

    public EdgeWeightedGraphCycle(EdgeWeightedDirectedGraph graph) {


        marked = new boolean[graph.vertices()];
        onStack = new boolean[graph.vertices()];
        edgeTo = new DirectedEdge[graph.vertices()];
        for (int v = 0; v < graph.vertices(); v++) {
            if (!marked[v]) {
                depthFirstSearch(graph, v);
            }
        }
    }

    private void depthFirstSearch(EdgeWeightedDirectedGraph graph, int source) {

        onStack[source] = true;
        marked[source] = true;

        for (DirectedEdge edge : graph.adjEdges(source)) {

            int to = edge.to();

            if (cycle != null) return;

            else if (!marked[to]) {
                edgeTo[to] = edge;
                depthFirstSearch(graph, to);

                // If there is a cycle, build it
            } else if (onStack[to]) {
                cycle = new Stack<DirectedEdge>();
                while (edge.from() != to) {
                    cycle.push(edge);
                    edge = edgeTo[edge.from()];
                }
                cycle.push(edge);
            }
        }

        onStack[source] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<DirectedEdge> cycle() {
        return cycle;
    }


}

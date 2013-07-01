package Graphs;


import DataStructure.Stack;

public class EdgeWeightedGraphCycle {

    private boolean[] marked, onStack;
    private DirectedEdge edgeTo[];
    private Stack<DirectedEdge> cycle;

    public EdgeWeightedGraphCycle(EdgeWeightedDirectedGraph graph) {


        marked = new boolean[graph.vertices()];
        onStack = new boolean[graph.vertices()];
        edgeTo = new DirectedEdge[graph.vertices()];
        for (int i = 0; i < graph.vertices(); i++) {
            if (!marked[i]) {
                depthFirstSearch(graph, i);
            }
        }
    }

    private void depthFirstSearch(EdgeWeightedDirectedGraph graph, int source) {

        // Already found cycle
        if (cycle != null) return;

        Stack<Integer> verticesToVisit = new Stack<Integer>();
        verticesToVisit.push(source);

        while (!verticesToVisit.isEmpty()) {

            int vertex = verticesToVisit.pop();
            onStack[vertex] = true;
            marked[vertex] = true;

            for (DirectedEdge neighborEdge : graph.adjEdges(vertex)) {

                // If there is a cycle, build it
                if (onStack[neighborEdge.dest()]) {

                    cycle = new Stack<DirectedEdge>();

                    for (DirectedEdge current = edgeTo[vertex]; current.dest() != neighborEdge.dest(); current = edgeTo[neighborEdge.source()]) {
                        cycle.push(current);
                    }
                    cycle.push(neighborEdge);

                    return;


                    // else, continue searching
                } else if (!marked[neighborEdge.dest()]) {
                    edgeTo[neighborEdge.dest()] = neighborEdge;
                    verticesToVisit.push(neighborEdge.dest());
                }

            }

            onStack[vertex] = false;
        }
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<DirectedEdge> cycle() {
        return cycle;
    }


}

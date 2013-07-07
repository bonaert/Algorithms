package Graphs;


import DataStructure.Stack;

public class EdgeWeightedGraphCycle {

    private boolean[] marked, onStack;
    private DirectedEdge[] edgeTo;
    private Stack<DirectedEdge> cycle;

    /*public EdgeWeightedGraphCycle(EdgeWeightedDirectedGraph graph) {


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

            int dest = edge.dest();

            if (cycle != null) return;

            else if (!marked[dest]) {
                edgeTo[dest] = edge;
                depthFirstSearch(graph, dest);

                // If there is a cycle, build it
            } else if (onStack[dest]) {
                cycle = new Stack<DirectedEdge>();
                while (edge.source() != dest) {
                    cycle.push(edge);
                    edge = edgeTo[edge.source()];
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
    }*/

    public EdgeWeightedGraphCycle(EdgeWeightedDirectedGraph G) {
        marked = new boolean[G.vertices()];
        onStack = new boolean[G.vertices()];
        edgeTo = new DirectedEdge[G.vertices()];
        for (int v = 0; v < G.vertices(); v++)
            if (!marked[v]) dfs(G, v);

    }


    // check that algorithm computes either the topological order or finds a directed cycle
    private void dfs(EdgeWeightedDirectedGraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (DirectedEdge e : G.adjEdges(v)) {
            int w = e.dest();

            // short circuit if directed cycle found
            if (cycle != null) return;

                //found new vertex, so recur
            else if (!marked[w]) {
                edgeTo[w] = e;
                dfs(G, w);
            }

            // trace back directed cycle
            else if (onStack[w]) {
                cycle = new Stack<DirectedEdge>();
                while (e.source() != w) {
                    System.out.println(e);
                    System.out.println(e.source());
                    System.out.println(w);
                    cycle.push(e);
                    e = edgeTo[e.source()];
                }
                cycle.push(e);
            }
        }

        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<DirectedEdge> cycle() {
        return cycle;
    }

}

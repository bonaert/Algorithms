package Graphs;

import DataStructure.Stack;

public class GraphCycle {

    private boolean[] marked, onStack;
    private int edgeTo[];
    private Stack<Integer> cycle;


    public GraphCycle(UndirectedGraph graph) {


        marked = new boolean[graph.vertices()];
        onStack = new boolean[graph.vertices()];
        edgeTo = new int[graph.vertices()];
        for (int i = 0; i < graph.vertices(); i++) {
            if (!marked[i]) {
                depthFirstSearch(graph, i);
            }
        }
    }

    private void depthFirstSearch(UndirectedGraph graph, int source) {

        // Already found cycle
        if (cycle != null) return;

        Stack<Integer> verticesToVisit = new Stack<Integer>();
        verticesToVisit.push(source);

        while (!verticesToVisit.isEmpty()) {

            int vertex = verticesToVisit.pop();
            onStack[vertex] = true;
            marked[vertex] = true;

            for (Integer neighbor : graph.adj(vertex)) {

                // If there is a cycle, build it
                if (onStack[neighbor]) {

                    cycle = buildCycle(vertex, neighbor);
                    return;

                    // else, continue searching
                } else if (!marked[neighbor]) {
                    edgeTo[neighbor] = vertex;
                    verticesToVisit.push(neighbor);
                }

            }

            onStack[vertex] = false;
        }
    }


    public GraphCycle(DirectedGraph graph) {


        marked = new boolean[graph.vertices()];
        onStack = new boolean[graph.vertices()];
        edgeTo = new int[graph.vertices()];
        for (int i = 0; i < graph.vertices(); i++) {
            if (!marked[i]) {
                depthFirstSearch(graph, i);
            }
        }
    }

    private void depthFirstSearch(DirectedGraph graph, int source) {

        // Already found cycle
        if (cycle != null) return;

        Stack<Integer> verticesToVisit = new Stack<Integer>();
        verticesToVisit.push(source);

        while (!verticesToVisit.isEmpty()) {

            int vertex = verticesToVisit.pop();
            onStack[vertex] = true;
            marked[vertex] = true;

            for (Integer neighbor : graph.adj(vertex)) {

                // If there is a cycle, build it
                if (onStack[neighbor]) {

                    buildCycle(vertex, neighbor);

                    return;


                    // else, continue searching
                } else if (!marked[neighbor]) {
                    edgeTo[neighbor] = vertex;
                    verticesToVisit.push(neighbor);
                }

            }

            onStack[vertex] = false;
        }
    }

    private Stack<Integer> buildCycle(int vertex, Integer neighbor) {
        Stack<Integer> cycle = new Stack<Integer>();

        for (int current = vertex; current != neighbor; current = edgeTo[neighbor]) {
            cycle.push(current);
        }
        cycle.push(neighbor);
        cycle.push(vertex);

        return cycle;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }


}

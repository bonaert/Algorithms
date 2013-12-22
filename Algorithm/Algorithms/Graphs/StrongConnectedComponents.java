package Graphs;

import DataStructure.Queue;
import DataStructure.Stack;

public class StrongConnectedComponents {

    private boolean[] marked;
    private int[] id;
    private int[] size;
    private int count;

    public StrongConnectedComponents(DirectedGraph graph) {

        marked = new boolean[graph.vertices()];
        id = new int[graph.vertices()];
        size = new int[graph.vertices()];
        count = 0;

        DirectedGraph reverseGraph = graph.reverse();
        DepthFirstSearchOrder dfs = new DepthFirstSearchOrder(reverseGraph);


        for (int i : dfs.reversePostOrderIterator()) {
            if (!marked[i]) {
                dfs(graph, i);
                count++;
            }
        }
    }

    private void dfs(Graph graph, int source) {

        Stack<Integer> verticesToVisit = new Stack<Integer>();
        verticesToVisit.push(source);

        while (!verticesToVisit.isEmpty()) {
            int currentVertex = verticesToVisit.pop();

            marked[currentVertex] = true;

            for (Integer adjVertex : graph.adj(currentVertex)) {

                if (!marked[adjVertex]) {
                    verticesToVisit.push(adjVertex);
                }
            }

            id[currentVertex] = count;
            size[count]++;
        }

    }

    public boolean isStronglyConnected(int vertexA, int vertexB) {
        return (id[vertexA] == id[vertexB]);
    }

    public int id(int vertex) {
        return id[vertex];
    }

    public int numComponents() {
        return count;
    }

    public int size(int vertex) {
        return size[id[vertex]];
    }

}

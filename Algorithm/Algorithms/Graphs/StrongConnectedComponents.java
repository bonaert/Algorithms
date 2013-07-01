package Graphs;

import DataStructure.Queue;

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
                breadthFirstSearch(graph, i);
                count++;
            }
        }
    }

    private void breadthFirstSearch(DirectedGraph graph, int source) {

        Queue<Integer> verticesToVisit = new Queue<Integer>();
        verticesToVisit.enqueue(source);

        while (!verticesToVisit.isEmpty()) {
            int vertex = verticesToVisit.dequeue();
            if (!marked[vertex]) {

                for (Integer neighbor : graph.adj(vertex)) {
                    if (!marked[neighbor]) verticesToVisit.enqueue(neighbor);
                }

                marked[vertex] = true;
                id[vertex] = count;
                size[count]++;
            }
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

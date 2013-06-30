package Graphs;

import DataStructure.Queue;

public class ConnectedComponents {

    private boolean[] marked;
    private int[] id;
    private int[] size;
    private int count;

    public ConnectedComponents(UndirectedGraph graph) {

        marked = new boolean[graph.vertices()];
        id = new int[graph.vertices()];
        size = new int[graph.vertices()];
        count = 0;


        for (int i = 0; i < graph.vertices(); i++) {
            if (!marked[i]) {
                breadthFirstSearch(graph, i);
                count++;
            }
        }
    }

    private void breadthFirstSearch(UndirectedGraph graph, int source) {

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

    public ConnectedComponents(DirectedGraph graph) {

        marked = new boolean[graph.vertices()];
        id = new int[graph.vertices()];
        size = new int[graph.vertices()];
        count = 0;


        for (int i = 0; i < graph.vertices(); i++) {
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

    public boolean isConnected(int vertexA, int vertexB) {
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

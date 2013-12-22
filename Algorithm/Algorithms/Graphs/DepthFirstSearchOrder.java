package Graphs;

import DataStructure.Queue;
import DataStructure.Stack;

public class DepthFirstSearchOrder {

    private boolean[] marked;
    private int[] pre;
    private int[] post;
    private Queue<Integer> preOrder;
    private Queue<Integer> postOrder;
    private int preOrderCounter, postOrderCounter;


    public DepthFirstSearchOrder(DirectedGraph graph) {

        marked = new boolean[graph.vertices()];
        pre = new int[graph.vertices()];
        post = new int[graph.vertices()];
        preOrder = new Queue<Integer>();
        postOrder = new Queue<Integer>();

        for (int i = 0; i < graph.vertices(); i++) {
            if (!marked[i]) depthFirstSearch(graph, i);
        }

    }

    public DepthFirstSearchOrder(EdgeWeightedDirectedGraph graph) {

        marked = new boolean[graph.vertices()];
        pre = new int[graph.vertices()];
        post = new int[graph.vertices()];
        preOrder = new Queue<Integer>();
        postOrder = new Queue<Integer>();

        for (int i = 0; i < graph.vertices(); i++) {
            if (!marked[i]) depthFirstSearch(graph, i);
        }
    }

    private void depthFirstSearch(DirectedGraph graph, int source) {

        marked[source] = true;

        // Update pre-order
        pre[preOrderCounter++] = source;
        preOrder.enqueue(source);

        for (Integer neighbor : graph.adj(source)) {
            if (!marked[neighbor]) {
                depthFirstSearch(graph, neighbor);
            }
        }

        // Update post-order
        post[postOrderCounter++] = source;
        postOrder.enqueue(source);
    }

    private void depthFirstSearch(EdgeWeightedDirectedGraph graph, int source) {

        marked[source] = true;

        // Update pre-order
        pre[preOrderCounter++] = source;
        preOrder.enqueue(source);

        for (DirectedEdge neighborEdge : graph.adjEdges(source)) {
            if (!marked[neighborEdge.to()]) {
                depthFirstSearch(graph, neighborEdge.to());
            }
        }

        // Update post-order
        post[postOrderCounter++] = source;
        postOrder.enqueue(source);
    }

    public int preOrder(int vertex) {
        return pre[vertex];
    }

    public int postOrder(int vertex) {
        return post[vertex];
    }

    public Iterable<Integer> preOrderIterator() {
        return preOrder;
    }

    public Iterable<Integer> postOrderIterator() {
        return postOrder;
    }

    public Iterable<Integer> reversePostOrderIterator() {
        Stack<Integer> reversePostOrder = new Stack<Integer>();

        for (Integer vertex : postOrder) {
            reversePostOrder.push(vertex);
        }

        return reversePostOrder;
    }

}

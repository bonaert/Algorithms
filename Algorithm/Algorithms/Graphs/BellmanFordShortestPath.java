package Graphs;

import DataStructure.IndexMinPriorityQueue;
import DataStructure.Queue;

import java.util.Arrays;

public class BellmanFordShortestPath {

    private double[] distTo;
    private int[] edgeTo;
    private boolean[] onQueue;
    private Queue<Integer> verticesToCheck;
    private IndexMinPriorityQueue<Double> indexMinPQ;


    public BellmanFordShortestPath(EdgeWeightedDirectedGraph graph, int source) {

        distTo = new double[graph.vertices()];
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        distTo[source] = 0.0;


        edgeTo = new int[graph.vertices()];
        onQueue = new boolean[graph.vertices()];
        indexMinPQ = new IndexMinPriorityQueue<Double>(graph.vertices());

        verticesToCheck = new Queue<Integer>();
        verticesToCheck.enqueue(source);

        while (!verticesToCheck.isEmpty()) {
            int v = verticesToCheck.dequeue();
            onQueue[v] = false;
            relax(graph, v);
        }


    }


    private void relax(EdgeWeightedDirectedGraph graph, int vertex) {
        for (DirectedEdge edge : graph.adjEdges(vertex)) {
            relax(edge);
        }

    }

    private void relax(DirectedEdge edge) {
        int from = edge.source();
        int to = edge.dest();
        if (distTo[to] > edge.weight() + distTo[from]) {

            distTo[to] = edge.weight() + distTo[from];
            edgeTo[to] = from;

            if (!onQueue[to]) {
                onQueue[to] = true;
                verticesToCheck.enqueue(to);
            }

            if (indexMinPQ.contains(to)) indexMinPQ.decreaseKey(to, distTo[to]);
            else indexMinPQ.insert(to, distTo[to]);
        }
    }

}

package Graphs;

import DataStructure.MinimumPriorityQueue;
import DataStructure.Queue;

public class KruskalMinimumSpanningTree {

    private Queue<UndirectedEdge> minimumSpanningTree = new Queue<UndirectedEdge>();
    private double weight;

    /**
     * Given the edge-weighted graph, it find the minimum spanning tree
     * and its weight.
     *
     * @param graph
     */
    public KruskalMinimumSpanningTree(EdgeWeightedUndirectedGraph graph) {

        MinimumPriorityQueue minEdges = new MinimumPriorityQueue<UndirectedEdge>();
        for (UndirectedEdge edge : graph.edgesIterator()) {
            minEdges.insert(edge);
        }

        int numEdges = 0;

        UnionFind unionFind = new UnionFind(graph.vertices());
        while (!minEdges.isEmpty() && numEdges < graph.vertices() - 1) {

            UndirectedEdge edge = (UndirectedEdge) minEdges.deleteMin();
            int v = edge.vertexA();
            int w = edge.vertexB();

            if (!unionFind.connected(v, w)) {
                unionFind.union(v, w);
                minimumSpanningTree.enqueue(edge);
                numEdges++;
                weight += edge.weight();
            }
        }
    }

    /**
     * Returns the weight of the minimum spanning tree.
     */
    public double weight() {
        return weight;
    }

    /**
     * Returns the edges in the minimum spanning tree.
     */
    public Iterable<UndirectedEdge> minimumSpanningTree() {
        return minimumSpanningTree;
    }
}

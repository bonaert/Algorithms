package Graphs;

public class TopologicalOrder {

    private Iterable<Integer> order;

    public TopologicalOrder(DirectedGraph graph) {

        GraphCycle cycle = new GraphCycle(graph);
        if (!cycle.hasCycle()) {
            DepthFirstSearchOrder dfsOrder = new DepthFirstSearchOrder(graph);
            this.order = dfsOrder.reversePostOrderIterator();
        }
    }

    public TopologicalOrder(EdgeWeightedDirectedGraph graph) {

        EdgeWeightedGraphCycle cycle = new EdgeWeightedGraphCycle(graph);
        if (!cycle.hasCycle()) {
            DepthFirstSearchOrder dfsOrder = new DepthFirstSearchOrder(graph);
            this.order = dfsOrder.reversePostOrderIterator();
        }
    }

    public Iterable<Integer> topologicalOrder() {
        return order;
    }

    public boolean hasOrder() {
        return (order != null);
    }


}

package Graphs;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

public class DijkstraShortestPathTest {

    private EdgeWeightedDirectedGraph graph;
    private DijkstraShortestPath sp;
    private int size = 7;
    private int source = 0;

    @Before
    public void setUp()throws Exception{
        graph = new EdgeWeightedDirectedGraph(size);
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 5);
        graph.addEdge(2, 3, 2);
        graph.addEdge(1, 3, 1);
        graph.addEdge(3, 4, 1);
        graph.addEdge(1, 4, 3);
        graph.addEdge(3, 5, 3);
        graph.addEdge(4, 5, 1);
        graph.addEdge(3, 2, 2);

        sp = new DijkstraShortestPath(graph, source);
    }

    @Test
    public void testHasPathTo() throws Exception {

        for (int i = 0; i < size - 1; i++) {
            assertTrue("Find no path to connected vertex", sp.hasPathTo(i));
        }

        assertFalse("Find path to unconnected vertex", sp.hasPathTo(size - 1));
    }

    @Test
    public void testDistTo() throws Exception {
        double[] distance = new double[] {0, 1, 4, 2, 3, 4, Double.POSITIVE_INFINITY};

        for (int i = 0; i < size; i++) {
            assertEquals(sp.distTo(i), distance[i]);
        }

    }

    @Test
    public void testPathTo() throws Exception {

        int[] vertices = new int[] {0, 1, 3, 4, 5};
        int i = 0;
        for (int vertex : sp.pathTo(size - 2)) {
            assertEquals("Wrong path", vertex, vertices[i]);
            i++;
        }

        assertNull("Find path for unreachable vertex", sp.pathTo(size - 1));

    }
}

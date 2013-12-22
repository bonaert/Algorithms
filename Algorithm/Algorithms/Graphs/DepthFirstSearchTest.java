package Graphs;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

public class DepthFirstSearchTest {
    private UndirectedGraph graph;
    private DepthFirstSearch dfs;
    private int size = 6;

    @Before
    public void setUp() throws Exception {
        graph = new UndirectedGraph(size);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        dfs = new DepthFirstSearch(graph, 0);
    }

    @Test
    public void testHasPathTo() throws Exception {
        for (int i = 0; i < size - 1; i++) {
            assertTrue("Doesn't find path", dfs.hasPathTo(i));
        }

        assertFalse("Find path to unconnected component", dfs.hasPathTo(size - 1));
    }

    @Test
    public void testPathTo() throws Exception {
        int[] vertices = new int[4];
        int i = 0;
        for (Integer vertex : dfs.pathTo(4)) {
            vertices[i] = vertex;
            i++;
        }

        assertEquals(vertices[0], 0);
        assertTrue("Wrong node", vertices[1] == 1 || vertices[1] == 2);
        assertEquals(vertices[2], 3);
        assertEquals(vertices[3], 4);

        assertNull(dfs.pathTo(5));
    }
}

package Graphs;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SymbolGraph {

    private String indexToName[];
    private Map<String, Integer> keysToIndex;
    private UndirectedGraph graph;


    public SymbolGraph(String filename, String delimiter) {

        // First pass: Find all different vertices
        keysToIndex = buildNameToIndexMapping(filename, delimiter);

        // Second pass: build index -> name mapping
        indexToName = buildIndexToNameMapping();

        // Third pass : build graph
        graph = buildGraph(filename, delimiter);
    }

    private Map<String, Integer> buildNameToIndexMapping(String filename, String delimiter) {
        Map<String, Integer> keysToIndex = new HashMap<String, Integer>();

        Scanner scanner = new Scanner(filename);
        while (scanner.hasNext()) {
            String[] vertices = scanner.next().split(delimiter);
            addVerticesToMapping(keysToIndex, vertices);
        }

        return keysToIndex;
    }

    private void addVerticesToMapping(Map<String, Integer> keysToIndex, String[] vertices) {
        for (String vertex : vertices) {
            if (!keysToIndex.containsKey(vertex)) {
                keysToIndex.put(vertex, keysToIndex.size());
            }
        }
    }

    private String[] buildIndexToNameMapping() {
        String[] keys = new String[keysToIndex.size()];
        for (String vertex : keysToIndex.keySet()) {
            keys[keysToIndex.get(vertex)] = vertex;
        }
        return keys;
    }

    private UndirectedGraph buildGraph(String filename, String delimiter) {
        UndirectedGraph graph = new UndirectedGraph(indexToName.length);

        Scanner scanner = new Scanner(filename);

        while (scanner.hasNext()) {
            String[] vertices = scanner.next().split(delimiter);
            addEdges(graph, vertices);
        }
        return graph;
    }

    private void addEdges(UndirectedGraph graph, String[] vertices) {
        int sourceIndex = keysToIndex.get(vertices[0]);
        for (int i = 1; i < vertices.length; i++) {
            graph.addEdge(sourceIndex, keysToIndex.get(vertices[i]));
        }
    }

    public boolean contains(String name) {
        return keysToIndex.containsKey(name);
    }

    public String name(int index) {
        return indexToName[index];
    }

    public int index(String name) {
        return keysToIndex.get(name);
    }

    public UndirectedGraph graph() {
        return graph;
    }

}

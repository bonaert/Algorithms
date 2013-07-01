package Graphs;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SymbolGraph {

    private String keys[];
    private Map<String, Integer> keysToIndex;
    private UndirectedGraph graph;


    public SymbolGraph(String filename, String delimiter) {

        Scanner scanner = new Scanner(filename);
        keysToIndex = new HashMap<String, Integer>();

        // First pass: Find all different vertices
        while (scanner.hasNext()) {

            String[] vertices = scanner.next().split(delimiter);
            for (String vertex : vertices) {
                if (!keysToIndex.containsKey(vertex)) {
                    keysToIndex.put(vertex, keysToIndex.size());
                }
            }
        }

        // Second pass: build index -> key mapping
        keys = new String[keysToIndex.size()];
        for (String vertex : keysToIndex.keySet()) {
            keys[keysToIndex.get(vertex)] = vertex;
        }

        // Third pass : build graph
        graph = new UndirectedGraph(keys.length);
        scanner = new Scanner(filename);
        while (scanner.hasNext()) {
            String[] vertices = scanner.next().split(delimiter);
            int sourceIndex = keysToIndex.get(vertices[0]);
            for (int i = 1; i < vertices.length; i++) {
                graph.addEdge(sourceIndex, keysToIndex.get(vertices[i]));
            }
        }
    }

    public boolean contains(String name) {
        return keysToIndex.containsKey(name);
    }

    public String name(int index) {
        return keys[index];
    }

    public int index(String name) {
        return keysToIndex.get(name);
    }

    public UndirectedGraph graph() {
        return graph;
    }

}

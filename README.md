Algorithms
==========

An implementation of various Algorithms in Java, based on the book Algorithms by Sedgewick and Wayne.

I wrote the code as I followed Sedgewick's Algorithms course, and tried to understand the various algorithms. I wanted to make the implementation clear, readable and easy to understand. I believe it's correct, but since it was my first time learning about these algorithms, so I don't guarantee that the code is bug free.

## Data Structures

* [HashMap](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/DataStructure/HashMap.java)
* [HashSet](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/DataStructure/HashSet.java)
* [Doubly-Linked List](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/DataStructure/DoublyLinkedList.java)
* [Bag](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/DataStructure/Bag.java)
* [Queue](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/DataStructure/Queue.java)
* [Stack](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/DataStructure/Stack.java)
* [Resizing Array Queue](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/DataStructure/ResizingArrayQueue.java)
* [Resizing Array Stack](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/DataStructure/ResizingArrayStack.java)
* [MinStack](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/DataStructure/MinStack.java)
* [Minimum Priority Queue](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/DataStructure/MinimumPriorityQueue.java)

**Cool Application**

- [Median Maintenance](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/MedianMaintenance.java): keeps track of the median of a variable sequence of elements, supporting insertions

## Sorting

* [Selection Sort](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/Sort/Selection.java)
* [Insertion Sort](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/Sort/Insertion.java)
* [Merge Sort](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/Sort/MergeSort.java)
* [Quick Sort](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/Sort/QuickSort.java)
* [Heap Sort](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/Sort/HeapSort.java)

**String sorting**:
* [Least Significant Character Sort](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/Sort/LeastSignificantCharacterSort.java)

**Neat adaptation of quicksort**
* [Quick Select](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/QuickSelect.java): efficiently find's the i-th smallest element in an **unsorted** array

## Trees

* [Binary Search Tree](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/Trees/BinarySearchTree.java)

## Graphs

**Data Structures**

* [Undirected Graph](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/Graphs/UndirectedGraph.java)
* [Directed Graph](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/Graphs/DirectedGraph.java)
* [Edge Weighted Undirected Graph](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/Graphs/EdgeWeightedUndirectedGraph.java)
* [Edge Weighted Directed Graph](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/Graphs/EdgeWeightedDirectedGraph.java)

**Algorithms**

- [Find the topological order](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/Graphs/TopologicalOrder.java)
- [Find cycle (if it exists)](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/Graphs/GraphCycle.java)
- [Find the connected components](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/Graphs/ConnectedComponents.java)
- [Depth First Search graph orderings](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/Graphs/DepthFirstSearchOrder.java): Post Order and Pre Order
- [Depth First Search](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/Graphs/DepthFirstSearch.java) 
- **Find the Minimum Spanning Tree**
  - [Kruskal Algorithm](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/Graphs/KruskalMinimumSpanningTree.java)
  - [Lazy Prim Algorithm](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/Graphs/LazyPrimMinimumSpanningTree.java)
  - [Eager Prim Algorithm](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/Graphs/EagerPrimMinimumSpanningTree.java)
- **Find the shortest path from A to B**:
  - [Breadth First Search](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/Graphs/BreadthFirstSearch.java)
  - [Dijkstra's Algorithm](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/Graphs/DijkstraShortestPath.java): cannot always handle negative weights correctly
  - [Bellman-Ford Algorithm](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/Graphs/BellmanFordShortestPath.java): always handle negative weights correctly
  - [A* Algorithm](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/Graphs/AStar.java): you need to add your appropriate heuristic for your problem
  

**Great applications**:

- [Union Find](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/Graphs/UnionFind.java)
- [Six Degrees from Kevin Bacon](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/Examples/SixDegreesKevinBacon.java)


## Searching

* [Binary Search](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/Trees/BinarySearch.java)

## Useful classes

* [Stop Watch](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/Extra/Stopwatch.java)
* [Statistics](https://github.com/bonaert/Algorithms/blob/master/Algorithm/Algorithms/Extra/Statistics.java): computes the mean, median, mode, size and sum of an array of numbers

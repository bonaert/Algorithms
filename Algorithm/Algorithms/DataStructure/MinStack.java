package DataStructure;

import java.util.NoSuchElementException;

public class MinStack<V extends Comparable<V>> {
    private Node<V> firstNode;
    private Node<V> minNode;
    private int size = 0;

    private static class Node<V> {
        private Node<V> next;
        private Node<V> nextBiggerNode;
        V value;
    }

    public MinStack() {
        firstNode = null;
        minNode = null;
        size = 0;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void insert(V value) {
        firstNode = makeNewFirstNode(value);
        size++;
    }

    private Node<V> makeNewFirstNode(V value) {
        Node<V> newFirstNode = new Node<V>();
        newFirstNode.value = value;
        newFirstNode.next = this.firstNode;

        // [V_new] -> [V_oldMinNode] -> ...
        if (minNode == null || minNode.value.compareTo(value) >= 0) {
            newFirstNode.nextBiggerNode = minNode;
            minNode = newFirstNode;
        } else {
            // ... [V_small] -> [V_new] -> [V_big] ...
            for (Node<V> currentNode = minNode; currentNode != null; currentNode = currentNode.nextBiggerNode) {
                if (valueIsBetween(value, currentNode, currentNode.nextBiggerNode)) {
                    newFirstNode.nextBiggerNode = currentNode.nextBiggerNode;
                    currentNode.nextBiggerNode = newFirstNode;
                    return newFirstNode;
                }
            }
        }

        return newFirstNode;
    }

    private boolean valueIsBetween(V value, Node<V> smallerNode, Node<V> biggerNode) {
        boolean biggerThanSmallerNode = value.compareTo(smallerNode.value) >= 0;
        boolean smallerThanBiggerNode = ((biggerNode == null) || biggerNode.value.compareTo(value) >= 0);
        return biggerThanSmallerNode && smallerThanBiggerNode;
    }


    public V pop() {
        if (isEmpty()) throw new NoSuchElementException("Empty minQueue!");

        if (minNode == firstNode) {
            minNode = minNode.nextBiggerNode;
        }

        V value = firstNode.value;
        firstNode = firstNode.next;

        size--;

        return value;
    }

    public V min() {
        if (isEmpty()) throw new NoSuchElementException("Empty minQueue!");
        return minNode.value;
    }
}

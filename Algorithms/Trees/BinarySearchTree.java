package Algorithms.Trees;

import Algorithms.DataStructure.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinarySearchTree<Key extends Comparable<Key>, Value> {

    private Node root;
    private int size;

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        public Node(Node node) {
            key = node.key;
            value = node.value;
            left = node.left;
            right = node.right;
        }

    }

    public BinarySearchTree() {
        size = 0;
    }

    /**
     * Basic functions
     */

    public int size() {
        return size;
    }


    public boolean isEmpty() {
        return (size == 0);
    }

    public boolean contains(Key key) {
        return (get(key) != null);
    }

    /**
     * Minimum and maximum functions
     */

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("Empty binary search tree!");

        return max(root).key;
    }

    private Node max(Node node) {
        if (node.right == null) return node;
        return max(node.right);
    }


    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("Empty binary search tree!");

        return min(root).key;
    }

    private Node min(Node node) {
        if (node.left == null) return node;
        return max(node.left);
    }

    /**
     * Get functions
     */


    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);

        if (cmp > 0) return get(node.right, key);
        else if (cmp < 0) return get(node.left, key);
        else return node.value;

    }

    /**
     * Insert functions
     */

    public void insert(Key key, Value value) {
        size++;
        root = put(root, key, value);
    }

    private Node put(Node node, Key key, Value value) {

        if (node == null) return new Node(key, value);

        int cmp = key.compareTo(node.key);

        if (cmp > 0) node.right = put(node.right, key, value);
        else if (cmp < 0) node.left = put(node.left, key, value);
        else node.value = value;

        return node;
    }

    /**
     * Delete functions
     */

    public void delete(Key key) {
        if (isEmpty()) throw new NoSuchElementException("Empty binary search tree!");
        size--;
        root = delete(root, key);
    }

    private Node delete(Node node, Key key) {

        if (node == null) return null;

        int cmp = key.compareTo(node.key);

        if (cmp > 0) node.left = delete(node.left, key);
        else if (cmp < 0) node.right = delete(node.right, key);
        else {

            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            Node t = node;
            node = min(node);
            node.right = deleteMax(node.right);
            node.left = t.left;
        }

        return node;
    }

    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("Empty binary search tree!");
        size--;
        root = deleteMin(root);
    }

    private Node deleteMin(Node node) {

        if (node.left == null) {
            return node.right;
        }

        node.left = deleteMin(node.left);
        return node;
    }

    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("Empty binary search tree!");
        size--;
        root = deleteMax(root);
    }

    private Node deleteMax(Node node) {

        if (node.right == null) {
            return node.left;
        }

        node.right = deleteMin(node.right);
        return node;
    }

    /**
     * Iterator over values and keys
     */

    public Iterator<Value> values() {
        Queue<Value> values = new Queue<Value>();
        getValues(root, values);
        return values.iterator();
    }

    private void getValues(Node node, Queue<Value> values) {
        if (node == null) return;

        getValues(node.left, values);
        values.enqueue(node.value);
        getValues(node.right, values);

    }

    public Iterator<Key> keys() {
        Queue<Key> keys = new Queue<Key>();
        getKeys(root, keys);
        return keys.iterator();
    }

    private void getKeys(Node node, Queue<Key> keys) {
        if (node == null) return;

        getKeys(node.left, keys);
        keys.enqueue(node.key);
        getKeys(node.right, keys);

    }

    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        for (int i = 0; i < 15; i++) {
            binarySearchTree.insert(i, i * i);
        }
        if (binarySearchTree.contains(15)) throw new Error();
        if (!binarySearchTree.contains(14)) throw new Error();


        for (int i = 0; i < 15; i++) {
            if (!binarySearchTree.get(i).equals((Integer) i * i)) throw new Error();
        }

        for (int i = 0; i < 15; i++) {
            if (!binarySearchTree.contains(i)) throw new Error();
        }


        if (!binarySearchTree.max().equals((Integer) 14)) throw new Error();
        if (!binarySearchTree.min().equals((Integer) 0)) throw new Error();

        for (int i = 0; i < 15; i++) {
            binarySearchTree.deleteMax();
        }

        try {
            binarySearchTree.deleteMax();
        } catch (NoSuchElementException e) {
            System.out.println("All tests pass!");
        }


    }


}

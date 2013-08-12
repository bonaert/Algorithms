package DataStructure;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MaximumPriorityQueue<Key> implements Iterable<Key> {

    private Key[] priorityQueue;
    private int size;
    private Comparator comparator;

    /**
     * Build a maximum priority queue, in which finding the
     * maximum key and deleting it are O(1).
     *
     * @param capacity
     */
    public MaximumPriorityQueue(int capacity) {
        priorityQueue = (Key[]) new Object[capacity + 1];
        size = 0;
    }

    /**
     * Build a maximum priority queue, in which finding the
     * max and deleting it are O(1).
     */
    public MaximumPriorityQueue() {
        this(1);
    }

    /**
     * Build a maximum priority queue, in which finding the
     * max and deleting it are O(1).
     *
     * @param capacity
     * @param comparator
     */
    public MaximumPriorityQueue(int capacity, Comparator comparator) {
        priorityQueue = (Key[]) new Object[capacity + 1];
        size = 0;
        this.comparator = comparator;
    }

    /**
     * Returns true if the priority queue is empty and
     * false otherwise.
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Returns the size of the priority queue.
     */
    public int size() {
        return size;
    }

    /**
     * Inserts the given key in the priority queue.
     *
     * @param key
     */
    public void insert(Key key) {
        if (size >= priorityQueue.length - 1) resize(priorityQueue.length * 2);

        priorityQueue[++size] = key;

        swim(size);
    }

    /**
     * Deletes the maximum key and returns it.
     */
    public Key deleteMax() throws NoSuchElementException {

        if (isEmpty()) throw new NoSuchElementException("Empty priority queue.");

        Key max = priorityQueue[1];

        swap(1, size--);
        sink(1);

        priorityQueue[size + 1] = null;
        if ((size > 0) && (size == (priorityQueue.length - 1) / 4)) resize(priorityQueue.length / 2);

        return max;
    }

    /**
     * Returns the maximum key
     */
    public Key max() throws NoSuchElementException {

        if (isEmpty()) throw new NoSuchElementException("Empty priority queue.");

        return priorityQueue[1];
    }

    /**
     * Helper functions
     */

    private void swim(int rank) {

        while (rank > 1 && less(rank / 2, rank)) {
            swap(rank, rank / 2);
            rank = rank / 2;
        }
    }

    private void sink(int parent) {
        while (2 * parent <= size) {
            int child = 2 * parent;
            if (child < size && less(child, child + 1)) child++;
            if (!less(parent, child)) break;
            swap(parent, child);
            parent = child;
        }
    }

    private void resize(int newSize) {
        Key[] newPriorityQueue = (Key[]) new Object[newSize];
        for (int i = 1; i <= size; i++) {
            newPriorityQueue[i] = priorityQueue[i];
        }
        priorityQueue = newPriorityQueue;
    }


    // Helper functions for keys

    private boolean greater(int rank1, int rank2) {
        if (comparator != null) {
            return comparator.compare(priorityQueue[rank1], priorityQueue[rank2]) > 0;
        } else {
            return ((Comparable<Key>) priorityQueue[rank1]).compareTo(priorityQueue[rank2]) > 0;
        }
    }

    private boolean less(int rank1, int rank2) {
        if (comparator != null) {
            return comparator.compare(priorityQueue[rank1], priorityQueue[rank2]) < 0;
        } else {
            return ((Comparable<Key>) priorityQueue[rank1]).compareTo(priorityQueue[rank2]) < 0;
        }
    }

    private void swap(int rank1, int rank2) {
        Key key = priorityQueue[rank1];
        priorityQueue[rank1] = priorityQueue[rank2];
        priorityQueue[rank2] = key;
    }


    /**
     * Returns an iterator over the keys in the priority queue,
     * in decreasing order.
     *
     * @return
     */
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key> {

        private MaximumPriorityQueue<Key> copy;

        public HeapIterator() {
            if (comparator == null) copy = new MaximumPriorityQueue(size);
            else copy = new MaximumPriorityQueue(size, comparator);


            for (int i = 1; i <= size; i++) {
                copy.insert(priorityQueue[i]);
            }

        }

        public boolean hasNext() {
            return !copy.isEmpty();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Key next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.deleteMax();
        }

    }

    public static void main(String[] args) {
        MaximumPriorityQueue maxPQ = new MaximumPriorityQueue();
        for (int i = 0; i < 10; i++) {
            maxPQ.insert(i);
        }

        for (int i = 0; i < 10; i++) {
            maxPQ.deleteMax();
        }

        try {
            maxPQ.deleteMax();
        } catch (NoSuchElementException e) {
            System.out.println("Passed exception test.");
        }
    }

}

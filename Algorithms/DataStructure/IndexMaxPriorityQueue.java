package Algorithms.DataStructs;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class IndexMaxPriorityQueue<Key extends Comparable<Key>> implements Iterable<Integer> {

    private Key[] keys;
    private int[] logicalValueToRealPosition; // pq
    private int[] realPositionToLogicalValue; // qp
    private final int maxN;
    private int size;


    /**
     * Builds a indexed minimum priority queue that associates key
     * with indexes in [0, maxN - 1].
     *
     * @param maxN
     */
    public IndexMaxPriorityQueue(int maxN) {

        if (maxN < 0) throw new IllegalArgumentException("MaxN must be a non-negative integer!");

        this.maxN = maxN;

        keys = (Key[]) new Comparable[maxN + 1];
        logicalValueToRealPosition = new int[maxN + 1];
        realPositionToLogicalValue = new int[maxN + 1];
        Arrays.fill(realPositionToLogicalValue, -1);

        size = 0;
    }

    /**
     * Returns true if the queue is empty, otherwise return false
     *
     * @return
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Return the number of key in the queue
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if the index has a key associated with it, otherwise return false.
     *
     * @param index
     * @return
     */
    public boolean contains(int index) {
        if (index < 0 || index >= maxN) throw new IndexOutOfBoundsException();

        return (realPositionToLogicalValue[index] != -1);
    }


    /**
     * Inserts key and associate it with index
     *
     * @param index
     * @param key
     */
    public void insert(int index, Key key) {
        if (index < 0 || index >= maxN) throw new IndexOutOfBoundsException();
        if (contains(index)) throw new IllegalArgumentException("Index is already in the queue");

        size++;
        keys[index] = key;
        realPositionToLogicalValue[index] = size;
        logicalValueToRealPosition[size] = index;

        swim(size);
    }

    /**
     * Changes the key associated with the index to the new provided key
     *
     * @param index
     * @param key
     */
    public void change(int index, Key key) {
        if (index < 0 || index >= maxN) throw new IndexOutOfBoundsException();
        if (!contains(index)) throw new IllegalArgumentException("Index is not already in the queue");


        keys[index] = key;
        swim(realPositionToLogicalValue[index]);
        sink(realPositionToLogicalValue[index]);
    }

    /**
     * Delete the key associated with the index
     *
     * @param index
     */
    public void delete(int index) {
        if (isEmpty()) throw new NoSuchElementException("Empty queue!");
        if (index < 0 || index >= maxN) throw new IndexOutOfBoundsException();
        if (!contains(index)) throw new IllegalArgumentException("Index is not already in the queue");

        int logicalPosition = realPositionToLogicalValue[index];
        exchange(logicalPosition, size--);

        swim(logicalPosition);
        sink(logicalPosition);


        keys[index] = null;
        realPositionToLogicalValue[index] = -1;
    }

    /**
     * Return the maximum key in the queue
     */
    public Key max() {
        return keys[logicalValueToRealPosition[1]];
    }

    /**
     * Return index associated with the maximum key in the queue
     */
    public int maxIndex() {
        return logicalValueToRealPosition[1];
    }

    // i -> l == qp
    // l -> i == pq

    /**
     * Delete the maximum key in the queue and return
     * the index associated with it
     */
    public int deleteMax() {

        if (isEmpty()) throw new NoSuchElementException("Empty queue!");

        int index = logicalValueToRealPosition[1];
        exchange(1, size--);

        sink(1);

        realPositionToLogicalValue[index] = -1;
        keys[logicalValueToRealPosition[size + 1]] = null;
        logicalValueToRealPosition[size + 1] = -1;

        return index;
    }

    /**
     * Return the key associated with the index
     *
     * @param index
     */
    public Key keyOf(int index) {
        if (!contains(index)) throw new IllegalArgumentException("Index is not already in the queue");

        return keys[index];
    }

    public void decreaseKey(int index, Key key) {
        if (index < 0 || index >= maxN) throw new IndexOutOfBoundsException();
        if (!contains(index)) throw new IllegalArgumentException("Index is not already in the queue");
        if (keys[index].compareTo(key) <= 0)
            throw new IllegalArgumentException("New key is superior or equal to old one!");


        keys[index] = key;
        swim(realPositionToLogicalValue[index]);
    }

    public void increaseKey(int index, Key key) {
        if (index < 0 || index >= maxN) throw new IndexOutOfBoundsException();
        if (!contains(index)) throw new IllegalArgumentException("Index is not already in the queue");
        if (keys[index].compareTo(key) >= 0)
            throw new IllegalArgumentException("New key is inferior or equal to old one!");


        keys[index] = key;
        sink(realPositionToLogicalValue[index]);
    }


    /**
     * Iterator
     */

    /**
     * Return an iterator that iterates over all of the elements on the
     * priority queue in descending order.
     * <p/>
     * The iterator doesn't implement <tt>remove()</tt> since it's optional.
     */
    public Iterator<Integer> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Integer> {
        // create a new pq
        private IndexMaxPriorityQueue<Key> copy;

        // add all elements to copy of heap
        // takes linear time since already in heap order so no keys move
        public HeapIterator() {
            copy = new IndexMaxPriorityQueue<Key>(realPositionToLogicalValue.length - 1);
            for (int i = 1; i <= size; i++)
                copy.insert(logicalValueToRealPosition[i], keys[logicalValueToRealPosition[i]]);
        }

        public boolean hasNext() {
            return !copy.isEmpty();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.deleteMax();
        }
    }


    /**
     * Key properties methods
     */

    private void exchange(int logPos1, int logPos2) {

        // Exchange the two indexes in the binary heap
        int swap = logicalValueToRealPosition[logPos1];
        logicalValueToRealPosition[logPos1] = logicalValueToRealPosition[logPos2];
        logicalValueToRealPosition[logPos2] = swap;


        // Correct the inverse binary heap indexes
        realPositionToLogicalValue[logicalValueToRealPosition[logPos1]] = logPos1;
        realPositionToLogicalValue[logicalValueToRealPosition[logPos2]] = logPos2;
    }

    private boolean less(int logPos1, int logPos2) {
        return keys[logicalValueToRealPosition[logPos1]].compareTo(keys[logicalValueToRealPosition[logPos2]]) < 0;
    }


    /**
     * Binary heap methods
     */

    // LogPos : Logical Position (position in the binary heap)
    private void swim(int childLogPos) {
        int parentLogPos = childLogPos / 2;
        while (childLogPos > 1 && less(parentLogPos, childLogPos)) {
            exchange(childLogPos, parentLogPos);
            childLogPos = parentLogPos;
            parentLogPos = childLogPos / 2;
        }
    }

    private void sink(int parent) {
        while (2 * parent <= size) {

            int child = 2 * parent;
            if (child < size && less(child, child + 1)) child++;

            if (!less(parent, child)) break;

            exchange(child, parent);

            parent = child;
        }
    }

    /**
     * Test method
     */

    public static void main(String[] args) {
        IndexMaxPriorityQueue indexMaxPriorityQueue = new IndexMaxPriorityQueue(20);

        try {
            indexMaxPriorityQueue.contains(32);
        } catch (IndexOutOfBoundsException e) {

        }

        try {
            indexMaxPriorityQueue.keyOf(15);
        } catch (IllegalArgumentException e) {

        }


        for (int i = 0; i < 20; i++) {
            indexMaxPriorityQueue.insert(i, i * i);
        }

        for (Object o : indexMaxPriorityQueue) {
            System.out.print(o + " ");
        }

        for (int i = 0; i < 20; i++) {
            if (!indexMaxPriorityQueue.keyOf(i).equals(i * i)) throw new Error();
        }

        if (indexMaxPriorityQueue.deleteMax() != 19) throw new Error();

        indexMaxPriorityQueue.decreaseKey(10, 99);
        if (!indexMaxPriorityQueue.keyOf(10).equals(99)) throw new Error();

        if (indexMaxPriorityQueue.contains(19)) throw new Error();

        if (indexMaxPriorityQueue.maxIndex() != 18) throw new Error();


    }
}

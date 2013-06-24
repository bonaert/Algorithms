import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IndexMinPriorityQueue<Key extends Comparable<Key>> implements Iterable<Integer> {

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
    public IndexMinPriorityQueue(int maxN) {

        if (maxN < 0) throw new IllegalArgumentException("MaxN must be a non-negative integer!");

        this.maxN = maxN;

        keys = (Key[]) new Comparable[maxN + 1];
        logicalValueToRealPosition = new int[maxN + 1];
        realPositionToLogicalValue = new int[maxN + 1];
        Arrays.fill(realPositionToLogicalValue, -1);

        size = 0;
    }

    /**
     * Returns true if the queue is empty, otherwise returns false
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
     * Returns true if the index has a key associated with it, otherwise returns false.
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
     * Deletes the key associated with the index
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
     * Returns the minimum key in the queue
     */
    public Key min() {
        return keys[logicalValueToRealPosition[1]];
    }

    /**
     * Returns index associated with the minimum key in the queue
     */
    public int minIndex() {
        return logicalValueToRealPosition[1];
    }

    // i -> l == qp
    // l -> i == pq

    /**
     * Deletes the minimum key in the queue and returns
     * the index associated with it
     */
    public int deleteMin() {

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
     * Returns the key associated with the index
     *
     * @param index
     */
    public Key keyof(int index) {
        if (index < 0 || index >= maxN) throw new IndexOutOfBoundsException();
        if (!contains(index)) throw new IllegalArgumentException("Index is not already in the queue");

        return keys[index];
    }

    /**
     * Changes the key associated with the index to the new key,
     * which must be inferior to the old one.
     *
     * @param index
     * @param key
     */
    public void decreaseKey(int index, Key key) {
        if (index < 0 || index >= maxN) throw new IndexOutOfBoundsException();
        if (!contains(index)) throw new IllegalArgumentException("Index is not already in the queue");
        if (keys[index].compareTo(key) <= 0)
            throw new IllegalArgumentException("New key is superior or equal to old one!");


        keys[index] = key;
        swim(realPositionToLogicalValue[index]);
    }

    /**
     * Changes the key associated with the index to the new key,
     * which must be superior to the old one.
     *
     * @param index
     * @param key
     */
    public void increaseKey(int index, Key key) {
        if (index < 0 || index >= maxN) throw new IndexOutOfBoundsException();
        if (!contains(index)) throw new IllegalArgumentException("Index is not already in the queue");
        if (keys[index].compareTo(key) >= 0)
            throw new IllegalArgumentException("New key is inferior or equal to old one!");


        keys[index] = key;
        sink(realPositionToLogicalValue[index]);
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

    private boolean greater(int logPos1, int logPos2) {
        return keys[logicalValueToRealPosition[logPos1]].compareTo(keys[logicalValueToRealPosition[logPos2]]) > 0;
    }

    /**
     * Iterator
     */


    /**
     * Return an iterator that iterates over all of the elements on the
     * priority queue in ascending order.
     * <p/>
     * The iterator doesn't implement <tt>remove()</tt> since it's optional.
     */
    public Iterator<Integer> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Integer> {
        // create a new pq
        private IndexMinPriorityQueue<Key> copy;

        // add all elements to copy of heap
        // takes linear time since already in heap order so no keys move
        public HeapIterator() {
            copy = new IndexMinPriorityQueue<Key>(realPositionToLogicalValue.length - 1);
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
            return copy.deleteMin();
        }
    }


    /**
     * Binary heap methods
     */

    // LogPos : Logical Position (position in the binary heap)
    private void swim(int childLogPos) {
        int parentLogPos = childLogPos / 2;
        while (childLogPos > 1 && greater(parentLogPos, childLogPos)) {
            exchange(childLogPos, parentLogPos);
            childLogPos = parentLogPos;
            parentLogPos = childLogPos / 2;
        }
    }

    private void sink(int parent) {
        while (2 * parent <= size) {

            int child = 2 * parent;
            if (child < size && greater(child, child + 1)) child++;

            if (!greater(parent, child)) break;

            exchange(child, parent);

            parent = child;
        }
    }

    /**
     * Testing method
     */

    public static void main(String[] args) {
        IndexMinPriorityQueue indexMinPriorityQueue = new IndexMinPriorityQueue(20);

        try {
            indexMinPriorityQueue.contains(32);
        } catch (IndexOutOfBoundsException e) {

        }

        try {
            indexMinPriorityQueue.keyof(15);
        } catch (IllegalArgumentException e) {

        }


        for (int i = 0; i < 20; i++) {
            indexMinPriorityQueue.insert(i, i * i);
        }

        for (Object integer : indexMinPriorityQueue) {
            System.out.print(integer + " ");
        }

        for (int i = 0; i < 20; i++) {
            if (!indexMinPriorityQueue.keyof(i).equals(i * i)) throw new Error();
        }

        if (indexMinPriorityQueue.deleteMin() != 0) throw new Error();

        indexMinPriorityQueue.decreaseKey(10, 99);
        if (!indexMinPriorityQueue.keyof(10).equals(99)) throw new Error();

        if (indexMinPriorityQueue.contains(0)) throw new Error();

        if (indexMinPriorityQueue.minIndex() != 1) throw new Error();


    }
}

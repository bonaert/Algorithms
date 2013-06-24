import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag<Item> implements Iterable<Item> {

    private Node first;
    private int size;

    private class Node {
        private Node next;
        private Item item;
    }

    /**
     * Creates a unordered container of object, that can be
     * extended without consideration. It provides an iterator
     * over the values contained in this Bag.
     */
    public Bag() {
        size = 0;
        first = null;
    }

    /**
     * Adds the item to the bag.
     *
     * @param item, the provided item.
     */
    public void add(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.next = oldfirst;
        first.item = item;
        size++;
    }

    /**
     * Return the size of the bag.
     */
    public int size() {
        return size;
    }

    /**
     * Returns {@code true} if the queue is empty, otherwise, return {@code false}.
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Returns an iterator over the items contained in this bag.
     * The items are iterated over in arbitrary order.
     */
    public Iterator<Item> iterator() {
        return new listIterator();
    }

    private class listIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current.next != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("Empty bag.");
            Item item = current.item;
            current = current.next;
            return item;
        }


    }
}

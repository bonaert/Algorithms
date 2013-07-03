package DataStructure;

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
     * Creates a empty container of objects, that can be
     * extended without consideration. It provides an iterator
     * over the values contained in this Bag.
     */
    public Bag() {
        size = 0;
        first = null;
    }

    /**
     * Creates a container of objects, initialized with the
     * given items, that can be extended without
     * consideration. It provides an iterator values
     * contained in this Bag.
     */
    public Bag(Iterable<Item> items) {

        size = 0;
        first = null;
        for (Item item : items) {
            add(item);
        }
    }

    /**
     * Creates a container of objects, initialized with the
     * given items, that can be extended without
     * consideration. It provides an iterator values
     * contained in this Bag.
     */
    public Bag(Bag bag){
        size = 0;
        first = null;
        Iterator<Item> iterator = bag.iterator();

        while (iterator.hasNext()) {
            Item next = iterator.next();
            add(next);
        }
    }

    /**
     * Adds the item to the bag.
     *
     * @param item, the provided item.
     */
    public void add(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.next = oldFirst;
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

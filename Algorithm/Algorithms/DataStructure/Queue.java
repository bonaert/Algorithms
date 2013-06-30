package DataStructure;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

    private class Node {
        private Node next;
        private Item item;
    }

    /**
     * Provided a queue, that is, an ordered object.
     * Through enqueing and dequeuing, this object
     * provides a first-in first-out(FIFO) ordering.
     */
    public Queue() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Enqueue an item to the queue.
     *
     * @param item, the item
     */
    public void enqueue(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        size++;
    }

    /**
     * Returns the first object enqueued that is left on
     * this queue. Throws an exception if the queue is empty.
     *
     * @return the first enqueued element in this queue.
     * @throws NoSuchElementException, if the queue is empty
     */
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Empty queue.");
        Item i = first.item;
        first = first.next;
        size--;
        if (isEmpty()) last = null;
        return i;
    }

    /**
     * Return the size of the queue.
     */
    public int size() {
        return size;
    }

    /**
     * Returns the the first object enqueued that is left on
     * this queue, but doesn't remove it.
     *
     * @return the last queued element in this queue
     * @throws NoSuchElementException, if the queue is empty
     */
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Empty queue.");
        return first.item;
    }

    /**
     * Returns {@code true} if the queue is empty, otherwise, return {@code false}.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Node node = first; node.next != null; node = node.next) {
            stringBuilder.append(node.item.toString()).append(" ");
        }
        return stringBuilder.toString();
    }

    /**
     * Returns an iterator over the items contained in this queue.
     * The items are iterated over in the order they have been enqueue,
     * that is, first-in first-out order.
     */
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("Iterator has traversed all items");

            Item item = current.item;

            current = current.next;

            return item;
        }


    }

    public static void main(String[] args) {
        Queue<Integer> q = new Queue<Integer>();
        for (int i = 0; i < 10; i++) {
            q.enqueue(i);
        }

        Iterator<Integer> it = q.iterator();

        for (int i = 0; i < 10; i++) {
            int l = q.dequeue();
            if (l != i) throw new Error("Difference between elements.");
        }

        while (it.hasNext()) {
            Integer i = it.next();
            System.out.print(i + " ");
        }

        try {
            q.dequeue();
        } catch (NoSuchElementException e) {
            System.out.println("Passed exception tests.");
        }

        q.enqueue(1);
        if (q.peek() != 1) throw new Error("Peek is incorrect");


    }


}

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<Item> implements Iterable<Item> {

    private Node first, last;
    private int size;

    private class Node {
        private Node next;
        private Node previous;
        private Item item;
    }

    public DoublyLinkedList() {
        size = 0;
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.previous = null;

        if (isEmpty()) last = first;

        size++;
    }

    public void push(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.previous = oldLast;
        oldLast.next = last;

        if (isEmpty()) first = last;

        size++;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Empty linked list.");

        Item item = first.item;

        first = first.next;

        size--;

        if (isEmpty()) last = first;

        return item;
    }

    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException("Empty linked list.");

        Item item = last.item;

        last = last.previous;

        size--;

        if (isEmpty()) first = last;

        return item;
    }

    public Iterator<Item> iterator() {
        return new LinkedListIterator(false);
    }

    public Iterator<Item> reverseIterator() {
        return new LinkedListIterator(true);
    }

    private class LinkedListIterator implements Iterator<Item> {


        private Node current;
        private boolean reverse;

        public LinkedListIterator(boolean reverse) {
            current = last;
            this.reverse = reverse;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public boolean hasNext() {
            return (current != null);
        }

        public Item next() {

            if (!hasNext()) throw new NoSuchElementException("Has traversed all elements");

            Item item = current.item;

            if (reverse) {
                current = current.previous;
            } else {
                current = current.next;
            }

            return item;
        }

    }

}

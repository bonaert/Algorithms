package DataStructure;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

    private class Node {
        private Node next;
        private Item item;
    }

    /**
     * Provided a stack, that is, an ordered object.
     * Through pushing and popping, this object
     * provides a last-in first-out(LIFO) ordering.
     */
    public Stack() {
        first = null;
        size = 0;
    }

    /**
     * Provided a stack, that is, an ordered object,
     * initialized with the provided items.
     * Through enqueing and dequeuing, this object
     * provides a first-in first-out(FIFO) ordering.
     */
    public Stack(Iterable<Item> items) {

        size = 0;
        first = null;
        for (Item item : items) {
            push(item);
        }
    }

    /**
     * Provided a stack, that is, an ordered object,
     * initialized with the provided items.
     * Through enqueing and dequeuing, this object
     * provides a first-in first-out(FIFO) ordering.
     */
    public Stack(Stack stack) {
        buildStack(stack, false);
    }

    /**
     * Since it will first add the items in the wrong direction,
     * we must do it a second time to put the
     * items in the correct order
     *
     * @param stack
     * @param goodDirection
     */
    private void buildStack(Stack stack, boolean goodDirection) {
        size = 0;
        first = null;
        Iterator<Item> iterator = stack.iterator();

        while (iterator.hasNext()) {
            Item next = iterator.next();
            push(next);
        }

        if (!goodDirection) buildStack(this, true);
    }


    /**
     * Push an item onto the stack.
     *
     * @param item, the item
     */
    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        if (isEmpty()) last = first;
        else first.next = oldfirst;
        size++;
    }

    /**
     * Returns the last object pushed into the stack.
     * Throws an exception if the queue is empty.
     *
     * @return the the last object pushed into the stack.
     * @throws NoSuchElementException, if the queue is empty
     */
    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException("Empty stack.");
        Item item = first.item;
        first = first.next;
        size--;
        if (isEmpty()) last = null;
        return item;
    }

    /**
     * Return the size of the queue.
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
     * Returns the the last object pushed that is left on
     * this stack, but doesn't remove it.
     *
     * @return the last object pushed in this stack
     * @throws NoSuchElementException, if the stack is empty
     */
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Empty stack.");
        return first.item;
    }


    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Node current = first; current.next != null; current = current.next) {
            stringBuilder.append(current.item.toString()).append(" ");

        }
        return stringBuilder.toString();
    }

    /**
     * Returns an iterator over the items contained in this stack.
     * The items are iterated over in last-in first-out order (LIFO).
     */
    public Iterator<Item> iterator() {
        return new listIterator();
    }

    private class listIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }


}

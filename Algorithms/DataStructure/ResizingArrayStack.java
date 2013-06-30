package Algorithms.DataStructs;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayStack<Key> implements Iterable<Key> {

    private Key[] items;
    private int size;

    public ResizingArrayStack(int capacity) {
        items = (Key[]) new Object[capacity];
        size = 0;
    }

    public ResizingArrayStack() {
        this(1);
    }

    public void push(Key key) {
        if (size == items.length) resize(items.length * 2);

        items[size++] = key;
    }

    public Key pop() {

        if (isEmpty()) throw new NoSuchElementException("Empty resizing array stack!");

        Key key = items[size - 1];
        items[size - 1] = null;
        if (size == items.length / 4) resize(items.length / 2);

        size--;
        return key;
    }

    public Key peek() {
        return items[size - 1];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    private void resize(int capacity) {
        Key[] newItems = (Key[]) new Object[capacity];
        System.arraycopy(items, 0, newItems, 0, size);
        items = newItems;
    }

    public Iterator<Key> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Key> {
        private int n;

        public ArrayIterator() {
            n = size;
        }

        public boolean hasNext() {
            return n > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Key next() {
            if (!hasNext()) throw new NoSuchElementException("Empty resizing array stack.");
            return items[--size];
        }
    }

    public static void main(String[] args) {
        ResizingArrayStack arrayStack = new ResizingArrayStack();

        for (int i = 0; i < 1000; i++) {
            arrayStack.push(i * i);
        }

        for (int i = 0; i < 1000; i++) {
            arrayStack.pop();
        }

        try {
            arrayStack.pop();
        } catch (NoSuchElementException a) {
            System.out.println("Worked!");
        }

        arrayStack.push(1);

        if (arrayStack.peek() != arrayStack.pop()) throw new Error("Peek or pop doesn't work!");

        arrayStack.push(1);

        if (arrayStack.peek() != (Integer) 1) throw new Error("Peek doesn't work!");

        System.out.println("Tests pass!");
    }
}

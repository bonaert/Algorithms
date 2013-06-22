import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayQueue<Key> implements Iterable<Key> {

    private Key[] items;
    private int size;
    private int first, last;

    public ResizingArrayQueue(int capacity) {
        items = (Key[]) new Object[capacity];
        size = 0;
        first = capacity - 1;
        last = capacity - 1;
    }

    public ResizingArrayQueue() {
        this(1);
    }

    public void enqueue(Key key) {
        if (size == items.length) resize(items.length * 2);

        items[last++] = key;
        size++;

        if (last == items.length) last = 0;
    }

    public Key dequeue() {

        if (isEmpty()) throw new NoSuchElementException("Empty resizing array stack!");
        Key key = items[first];
        items[first++] = null;
        size--;

        if (first == items.length) first = 0;

        if (size > 0 && size == items.length / 4) resize(items.length / 2);

        return key;
    }

    public Key peek() {
        return items[first];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    private void resize(int capacity) {
        Key[] newItems = (Key[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newItems[i] = items[(first + i) % size];
        }

        first = 0;
        last = size;
        items = newItems;
    }

    public Iterator<Key> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Key> {
        private int n, current;

        public ArrayIterator() {
            n = size;
            current = first;
        }

        public boolean hasNext() {
            return n > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Key next() {
            if (!hasNext()) throw new NoSuchElementException("Empty resizing array stack.");

            Key key = items[current++];
            if (current == items.length) current = 0;
            size--;

            return key;
        }
    }

    public static void main(String[] args) {
        ResizingArrayQueue arrayQueue = new ResizingArrayQueue();

        for (int i = 0; i < 1000; i++) {
            arrayQueue.enqueue(i);
        }

        if (arrayQueue.size() != 1000) throw new Error("Wrong size");

        for (int i = 0; i < 1000; i++) {
            if (arrayQueue.dequeue() != (Integer) i) throw new Error();
        }

        try {
            arrayQueue.dequeue();
        } catch (NoSuchElementException a) {
            System.out.println("Worked!");
        }

        arrayQueue.enqueue(1);
        arrayQueue.enqueue(2);

        if (arrayQueue.peek() != arrayQueue.dequeue()) throw new Error("Peek or pop doesn't work!");

        arrayQueue.enqueue(1);
        arrayQueue.enqueue(2);

        if (arrayQueue.peek() != (Integer) 1) throw new Error("Peek doesn't work!");

        System.out.println("Tests pass!");
    }
}

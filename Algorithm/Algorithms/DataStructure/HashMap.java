
package DataStructure;

import java.util.Arrays;
import java.util.Map;

public class HashMap<Key, Value> {

    static class Entry<Key, Value> implements Map.Entry<Key, Value> {

        final Key key;
        Value value;
        Entry<Key, Value> nextEntry;
        int hash;

        Entry(int hash, Key key, Value value, Entry<Key, Value> nextEntry) {
            this.key = key;
            this.value = value;
            this.nextEntry = nextEntry;
            this.hash = hash;
        }

        public final Key getKey() {
            return key;
        }

        public final Value getValue() {
            return value;
        }

        public Value setValue(Value value) {
            Value oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        public boolean isLastEntryInChain() {
            return nextEntry == null;
        }

        public boolean hasKey(Key key) {
            return key.equals(this.key);
        }

        public boolean hasValue(Value value) {
            return value.equals(this.value);
        }
    }

    private int capacity;
    transient Entry<Key, Value>[] entries;
    transient int size;
    final double loadFactor;


    static final double DEFAULT_LOAD_CAPACITY = 0.75;
    static final int DEFAULT_INITIAL_CAPACITY = 16;


    /**
     * Creates a hash map, that is a map that
     * associates a key with a value, but with very fast
     * insertion, deletion, getting and check if the
     * key is contained, provided that the hash
     * function of the key is uniformly distributed.
     */

    public HashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_CAPACITY);
    }


    /**
     * Creates a hash map, that is a map that
     * associates a key with a value, but with very fast
     * insertion, deletion, getting and check if the
     * key is contained, provided that the hash
     * function of the key is uniformly distributed.
     *
     * @param capacity
     */

    public HashMap(int capacity) {
        this(capacity, DEFAULT_LOAD_CAPACITY);
    }


    /**
     * Creates a hash map, that is a map that
     * associates a key with a value, but with very fast
     * insertion, deletion, getting and check if the
     * key is contained, provided that the hash
     * function of the key is uniformly distributed.
     *
     * @param initialCapacity
     * @param loadFactor
     */

    public HashMap(int initialCapacity, double loadFactor) {
        size = 0;

        // Find a power of 2 >= initialCapacity
        int capacity = 1;
        while (capacity < initialCapacity) capacity *= 2;

        this.capacity = capacity;
        this.loadFactor = loadFactor;

        entries = new Entry[this.capacity];
    }


    /**
     * Returns the value associated with this key
     * in the hash map.
     *
     * @param key
     */
    public Value get(Key key) {
        int hash = computeHash(key);
        Entry<Key, Value> entry = entries[hash];

        while (entry != null && !entry.hasKey(key)) {
            entry = entry.nextEntry;
        }

        return (entry == null) ? null : entry.getValue();
    }


    /**
     * Puts the given key and value in the hash map.
     *
     * @param key
     * @param value
     */
    public void put(Key key, Value value) {
        if (size >= capacity * loadFactor) resize(2 * capacity);

        int hash = computeHash(key);
        addEntry(key, value, hash);
    }

    private void resize(int newSize) {
        System.out.println("Resizing");
        Entry<Key, Value>[] oldEntries = entries;
        entries = new Entry[newSize];

        capacity = newSize;
        size = 0;

        for (Entry<Key, Value> oldFirstEntryInChain : oldEntries) {
            addAllEntriesWithSameHash(oldFirstEntryInChain);
        }
    }


    private void addAllEntriesWithSameHash(Entry<Key, Value> firstEntryInChain) {
        for (Entry<Key, Value> entry = firstEntryInChain; entry != null; entry = entry.nextEntry) {
            addEntry(entry);
        }
    }

    private void addEntry(Entry<Key, Value> entry) {
        int hash = entry.hash;
        Key key = entry.getKey();
        Value value = entry.getValue();
        addEntry(key, value, hash);
    }

    private void addEntry(Key key, Value value, int hash) {
        Entry<Key, Value> newEntry = new Entry<Key, Value>(hash, key, value, null);

        if (entryExists(hash)) {
            Entry<Key, Value> lastEntry = getLastEntry(hash);
            lastEntry.nextEntry = newEntry;
        } else {
            entries[hash] = newEntry;
        }

        size++;
    }

    private Entry<Key, Value> getLastEntry(int hash) {
        Entry<Key, Value> entry = entries[hash];
        while (!entry.isLastEntryInChain()) {
            entry = entry.nextEntry;
        }
        return entry;
    }


    /**
     * Remove the key and value in the hash map.
     * If the key is not present, this methods doesn't
     * do anything.
     */
    public void remove(Key key) {
        int hash = computeHash(key);

        if (!entryExists(hash)) {
            return;
        }

        Entry<Key, Value> entry = entries[hash];

        // [Good Entry] -> [A] -> ... transforms into [A] -> ...
        if (entry.hasKey(key)) {
            entries[hash] = entry.nextEntry;
            size--;
            return;
        }

        // ... -> [A] -> [Good Entry] -> [B]  transform into ... -> [A] -> [B]
        while (!entry.isLastEntryInChain()) {
            Entry<Key, Value> nextEntry = entry.nextEntry;
            if (nextEntry.hasKey(key)) {
                entry.nextEntry = (nextEntry.isLastEntryInChain()) ? null : nextEntry.nextEntry;
                size--;
                return;
            }
            entry = entry.nextEntry;
        }
    }

    public void clear() {
        Arrays.fill(entries, null);
        size = 0;
    }


    /**
     * Returns true if the key is in the hash map,
     * otherwise returns false;
     */
    public boolean containsKey(Key key) {
        if (key == null) throw new IllegalArgumentException("Null cannot be a key!");

        int hash = computeHash(key);
        if (!entryExists(hash)) return false;

        Entry<Key, Value> entry = entries[hash];

        while (entry != null) {
            if (entry.hasKey(key)) return true;
            entry = entry.nextEntry;
        }

        return false;
    }


    /**
     * Returns true if the value is in the hash map,
     * otherwise returns false;
     */

    public boolean containsValue(Value value) {
        if (value == null) throw new IllegalArgumentException("Null cannot be a key!");

        for (Entry<Key, Value> entry : entries) {
            while (entry != null) {
                if (entry.hasValue(value)) return true;
                entry = entry.nextEntry;
            }
        }

        return false;
    }

    private int computeHash(Key key) {
        return (key.hashCode() & 0x7FFFFFF) % capacity;
    }

    private boolean entryExists(int hash) {
        return entries[hash] != null;
    }


    /**
     * Returns true if the hash map is empty,
     * otherwise return false.
     */

    public boolean isEmpty() {
        return (size == 0);
    }


    /**
     * Returns the size of the hash map.
     */

    public int size() {
        return size;
    }


    public static void main(String[] args) throws Exception {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < 100; i++) {
            map.put(i, i * i);
        }

        for (int i = 0; i < 100; i++) {
            if (map.get(i) != i * i) {
                throw new Exception();
            }
        }
    }

}


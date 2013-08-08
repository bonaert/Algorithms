/*
package DataStructure;

import java.util.Arrays;

public class HashMap<Key, Value> {

    class Entry<Key, Value> {

        private Key key;
        private Value value;
        private Entry<Key, Value> nextEntry;
        int hash;

        private Entry(Key key, Value value, Entry nextEntry) {
            this.key = key;
            this.value = value;
            this.nextEntry = nextEntry;
        }

        public Key getKey() {
            return key;
        }

        public Value getValue() {
            return value;
        }

        public final Entry nextEntry() {
            return nextEntry;
        }

        private void setNextEntry(Entry nextEntry) {
            this.nextEntry = nextEntry;
        }
    }

    private int capacity;
    private Entry[] entries;
    private int size;
    private final double loadFactor;


    static final double DEFAULT_LOAD_CAPACITY = 0.75;
    static final int DEFAULT_INITIAL_CAPACITY = 16;


    */
/**
     * Creates a hash map, that is a map that
     * associates a key with a value, but with very fast
     * insertion, deletion, getting and check if the
     * key is contained, provided that the hash
     * function of the key is uniformly distributed.
     *//*

    public HashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_CAPACITY);
    }

    */
/**
     * Creates a hash map, that is a map that
     * associates a key with a value, but with very fast
     * insertion, deletion, getting and check if the
     * key is contained, provided that the hash
     * function of the key is uniformly distributed.
     *
     * @param capacity
     *//*

    public HashMap(int capacity) {
        this(capacity, DEFAULT_LOAD_CAPACITY);
    }

    */
/**
     * Creates a hash map, that is a map that
     * associates a key with a value, but with very fast
     * insertion, deletion, getting and check if the
     * key is contained, provided that the hash
     * function of the key is uniformly distributed.
     *
     * @param initialCapacity
     * @param loadFactor
     *//*

    public HashMap(int initialCapacity, double loadFactor) {
        size = 0;

        // Find a power of 2 >= initialCapacity
        int possibleCapacity = 1;
        while (possibleCapacity < initialCapacity)
            possibleCapacity <<= 1;

        this.capacity = possibleCapacity;

        entries = (Entry[]) new Object[capacity];
        this.loadFactor = loadFactor;
    }

    */
/**
     * Returns the value associated with this key
     * in the hash map.
     *
     * @param key
     *//*

    public Value get(Key key) {

        int hash = computeHash(key);
        Entry entry = entries[hash];

        while (entry != null && !entry.getKey().equals(key)) {
            entry = entry.nextEntry();
        }

        if (entry != null) return entry.getValue();
        return null;
    }

    */
/**
     * Puts the given key and value in the hash map.
     *
     * @param key
     * @param value
     *//*

    public void put(Key key, Value value) {

        if (size >= capacity * loadFactor) resize(2 * capacity);

        int hash = computeHash(key);
        Entry entry = entries[hash];

        Entry newEntry = new Entry(key, value, null);

        if (isEntry(hash)) {
            while (entry.nextEntry != null) {
                entry = entry.nextEntry;
            }
            entry.setNextEntry(newEntry);
            entry = entry.nextEntry();
        }

        entry = newEntry;
        size++;
    }

    private void resize(int newSize) {
        Entry[] oldEntries = entries;
        entries = (Entry[]) new Object[newSize];


        capacity = newSize;

        for (Entry oldEntry : oldEntries) {

            add(entries, oldEntry);

            oldEntry = oldEntry.nextEntry();
            while (oldEntry != null) {
                add(entries, oldEntry);
            }
        }
    }

    private void add(Entry[] newEntries, Entry newEntry) {

        Key key = (Key) newEntry.getKey();
        Value value = (Value) newEntry.getValue();

        int hash = computeHash(key);
        Entry entry = entries[hash];

        Entry nextEntry = new Entry(key, value, null);

        if (isEntry(hash)) {
            while (entry.nextEntry != null) {
                entry = entry.nextEntry;
            }
            entry.setNextEntry(nextEntry);
            entry = entry.nextEntry();
        }

        entry = nextEntry;
        size++;
    }

    */
/**
     * Remove the key and value in the hash map.
     * If the key is not present, this methods doesn't
     * do anything.
     *//*

    public void remove(Key key) {

        int hash = computeHash(key);
        Entry entry = entries[hash];

        if (entry == null) return;

        if (entry.getKey().equals(key)) {
            entries[hash] = entry.nextEntry();
            size--;
            return;
        }

        while (entry.nextEntry != null) {

            if (entry.nextEntry.getKey().equals(key)) {
                entry = entry.nextEntry().nextEntry();
                size--;
                return;
            }
        }

    }

    public void clear() {
        Arrays.fill(entries, null);
        size = 0;
    }

    */
/**
     * Returns true if the key is in the hash map,
     * otherwise returns false;
     *//*

    public boolean containsKey(Key key) {
        int hash = computeHash(key);

        Entry entry = entries[hash];
        if (entry == null) return false;

        while (entry != null && !entry.getKey().equals(key)) {
            entry = entry.nextEntry();
        }

        return (entry != null);
    }

    */
/**
     * Returns true if the value is in the hash map,
     * otherwise returns false;
     *//*

    public boolean containsValue(Value value) {

        for (int i = 0; i < capacity; i++) {

            for (Entry entry = entries[i]; entry != null; entry = entry.nextEntry()) {
                if (entry.getValue().equals(value)) return true;
            }
        }

        return false;
    }

    private int computeHash(Key key) {
        return (key.hashCode() & 0x7FFFFFF) % capacity;
    }

    private boolean isEntry(int hash) {
        return entries[hash] != null;
    }

    */
/**
     * Returns true if the hash map is empty,
     * otherwise return false.
     *//*

    public boolean isEmpty() {
        return (size == 0);
    }

    */
/**
     * Returns the size of the hash map.
     *//*

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
*/

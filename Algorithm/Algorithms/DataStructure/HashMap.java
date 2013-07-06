package DataStructure;

import java.util.Arrays;

public class HashMap<Key, Value> {

    private class Entry {

        private Key key;
        private Value value;
        private Entry nextEntry;

        public Entry(Key key, Value value, Entry nextEntry) {
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

        public Entry nextEntry() {
            return nextEntry;
        }

        private void setNextEntry(Entry nextEntry) {
            this.nextEntry = nextEntry;
        }
    }

    private final int capacity;
    private Entry[] entries;
    private int size;

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
        size = 0;
        this.capacity = capacity;
        entries = (Entry[]) new Object[capacity];
    }

    /**
     * Returns the value associated with this key
     * in the hash map.
     *
     * @param key
     */
    public Value get(Key key) {

        int hash = computeHash(key);
        Entry entry = entries[hash];

        while (entry != null && !entry.getKey().equals(key)) {
            entry = entry.nextEntry();
        }

        if (entry != null) return entry.getValue();
        return null;
    }

    /**
     * Puts the given key and value in the hash map.
     *
     * @param key
     * @param value
     */
    public void put(Key key, Value value) throws Exception {

        if (capacity == size) throw new Exception("Full hash map");

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

    /**
     * Remove the key and value in the hash map.
     * If the key is not present, this methods doesn't
     * do anything.
     */
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

    /**
     * Returns true if the key is in the hash map,
     * otherwise returns false;
     */
    public boolean containsKey(Key key) {
        int hash = computeHash(key);

        Entry entry = entries[hash];
        if (entry == null) return false;

        while (entry != null && !entry.getKey().equals(key)) {
            entry = entry.nextEntry();
        }

        return (entry != null);
    }

    /**
     * Returns true if the value is in the hash map,
     * otherwise returns false;
     */
    public boolean containsValue(Value value) {

        for (int i = 0; i < capacity; i++) {

            for (Entry entry = entries[i]; entry != null; entry = entry.nextEntry()) {
                if (entry.getValue().equals(value)) return true;
            }
        }

        return false;
    }

    private int computeHash(Key key) {
        return key.hashCode() % capacity;
    }

    private boolean isEntry(int hash) {
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


}

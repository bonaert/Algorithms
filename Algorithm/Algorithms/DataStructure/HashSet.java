package DataStructure;


import java.util.Arrays;

public class HashSet<Key> {

    private class Entry {

        private Key key;
        private Entry nextEntry;

        public Entry(Key key, Entry nextEntry) {
            this.key = key;
            this.nextEntry = nextEntry;
        }

        public Key getKey() {
            return key;
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
    public HashSet(int capacity) {
        size = 0;
        this.capacity = capacity;
        entries = (Entry[]) new Object[capacity];
    }

    /**
     * Puts the given key and value in the hash map.
     *
     * @param key
     */
    public void put(Key key) throws Exception {

        if (capacity == size) throw new Exception("Full hash set");

        int hash = computeHash(key);
        Entry entry = entries[hash];

        Entry newEntry = new Entry(key, null);

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

    /**
     * Returns true if the key is in the hash map,
     * otherwise returns false;
     */
    public boolean contains(Key key) {
        int hash = computeHash(key);

        Entry entry = entries[hash];
        if (entry == null) return false;

        while (entry != null && !entry.getKey().equals(key)) {
            entry = entry.nextEntry();
        }

        return (entry != null);
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

    public void clear() {
        Arrays.fill(entries, null);
        size = 0;
    }


}



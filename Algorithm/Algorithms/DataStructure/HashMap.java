
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

        public Value setValue(Value newValue) {
            Value oldValue = value;
            value = newValue;
            return oldValue;
        }

        public boolean hasKey(Key key) {
            return (key == this.key) || (key != null && key.equals(this.key));
        }

        public boolean hasValue(Value value) {
            return value.equals(this.value);
        }
    }

    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final int MAXIMUM_CAPACITY = 1 << 30;

    static final double DEFAULT_LOAD_FACTOR = 0.75f;

    transient Entry<Key, Value>[] entries;
    transient int size;
    private int resizingThreshold;
    final double loadFactor;


    /**
     * Creates a hash map, that is a map that
     * associates a key with a value, but with very fast
     * insertion, deletion, getting and check if the
     * key is contained, provided that the hash
     * function of the key is uniformly distributed.
     */

    public HashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
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
        this(capacity, DEFAULT_LOAD_FACTOR);
    }


    /**
     * Creates a hash map, that is a map that
     * associates a key with a value, but with very fast
     * insertion, deletion, getting and check if the
     * key is contained, provided that the hash
     * function of the key is uniformly distributed.
     *
     * @param capacity
     * @param loadFactor
     */

    public HashMap(int capacity, double loadFactor) {
        size = 0;

        // Find a power of 2 >= requiredCapacity
        int actualCapacity = 1;
        capacity = Math.min(capacity, MAXIMUM_CAPACITY);
        while (actualCapacity < capacity) actualCapacity *= 2;

        resizingThreshold = (int) Math.min(actualCapacity * loadFactor, MAXIMUM_CAPACITY + 1);
        this.loadFactor = loadFactor;

        entries = new Entry[actualCapacity];
    }


    /**
     * Returns the value associated with this key
     * in the hash map.
     *
     * @param key
     */
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("Null keys are not allowed.");

        int hash = computeHash(key);
        int index = indexFor(hash);
        Entry<Key, Value> entry = entries[index];

        while (entry != null && !entry.hasKey(key)) {
            entry = entry.nextEntry;
        }

        return (entry == null) ? null : entry.getValue();
    }


    /**
     * Puts the given key and value in the hash map.
     * Returns the value associated with the key. If the key has no
     * associated value, null is returned.
     *
     * @param key
     * @param value
     */
    public Value put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("Illegal keys are not allowed");

        Value currentValue = updateKeyValuePair(key, value);

        if (currentValue == null) {
            addEntry(key, value);
        }

        return currentValue;
    }

    private Value updateKeyValuePair(Key key, Value value) {
        int hash = computeHash(key);
        int index = indexFor(hash);
        Entry<Key, Value> entry = entries[index];

        while (entry != null) {
            if (entry.hash == hash && entry.hasKey(key)) {
                Value oldValue = entry.value;
                entry.setValue(value);
                return oldValue;
            }
            entry = entry.nextEntry;
        }

        return null;
    }

    private void resize(int newCapacity) {
        int oldCapacity = entries.length;
        if (oldCapacity == MAXIMUM_CAPACITY) {
            resizingThreshold = Integer.MAX_VALUE;
            return;
        }

        Entry<Key, Value>[] newEntries = new Entry[newCapacity];

        transferEntries(newEntries);
        entries = newEntries;
        resizingThreshold = (int) Math.min(newCapacity * loadFactor, MAXIMUM_CAPACITY + 1);
    }

    private void transferEntries(Entry<Key, Value>[] newEntries) {
        for (Entry<Key, Value> entry : entries) {
            addAllEntriesWithSameHash(entry, newEntries);
        }
    }


    private void addAllEntriesWithSameHash(Entry<Key, Value> entry, Entry<Key, Value>[] newEntries) {
        int newCapacity = newEntries.length;
        while (entry != null) {
            Entry<Key, Value> next = entry.nextEntry;

            // [A] -> ... into [newEntry] -> [A] -> ... (add new entry at the beginning of the chain)
            int index = indexFor(entry.hash, newCapacity);
            entry.nextEntry = newEntries[index];
            newEntries[index] = entry;

            entry = next;
        }
    }

    private void addEntry(Key key, Value value) {
        int hash = computeHash(key);
        int index = indexFor(hash);

        if ((size >= resizingThreshold) && (entries[index] != null)) {
            resize(2 * entries.length);
            index = indexFor(hash);
        }

        addEntry(key, value, hash, index);
    }

    private void addEntry(Key key, Value value, int hash, int index) {
        Entry<Key, Value> firstEntry = entries[index];
        entries[index] = new Entry<Key, Value>(hash, key, value, firstEntry);
        size++;
    }

    /**
     * Remove the key and value in the hash map.
     * If the key is not present, this methods doesn't
     * do anything.
     */
    public Value remove(Key key) {
        if (key == null) throw new IllegalArgumentException("Null keys are not allowed.");

        Entry<Key, Value> entry = removeEntry(key);
        return (entry == null) ? null : entry.value;
    }

    /**
     * Removes and returns the entry associated with the specified key
     * in the HashMapCopy.  Returns null if the HashMapCopy contains no mapping
     * for this key.
     */
    private Entry<Key, Value> removeEntry(Key key) {
        int hash = computeHash(key);
        int index = indexFor(hash);
        Entry<Key, Value> prev = entries[index];
        Entry<Key, Value> entry = prev;

        while (entry != null) {
            Entry<Key, Value> next = entry.nextEntry;

            if (entry.hash == hash && entry.hasKey(key)) {
                boolean isFirstEntry = (prev == entry);
                if (isFirstEntry) {
                    // [Good Entry] -> [next] -> ... transforms into [next] -> ...
                    entries[index] = next;
                } else {
                    // ... [prev] -> [Good Entry] -> [next]  transform into ... [prev] -> [next]
                    prev.nextEntry = next;
                }

                size--;
                return entry;
            }

            prev = entry;
            entry = next;
        }

        return null;
    }


    /**
     * Clears all entries in the hash map.
     */
    public void clear() {
        Arrays.fill(entries, null);
        size = 0;
    }


    /**
     * Returns true if the key is in the hash map,
     * otherwise returns false;
     */
    public boolean containsKey(Key key) {
        if (key == null) throw new IllegalArgumentException("Null keys are not allowed.");

        int hash = computeHash(key);
        int index = indexFor(hash);
        if (!entryExists(hash)) return false;

        Entry<Key, Value> entry = entries[index];
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
        return (key.hashCode() & 0x7FFFFFF) % entries.length;
    }

    private int indexFor(int hash) {
        return indexFor(hash, entries.length);
    }

    private int indexFor(int hash, int capacity) {
        return hash % capacity;
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


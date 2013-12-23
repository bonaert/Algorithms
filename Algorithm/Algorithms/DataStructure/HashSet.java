package DataStructure;


public class HashSet<E> {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final int MAXIMUM_CAPACITY = 1 << 30;
    private static final double DEFAULT_LOAD_FACTOR = 0.75f;

    private final Object PRESENT = new Object();
    private transient HashMap<E, Object> map;

    /**
     * Creates a hash set, that is a set with very fast
     * insertion, deletion, contain methods, provided that the hash
     * function of the elements is uniformly distributed.
     */
    public HashSet() {
        map = new HashMap<E, Object>();
    }

    /**
     * Creates a hash set, that is a set with very fast
     * insertion, deletion, contain methods, provided that the hash
     * function of the elements is uniformly distributed.
     *
     * @param initialCapacity
     */
    public HashSet(int initialCapacity) {
        map = new HashMap<E, Object>(initialCapacity);
    }

    /**
     * Creates a hash set, that is a set with very fast
     * insertion, deletion, contain methods, provided that the hash
     * function of the elements is uniformly distributed.
     *
     * @param initialCapacity
     * @param loadFactor
     */
    public HashSet(int initialCapacity, double loadFactor) {
        map = new HashMap<E, Object>(initialCapacity, loadFactor);
    }

    /**
     * Puts the given element and value in the hash set.
     */
    public void put(E element) throws Exception {
        map.put(element, PRESENT);
    }

    /**
     * Remove the element in the hash set. Returns true
     * if the set contained the element, otherwise false.
     */
    public boolean remove(E element) {
        return map.remove(element) == PRESENT;
    }

    /**
     * Returns true if the element is in the hash set,
     * otherwise returns false;
     */
    public boolean contains(E element) {
        return map.containsKey(element);
    }

    /**
     * Returns true if the hash set is empty,
     * otherwise return false.
     */
    public boolean isEmpty() {
        return map.isEmpty();
    }

    /**
     * Returns the size of the hash set.
     */
    public int size() {
        return map.size();
    }


    /**
     * Clear all the elements in the hash set.
     */
    public void clear() {
        map.clear();
    }
}



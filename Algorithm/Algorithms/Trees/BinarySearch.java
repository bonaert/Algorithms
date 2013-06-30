package Trees;

import java.util.Comparator;

public class BinarySearch {

    private BinarySearch() {
    }

    /**
     * Finds the rank (position) of the provided key in
     * this array of integers. It assumes that the provided
     * array is sorted, otherwise, it returns an incorrect rank.
     * If the key is not found, return -1.
     *
     * @param key,     the key to be found
     * @param numbers, the array of integers
     */
    public int rank(int key, int[] numbers) {

        int low = 0;
        int high = numbers.length - 1;

        while (low < high) {
            int mid = (low + high) >>> 1;
            int value = numbers[mid];
            if (value > key) {
                high = mid - 1;
            } else if (value < key) {
                low = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    /**
     * Finds the rank (position) of the provided key in
     * this array of comparable objects. It assumes that the provided
     * array is sorted, otherwise, it returns an incorrect rank.
     * If the key is not found, return -1.
     *
     * @param key,   the key to be found
     * @param array, the array of object
     */
    public int rank(Comparable key, Comparable[] array) {

        int low = 0;
        int high = array.length - 1;

        while (low < high) {
            int mid = (low + high) >>> 1;

            Comparable value = array[mid];
            int comp = value.compareTo(key);

            if (comp > 0) {
                high = mid - 1;
            } else if (comp < 0) {
                low = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }


    /**
     * Finds the rank (position) of the provided key in
     * this array of objects. It assumes that the provided
     * array is sorted, otherwise, it returns an incorrect rank.
     * If the key is not found, return -1.
     *
     * @param key,   the key to be found
     * @param array, the array of object
     * @param c,     the comparator
     */
    public int rank(Comparable key, Object[] array, Comparator c) {

        int low = 0;
        int high = array.length - 1;

        while (low < high) {
            int mid = (low + high) >>> 1;
            Object value = array[mid];
            int comp = c.compare(value, key);
            if (comp > 0) {
                high = mid - 1;
            } else if (comp < 0) {
                low = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }


}

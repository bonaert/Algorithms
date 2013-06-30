package Algorithms.Sort;

import java.util.Comparator;

public class Insertion {

    // To prevent instanciation
    private Insertion() {
    }

    /**
     * Sorts the provided array of integers through
     * the insertion sort algorithm.
     * Note: This sort is O(n^2), so only use it
     * for nearly sorted or small arrays.
     *
     * @param numbers, the array of numbers
     */
    public static void sort(int[] numbers) {

        int length = numbers.length;

        for (int i = 2; i < length; i++) {
            for (int j = i; j > 0 && numbers[j] < numbers[j - 1]; j--) {
                swap(numbers, j, j - 1);
            }
        }
    }

    /**
     * Sorts the provided array of integers through
     * the insertion sort algorithm.
     * Note: This sort is O(n^2), so only use it
     * for nearly sorted or small arrays.
     *
     * @param numbers, the array of numbers
     * @param low,     the lowest address
     * @param high,    the top address
     */
    public static void sort(int[] numbers, int low, int high) {

        for (int i = low + 1; i <= high; i++) {
            for (int j = i; j > low && numbers[j] < numbers[j - 1]; j--) {
                swap(numbers, j, j - 1);
            }
        }
    }

    /**
     * Sorts the provided array of comparable objects through
     * the insertion sort algorithm.
     * Note: This sort is O(n^2), so only use it
     * for nearly sorted or small arrays.
     *
     * @param a, the array of comparable objects
     */
    public static void sort(Comparable[] a) {
        int length = a.length;

        for (int i = 2; i < length; i++) {
            for (int j = i; j > 1 && less(a, j, j - 1); j--) {
                swap(a, j, j - 1);
            }
        }
    }

    /**
     * Sorts the provided array of comparable objects through
     * the insertion sort algorithm.
     * Note: This sort is O(n^2), so only use it
     * for nearly sorted or small arrays.
     *
     * @param a,    the array of comparable objects
     * @param low,  the lowest address
     * @param high, the top address
     */
    public static void sort(Comparable[] a, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            for (int j = i; j > low && less(a, j, j - 1); j--) {
                swap(a, j, j - 1);
            }
        }
    }

    /**
     * Sorts the provided array of objects through
     * the insertion sort algorithm.
     * Note: This sort is O(n^2), so only use it
     * for nearly sorted or small arrays.
     *
     * @param a, the array of comparable objects
     * @param c, the comparator for the provided objects
     */
    public static void sort(Object[] a, Comparator c) {
        int length = a.length;

        for (int i = 2; i < length; i++) {
            for (int j = i; j > 1 && less(a, c, j, j - 1); j--) {
                swap(a, j, j - 1);
            }
        }
    }


    public static int[] sorted(int[] a) {
        int[] copy = new int[a.length];
        System.arraycopy(a, 0, copy, 0, a.length);

        sort(copy);
        return copy;
    }

    public static Comparable[] sorted(Comparable[] a) {
        Comparable[] copy = new Comparable[a.length];
        System.arraycopy(a, 0, copy, 0, a.length);

        sort(copy);
        return copy;
    }

    public static Object[] sorted(Object[] a, Comparator c) {
        Object[] copy = new Object[a.length];
        System.arraycopy(a, 0, copy, 0, a.length);

        sort(copy, c);
        return copy;
    }


    // Helper methods

    private static void swap(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

    private static void swap(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void swap(Object[] a, int i, int j) {
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static boolean less(Comparable[] array, int a, int b) {
        return array[a].compareTo(array[b]) < 0;
    }

    private static boolean less(Object[] array, Comparator c, int a, int b) {
        return c.compare(array[a], array[b]) < 0;
    }

    public static void main(String[] args) {
        int[] abcd = new int[]{7, 6, 3, 4, 1, 6, 5, 2, 8};
        Insertion.sort(abcd, 2, 5);
        for (int i : abcd) {
            System.out.print(i + " ");
        }
    }

}

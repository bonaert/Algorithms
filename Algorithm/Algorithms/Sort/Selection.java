package Sort;

import java.util.Comparator;

public class Selection {

    private Selection() {
    }

    public static void sort(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            int minimumNumberIndex = i;
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[j] < numbers[minimumNumberIndex]) minimumNumberIndex = j;
            }
            swap(numbers, i, minimumNumberIndex);
        }
    }

    public static void sort(Comparable[] array) {
        for (int i = 0; i < array.length; i++) {
            int minimumComparableIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (less(array, j, minimumComparableIndex)) minimumComparableIndex = j;
            }
            swap(array, i, minimumComparableIndex);
        }
    }

    public static void sort(Object[] array, Comparator comparator) {
        for (int i = 0; i < array.length; i++) {
            int minimumObjectIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (less(array, comparator, j, minimumObjectIndex)) minimumObjectIndex = j;
            }
            swap(array, i, minimumObjectIndex);
        }
    }

    public static int[] sorted(int[] array) {
        int[] copy = new int[array.length];
        System.arraycopy(array, 0, copy, 0, array.length);

        sort(copy);
        return copy;
    }

    public static Comparable[] sorted(Comparable[] array) {
        Comparable[] copy = new Comparable[array.length];
        System.arraycopy(array, 0, copy, 0, array.length);

        sort(copy);
        return copy;
    }

    public static Object[] sorted(Object[] array, Comparator comparator) {
        Object[] copy = new Object[array.length];
        System.arraycopy(array, 0, copy, 0, array.length);

        sort(copy, comparator);
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

}

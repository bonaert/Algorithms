package Algorithms.Sort;

import java.util.Comparator;

public class Selection {

    private Selection() {
    }

    public void sort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int k = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[j - 1]) k = j;
            }
            swap(a, i, k);
        }
    }

    public void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            int k = i;
            for (int j = i + 1; j < a.length; j++) {
                if (less(a, j, j - 1)) k = j;
            }
            swap(a, i, k);
        }
    }

    public void sort(Object[] a, Comparator c) {
        for (int i = 0; i < a.length; i++) {
            int k = i;
            for (int j = i + 1; j < a.length; j++) {
                if (less(a, c, j, j - 1)) k = j;
            }
            swap(a, i, k);
        }
    }

    public int[] sorted(int[] a) {
        int[] copy = new int[a.length];
        System.arraycopy(a, 0, copy, 0, a.length);

        sort(copy);
        return copy;
    }

    public Comparable[] sorted(Comparable[] a) {
        Comparable[] copy = new Comparable[a.length];
        System.arraycopy(a, 0, copy, 0, a.length);

        sort(copy);
        return copy;
    }

    public Object[] sorted(Object[] a, Comparator c) {
        Object[] copy = new Object[a.length];
        System.arraycopy(a, 0, copy, 0, a.length);

        sort(copy, c);
        return copy;
    }


    // Helper methods

    private void swap(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

    private void swap(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private void swap(Object[] a, int i, int j) {
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private boolean less(Comparable[] array, int a, int b) {
        return array[a].compareTo(array[b]) < 0;
    }

    private boolean less(Object[] array, Comparator c, int a, int b) {
        return c.compare(array[a], array[b]) < 0;
    }

}

package Algorithms.Sort;

import java.util.Random;

public class MergeSort {

    private static final int INSERTION_THRESHOLD = 47;

    private MergeSort() {
    }

    public static void sort(int[] a) {
        int[] aux = new int[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private static void sort(int[] a, int[] aux, int low, int high) {

        if (high - low < INSERTION_THRESHOLD) {
            Insertion.sort(a, low, high);
            return;
        }

        int mid = (low + high) / 2;

        sort(a, aux, low, mid);
        sort(a, aux, mid + 1, high);

        merge(a, aux, low, high);
    }

    private static void merge(int[] a, int[] aux, int low, int high) {

        System.arraycopy(a, low, aux, low, high - low + 1);

        int mid = (low + high) / 2;

        int i = low;
        int j = mid + 1;
        for (int index = low; index <= high; index++) {

            if (i > mid) a[index] = aux[j++]; // Left half smaller than the rest of right half
            else if (j > high) a[index] = aux[i++]; // Right half smaller than the rest of left half
            else if (aux[i] > aux[j]) a[index] = aux[j++]; // If right half element is smaller, insert it
            else a[index] = aux[i++]; // Otherwise, insert the left half element
        }
    }

    private static void sort(Comparable[] a, Comparable[] aux, int low, int high) {

        if (high - low < INSERTION_THRESHOLD) {
            Insertion.sort(a, low, high);
            return;
        }

        int mid = (low + high) / 2;

        sort(a, aux, low, mid);
        sort(a, aux, mid + 1, high);

        merge(a, aux, low, high);
    }

    private static void merge(Comparable[] a, Comparable[] aux, int low, int high) {

        System.arraycopy(a, low, aux, low, high - low + 1);

        int mid = (low + high) / 2;

        int i = low;
        int j = mid + 1;
        for (int index = low; index <= high; index++) {

            if (i > mid) a[index] = aux[j++]; // Left half smaller than the rest of right half
            else if (j > high) a[index] = aux[i++]; // Right half smaller than the rest of left half
            else if (less(aux, j, i)) a[index] = aux[j++]; // If right half element is smaller, insert it
            else a[index] = aux[i++]; // Otherwise, insert the left half element
        }
    }


    private static boolean less(Comparable[] array, int a, int b) {
        return array[a].compareTo(array[b]) < 0;
    }

    public static void printArray(int[] a) {
        System.out.println();
        for (int i : a) {
            System.out.print(i + " ");
        }
    }


    public static void main(String[] args) {
        int[] abc = new int[]{7, 6, 3, 4, 1, 6, 5, 2, 8};
        MergeSort.sort(abc);
        assertArrayIsSorted(abc);
        MergeSort.printArray(abc);


        System.out.println();

        abc = new int[]{2, 3, 4, 7, 2, -1, 3, 55, 32, -7, 4, 3};
        MergeSort.sort(abc);
        assertArrayIsSorted(abc);
        MergeSort.printArray(abc);


        int size = (int) Math.pow(10., 7.);

        System.out.println();

        Random random = new Random();
        Stopwatch stopwatch = new Stopwatch();
        int[] randomArray = new int[size];
        for (int i = 0; i < size; i++) {
            randomArray[i] = random.nextInt();
        }

        System.out.println("Generated numbers in " + stopwatch.ellapsedTime());

        stopwatch = new Stopwatch();
        MergeSort.sort(randomArray);
        System.out.println("It took " + stopwatch.ellapsedTime() + " seconds.");

    }

    public static void assertArrayIsSorted(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                throw new Error("Doesn't work! " + a[i] + " " + a[i + 1]);
            }
        }
    }

    public static void assertArrayIsSorted(Comparable[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i].compareTo(a[i + 1]) > 0) throw new Error("Doesn't work!");
        }
    }

}

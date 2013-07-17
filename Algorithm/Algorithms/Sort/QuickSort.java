package Sort;

import Extra.Stopwatch;

import java.util.Random;

public class QuickSort {

    private static final Random random = new Random();
    private static final int INSERTION_THRESHOLD = 47;

    public static void sort(int[] a) {
        sort(a, 0, a.length - 1);
    }

    private static void sort(int[] a, int low, int high) {

        if (high - low < INSERTION_THRESHOLD) {
            Insertion.sort(a, low, high);
            return;
        }

        int pivotIndex = getRandomIndex(low, high);

        int separationIndex = partition(a, low, high, pivotIndex);

        sort(a, low, separationIndex - 1);
        sort(a, separationIndex + 1, high);

    }

    private static int partition(int[] a, int low, int high, int pivotIndex) {

        int pivot = a[pivotIndex];
        swap(a, pivotIndex, high);

        int storeIndex = low;

        for (int i = low; i < high; i++) {
            if (pivot > a[i]) {
                swap(a, i, storeIndex++);
            }
        }

        swap(a, storeIndex, high);
        return storeIndex;
    }

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int low, int high) {

        if (high - low < INSERTION_THRESHOLD) {
            Insertion.sort(a, low, high);
            return;
        }

        int pivotIndex = getRandomIndex(low, high);

        int separationIndex = partition(a, low, high, pivotIndex);

        sort(a, low, separationIndex - 1);
        sort(a, separationIndex + 1, high);

    }

    private static int partition(Comparable[] a, int low, int high, int pivotIndex) {

        Comparable pivot = a[pivotIndex];
        swap(a, pivotIndex, high);

        int storeIndex = low;

        for (int i = low; i < high; i++) {
            if (pivot.compareTo(a[i]) > 0) {
                swap(a, i, storeIndex++);
            }
        }

        swap(a, storeIndex, high);
        return storeIndex;
    }


    private static int getRandomIndex(int low, int high) {
        return random.nextInt(high - low + 1) + low;
    }

    private static void swap(int[] a, int index1, int index2) {
        int temp = a[index1];
        a[index1] = a[index2];
        a[index2] = temp;
    }

    private static void swap(Comparable[] a, int index1, int index2) {
        Comparable temp = a[index1];
        a[index1] = a[index2];
        a[index2] = temp;
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
        QuickSort.printArray(abc);


        System.out.println();

        abc = new int[]{2, 3, 4, 7, 2, -1, 3, 55, 32, -7, 4, 3};
        MergeSort.sort(abc);
        assertArrayIsSorted(abc);
        QuickSort.printArray(abc);


        int size = (int) Math.pow(10., 8.);

        System.out.println();

        Random random = new Random();
        Stopwatch stopwatch = new Stopwatch();
        int[] randomArray = new int[size];
        for (int i = 0; i < size; i++) {
            randomArray[i] = random.nextInt();
        }

        System.out.println("Generated numbers in " + stopwatch.ellapsedTime());

        stopwatch = new Stopwatch();
        QuickSort.sort(randomArray);
        System.out.println("It took " + stopwatch.ellapsedTime() + " seconds.");


    }

    public static void assertArrayIsSorted(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) throw new Error("Doesn't work!");
        }
    }


}

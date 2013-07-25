import java.util.Random;
import Sort.QuickSort;

public class QuickSelect {

    private static final Random random = new Random();

    private QuickSelect() {
    }

    /**
     * Given an array of integers, find the ith smallest
     * number in it. Warning: this method doesn't preserve
     * the order of the elements. If this is crucial, pass an
     * copy of your array. If you use this method an big amount
     * of times, sorting and then simply choosing will be more
     * efficient, if memory is not a problem.
     *
     * @param numbers
     * @param index
     */
    public static int select(int[] numbers, int index) {
        return select(numbers, 0, numbers.length - 1, index);
    }

    private static int select(int[] numbers, int low, int high, int index) {

        if (high == low) return numbers[index];

        int pivotIndex = getRandomIndex(low, high);

        int pivotPosition = QuickSort.partition(numbers, low, high, pivotIndex);

        if (pivotPosition == index) return numbers[index];

        if (index < pivotPosition) {
            return select(numbers, low, pivotPosition - 1, index);
        } else {
            return select(numbers, pivotPosition + 1, high, index);
        }

    }


    /**
     * Given an array of comparable objects, find the ith smallest
     * object in it. Warning: this method doesn't preserve
     * the order of the elements. If this is crucial, pass an
     * copy of your array. If you use this method an big amount
     * of times, sorting and then simply choosing will be more
     * efficient, if memory is not a problem.
     *
     * @param numbers
     * @param index
     */
    public static Comparable select(Comparable[] numbers, int index) {
        return select(numbers, 0, numbers.length - 1, index);
    }

    private static Comparable select(Comparable[] array, int low, int high, int index) {

        if (high == low) return array[index];

        int pivotIndex = getRandomIndex(low, high);

        int pivotPosition = QuickSort.partition(array, low, high, pivotIndex);

        if (pivotPosition == index) return array[index];

        if (index < pivotPosition) {
            return select(array, low, pivotPosition - 1, index);
        } else {
            return select(array, pivotPosition + 1, high, index);
        }

    }


    /**
     * Helper methods
     */


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

    private static boolean greater(Comparable comp1, Comparable comp2) {
        return comp1.compareTo(comp2) > 0;
    }

}

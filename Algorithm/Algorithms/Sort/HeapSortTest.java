package Sort;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class HeapSortTest {

    int length = 100;
    Random random = new Random();
    int[] numbers = new int[length];
    String[] objects = new String[length];
    HeapSort heapSorter = new HeapSort();

    @Before
    public void setUp() throws Exception {
        fillArrayWithRandomNumbers(numbers);
        fillArrayWithRandomStrings(objects);
    }

    private void fillArrayWithRandomStrings(String[] objects) {
        for (int i = 0; i < objects.length; i++) {
             objects[i] = makeRandomString();
        }
    }

    private String makeRandomString() {
        StringBuffer s = new StringBuffer();
        int length = 100;
        for (int i = 0; i < length; i++) {
            s.append((char) random.nextInt(256));
        }

        return s.toString();
    }

    private void fillArrayWithRandomNumbers(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt();
        }
    }

    @Test
    public void testSortingNumbers() {
        heapSorter.sort(numbers);
        Assert.assertTrue("Sorts random numbers correctly", isSorted(numbers));
    }

    @Test
    public void testSortingStrings() {
        heapSorter.sort(objects);
        Assert.assertTrue("Sorts random strings correctly", isSorted(objects));
    }

    private boolean isSorted(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] > numbers[i + 1]){
                return false;
            }
        }
        return true;
    }

    private boolean isSorted(String[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i].compareTo(numbers[i + 1]) > 0){
                return false;
            }
        }
        return true;
    }

}

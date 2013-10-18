package Sort;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class LeastSignificantCharacterSortTest {

    int length = 100;
    Random random = new Random();
    String[] objects = new String[length];

    @Before
    public void setUp() throws Exception {
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

    @Test
    public void testSortingStrings() {
        MergeSort.sort(objects);
        Assert.assertTrue("Sorts random strings correctly", isSorted(objects));
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

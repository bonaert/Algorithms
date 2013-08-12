import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class MedianMaintenanceTest {

    private int[] numbers1;
    private int[] numbers2;
    private int[] numbers3;

    private int result1;
    private int result2;
    private int result3;

    @Before
    public void setUp() throws Exception {
        numbers1 = new int[]{10, 1, 9, 2, 8, 3, 7, 4, 6 ,5};
        numbers2 = new int[]{4, 14, 5, 13, 17, 6, 1, 7, 19, 8, 9, 10, 2, 3, 11, 20, 15, 16, 18, 12};
        numbers3 = new int[]{4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 8, 9, 14, -9};

        result1 = 55;
        result2 = 148;
        result3 = 82;
    }

    @Test
    public void testGetSum() throws Exception {

       MedianMaintenance mm = new MedianMaintenance(numbers1);
       Assert.assertEquals(result1, mm.getSum() % 10000);

       mm = new MedianMaintenance(numbers2);
       Assert.assertEquals(result2, mm.getSum() % 10000);

       mm = new MedianMaintenance(numbers3);
       Assert.assertEquals(result3, mm.getSum() % 10000);

    }
}

package DataStructure;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static junit.framework.Assert.*;

public class MinStackTest {

    private MinStack<Integer> stack;

    @Before
    public void setUp() throws Exception {
        stack = new MinStack<Integer>();
    }

    @Test
    public void emptyQueueHasSizeZeroAndIsEmpty() {
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    public void canInsertOneAndSizeIsOne() {
        stack.insert(1);

        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());
    }

    @Test
    public void canInsertOneAndTwo() {
        stack.insert(1);
        stack.insert(2);

        assertFalse(stack.isEmpty());
        assertEquals(2, stack.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void poppingEmptyQueueRaisesException() {
        stack.pop();
    }

    @Test
    public void insertingOneAndPoppingTheQueueReturnsOne() {
        stack.insert(1);
        assertEquals(stack.pop(), (Integer) 1);
    }

    @Test
    public void insertTwoAndPoppingTheQueueReturnsTwo() {
        stack.insert(2);
        assertEquals(stack.pop(), (Integer) 2);
    }

    @Test
    public void insertTwoAndOne__PoppingReturnOneThenTwo() {
        stack.insert(2);
        stack.insert(1);
        assertFalse(stack.isEmpty());
        assertEquals(2, stack.size());

        assertEquals(stack.pop(), (Integer) 1);
        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());

        assertEquals(stack.pop(), (Integer) 2);
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void findingMinInEmptyQueueRaisesExceptions() {
        stack.min();
    }

    @Test
    public void insertOneAndFindingMin_ReturnsOne() {
        stack.insert(1);
        assertEquals((Integer) 1, stack.min());
        assertEquals(1, stack.size());
        assertFalse(stack.isEmpty());
    }

    @Test
    public void insertOneAndTwoAndFindingMin_ReturnsOne() {
        stack.insert(1);
        stack.insert(2);
        assertEquals((Integer) 1, stack.min());
    }

    @Test
    public void insertTwoAndOneAndFindingMin_ReturnsOne() {
        stack.insert(2);
        stack.insert(1);
        assertEquals((Integer) 1, stack.min());
    }

    @Test
    public void insertOneAndTwoAndZeroAndFindingMin_ReturnsZero() {
        stack.insert(1);
        stack.insert(2);
        stack.insert(0);
        assertEquals((Integer) 0, stack.min());
    }

    @Test
    public void insertOneAndFiveAndZeroAndPop_MinReturnsOne() {
        stack.insert(1);
        stack.insert(5);
        stack.insert(0);

        assertEquals(3, stack.size());
        assertFalse(stack.isEmpty());

        assertEquals((Integer) 0, stack.pop());

        assertEquals((Integer) 1, stack.min());
        assertEquals(2, stack.size());
        assertFalse(stack.isEmpty());
    }

}

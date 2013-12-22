package DataStructure;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

public class HashMapTest {
    private HashMap<Integer, Integer> hashMap;

    @Before
    public void setUp() throws Exception {
        hashMap = new HashMap<Integer, Integer>();
    }

    @Test
    public void emptyHashMapHasSizeZero_AndIsEmpty() {
        assertSizeIsEqualTo(0);
        assertIsEmpty();
    }

    @Test
    public void canInsertOneElementInHashMap_IsNotEmptyAndSizeIsOne() {
        hashMap.put(1, 2);
        assertSizeIsEqualTo(1);
        assertIsNotEmpty();
    }

    @Test
    public void insertOneElement_ContainsKeyAndContainsValueWork() {
        hashMap.put(1, 2);

        assertContainsKey(1);
        assertDoesNotContainKey(0);
        assertDoesNotContainKey(2);

        assertContainsValue(2);
        assertDoesNotContainValue(1);
        assertDoesNotContainValue(3);
    }

    @Test
    public void canPutVariousElementWithDifferentKeys() {
        hashMap.put(1, 2);
        hashMap.put(2, 3);
        hashMap.put(5, 10);

        assertIsNotEmpty();
        assertSizeIsEqualTo(3);
    }

    @Test
    public void canPutVariousElementWithSameKeys() {
        hashMap.put(1, 2);
        hashMap.put(1, 5);
        hashMap.put(1, 7);
        hashMap.put(0, 3);

        assertIsNotEmpty();
        assertSizeIsEqualTo(2);

        assertKeyValuePairIs(1, 7);
        assertKeyValuePairIs(0, 3);
    }

    @Test
    public void canGetElementAfterInsertingItInHashmap() {
        hashMap.put(1, 5);

        assertIsNotEmpty();
        assertSizeIsEqualTo(1);
        assertKeyValuePairIs(1, 5);
        assertEquals(null, hashMap.get(2));
    }

    @Test
    public void addTwoElementsAndRemoveOne_ReturnsGoodElementAndSize() {
        hashMap.put(1, 2);
        hashMap.put(5, 7);
        hashMap.remove(1);

        assertSizeIsEqualTo(1);
        assertIsNotEmpty();

        assertKeyValuePairIs(5, 7);
        assertNull(hashMap.get(1));

        assertDoesNotContainKey(1);
        assertContainsKey(5);

        assertContainsValue(7);
        assertDoesNotContainValue(2);
    }

    @Test
    public void canAddElementsAndClear_SizeGetContainsMethodsWork() {
        hashMap.put(2, 3);
        hashMap.put(4, 5);
        hashMap.put(6, 7);
        hashMap.clear();

        assertIsEmpty();
        assertSizeIsEqualTo(0);

        assertDoesNotContainKey(2);
        assertDoesNotContainKey(4);
        assertDoesNotContainKey(6);

        assertDoesNotContainValue(3);
        assertDoesNotContainValue(5);
        assertDoesNotContainValue(7);
    }

    private void assertDoesNotContainKey(int key) {
        assertFalse(hashMap.containsKey(key));
    }

    private void assertContainsKey(int k) {
        assertTrue(hashMap.containsKey(k));
    }

    private void assertDoesNotContainValue(int value) {
        assertFalse(hashMap.containsValue(value));
    }

    private void assertIsNotEmpty() {
        assertFalse(hashMap.isEmpty());
    }

    private void assertIsEmpty() {
        assertTrue(hashMap.isEmpty());
    }

    private void assertContainsValue(int value) {
        assertTrue(hashMap.containsValue(value));
    }

    private void assertKeyValuePairIs(int key, int value) {
        assertEquals((Integer) value, hashMap.get(key));
    }

    private void assertSizeIsEqualTo(int size) {
        assertEquals(size, hashMap.size());
    }
}

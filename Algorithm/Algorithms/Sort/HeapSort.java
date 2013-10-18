package Sort;

import DataStructure.MinimumPriorityQueue;

import java.util.Iterator;

public class HeapSort {

    public static void sort(int[] a) {
        MinimumPriorityQueue minimumPriorityQueue = new MinimumPriorityQueue(a.length);
        for (int i : a) {
            minimumPriorityQueue.insert(i);
        }

        Iterator<Integer> it = minimumPriorityQueue.iterator();


        for (int i = 0; i < a.length; i++) {
            a[i] = it.next();
        }
    }

    public static void sort(Comparable[] a) {
        MinimumPriorityQueue minimumPriorityQueue = new MinimumPriorityQueue(a.length);
        for (Comparable i : a) {
            minimumPriorityQueue.insert(i);
        }

        Iterator<Integer> it = minimumPriorityQueue.iterator();

        for (int i = 0; i < a.length; i++) {
            a[i] = it.next();
        }
    }

}

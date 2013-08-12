import DataStructure.MaximumPriorityQueue;
import DataStructure.MinimumPriorityQueue;

public class MedianMaintenance {

    private MaximumPriorityQueue<Integer> heapLow;
    private MinimumPriorityQueue<Integer> heapHigh;
    private int sum;
    private int numberElements;

    public MedianMaintenance(String filepath) {

        heapLow = new MaximumPriorityQueue<Integer>();
        heapHigh = new MinimumPriorityQueue<Integer>();

        In in = new In(filepath);
        while (!in.isEmpty()) {
            int number = in.readInt();
            insertElement(number);
            sum += getMedian();
        }
    }

    public MedianMaintenance(int[] numbers) {

        heapLow = new MaximumPriorityQueue<Integer>();
        heapHigh = new MinimumPriorityQueue<Integer>();

        for (int number : numbers) {
            insertElement(number);
            sum += getMedian();
        }
    }

    private void insertElement(int number) {
        if (heapLow.isEmpty() || number <= heapLow.max())
            heapLow.insert(number);
        else
            heapHigh.insert(number);


        numberElements++;
        maintainHalfSizeInvariant();
    }

    private void maintainHalfSizeInvariant() {

        while (heapLow.size() > heapHigh.size() + 1) {
            heapHigh.insert(heapLow.deleteMax());
        }

        while (heapHigh.size() > heapLow.size()) {
            heapLow.insert(heapHigh.deleteMin());
        }
    }

    private int getMedian() {
        return heapLow.max();
    }

    public int getSum() {
        return sum;
    }

    public static void main(String[] args) throws Exception {
        String filepath = "/home/greg/Desktop/median.txt";

        Stopwatch stopwatch = new Stopwatch();

        MedianMaintenance median = new MedianMaintenance(filepath);

        System.out.println("It took " + stopwatch.elapsedTime() + " seconds!");

        System.out.println("The sum of medians is " + median.getSum());
        System.out.println("The sum of medians mod 10000 is " + median.getSum() % 10000);

    }
}

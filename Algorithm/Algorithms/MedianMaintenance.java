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
        int i = 0;
        while (!in.isEmpty()) {
            int number = in.readInt();
            insertElement(number);
            i++;
            sum += getMedian();
            if ((i & (i - 1)) == 0) {
                System.out.println(i + " = " + getMedian() + " (" + sum + ")");
            }

        }
    }

    public MedianMaintenance(int[] numbers){

        heapLow = new MaximumPriorityQueue<Integer>();
        heapHigh = new MinimumPriorityQueue<Integer>();

        for (int number : numbers) {
            insertElement(number);
            sum += getMedian();
            printHeaps();
        }
    }

    public void printHeaps(){
        for (Integer number : heapLow) {
            System.out.print(number + " ");
        }

        System.out.println();

        for (Integer number : heapHigh) {
            System.out.print(number + " ");

        }

        System.out.println();
        System.out.println();
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

        int medianIndex = (numberElements % 2 == 0) ? (numberElements / 2) : ((numberElements + 1) / 2);

        if (medianIndex == heapLow.size()){
            return heapLow.max();
        } else if (medianIndex == heapLow.size() + 1){
            throw new Error();
            //return heapHigh.min();
        } else {
            throw new Error();
        }


    }

    public int getSum() {
        return sum;
    }

    public static void main(String[] args) {
        String filepath = "/home/greg/Desktop/median.txt";

        Stopwatch stopwatch = new Stopwatch();

        MedianMaintenance median = new MedianMaintenance(filepath);

        System.out.println("It took " + stopwatch.elapsedTime() + " seconds!");

        System.out.println("The sum of medians is " + median.getSum());
        System.out.println("The sum of medians mod 10000 is " + median.getSum() % 10000);

    }
}

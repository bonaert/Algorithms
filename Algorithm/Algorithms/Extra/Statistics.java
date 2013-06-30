package Extra;

import java.util.HashMap;
import java.util.Map;

public class Statistics {

    private int sum, size, median, mode;
    private double mean;

    public Statistics(int[] numbers) {
        size = numbers.length;
        Map<Integer, Integer> occurences = new HashMap<Integer, Integer>();
        int temporaryMax = 0;

        // Find mode and sum
        for (int number : numbers) {
            sum += number;
            int previousOccurences;

            if (occurences.containsKey(number)) {
                previousOccurences = occurences.get(number);
            } else {
                previousOccurences = 0;
            }

            previousOccurences++;
            occurences.put(number, previousOccurences);

            if (previousOccurences > temporaryMax) {
                temporaryMax = previousOccurences;
                mode = number;
            }
        }

        // For garbage collection
        occurences = null;

        if (size % 2 == 0) {
            median = (numbers[size / 2] + numbers[size / 2 - 1]) / 2;
        } else {
            median = numbers[size / 2];
        }

        mean = (sum + 0.0) / size;
    }

    public int sum() {
        return sum;
    }

    public int size() {
        return size;
    }

    public double mean() {
        return mean;
    }

    public int median() {
        return median;
    }

    public int mode() {
        return mode;
    }

}

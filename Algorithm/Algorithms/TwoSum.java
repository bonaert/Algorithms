import java.util.HashSet;

public class TwoSum {

    private HashSet<Long> numbers;
    private int numberOfSums;
    private boolean[] found;

    /**
     * Find the number of occurrences of integers n,m such that
     * n + m = t, for every t in [-range, range].
     *
     * @param filepath
     * @param range
     */
    public TwoSum(String filepath, int range) {

        numbers = readIntegersAndBuildHashSet(filepath);
        found = new boolean[2 * range + 1];

        System.out.println("Finished reading numbers");

        for (Long number : numbers) {
            // We choose numbers greater than 0 to remove repetitions, e.g. 2 + (-2) = 0 and (-2) + 2 = 0 would count
            // twice. We can't allow 0 either because 0 + 0 = 0
            if (number > 0) {
                numberOfSums += findNumberOfTwoSums(number, range);
            }
        }

    }

    private int findNumberOfTwoSums(long number, int range) {
        int numberOfCorrectSums = 0;

        for (int t = -range; t <= range; t++) {

            if (!found[t + range] && numbers.contains(t - number)) {
                numberOfCorrectSums++;
                found[t + range] = true;
            }
        }

        return numberOfCorrectSums;
    }


    private HashSet<Long> readIntegersAndBuildHashSet(String filepath) {

        HashSet<Long> numbers = new HashSet<Long>();

        In in = new In(filepath);
        while (!in.isEmpty()) {
            numbers.add(in.readLong());
        }

        return numbers;
    }

    public int getNumberOfSums() {
        return numberOfSums;
    }

    public static void main(String[] args) {

        int range = 10000;
        String filepath = "/home/greg/Desktop/numbers.txt";


        Stopwatch stopwatch = new Stopwatch();
        TwoSum twoSum = new TwoSum(filepath, range);

        System.out.println("There are " + twoSum.getNumberOfSums() + " number of sums in [" + (-range) + " , " + range + "].");
        System.out.println("It took " + stopwatch.elapsedTime() + " seconds!");
    }
}

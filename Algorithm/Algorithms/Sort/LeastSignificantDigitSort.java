package Sort;

public class LeastSignificantDigitSort {

    private LeastSignificantDigitSort() {
    }

    /**
     * Sorts list of strings using least significant
     * digit. If there are strings of highly different lengths,
     * this method is very slow. Instead, use quicksort or
     * mergesort method.
     * @param strings
     */
    public static void sort(String[] strings){

        int max = 0;
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].length() > max) max = strings[i].length();
        }

        sort(strings, max);
    }

    /**
     * Sorts list of strings using least significant
     * digit. If there are strings of highly different lengths,
     * this method is very slow. Instead, use quicksort or
     * mergesort method.
     * @param strings
     * @param numChars
     */
    public static void sort(String[] strings, int numChars) {

        int length = strings.length;
        int R = 256;
        String[] aux = new String[length];

        for (int d = numChars - 1; d >= 0; d--) {

            int[] count = new int[R + 1];

            // Count the number of strings with char at some place
            for (int i = 0; i < length; i++) {
                count[getChar(strings[i], d) + 1]++;
            }

            // Increment count to set place for each string
            for (int i = 0; i < R; i++) {
                count[i + 1] += count[i];
            }

            // Correctly place each string according to char at place d
            for (int i = 0; i < length; i++) {
                aux[count[getChar(strings[i], d)]++] = strings[i];
            }

            System.arraycopy(aux, 0, strings, 0, length);

        }

    }

    /**
     * Returns the char at the given index, if it
     * exists. Otherwise return 0.
     * @param string
     * @param index
     */
    private static int getChar(String string, int index){
        if (string.length() < index + 1){
            return 0;
        }
        return string.charAt(index);
    }

}

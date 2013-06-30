package Algorithms.Extra;

public class Stopwatch {

    private final double startTime;
    private final double nanoSecondsInSecond = Math.pow(10., 9.);

    public Stopwatch() {
        startTime = System.nanoTime();
    }

    public double ellapsedTime() {
        return (System.nanoTime() - startTime) / nanoSecondsInSecond;
    }
}

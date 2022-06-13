package Base.Timer;

public class Timer {

    private long startTime = -1;
    private boolean isRunning = false;

    public void startTimer() {
        if (isRunning) return;

     isRunning = true;
     startTime = System.nanoTime();
    }

    public float stopTimer() {
        if (!isRunning) return 0;

        isRunning = false;
        long time = System.nanoTime() - startTime;
        return time / 1e+9f;
    }

    public float showTimer() {
        return System.nanoTime() - startTime;
    }
    
}

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
        return System.nanoTime() - startTime / 1e+9f;
    }

    public int deltaTime() {
        long lastTime = System.nanoTime();

        if (isRunning) {
            long time = System.nanoTime();
            int deltaTime = (int) ((time - lastTime) / 1000000);
            lastTime = time;
            return deltaTime;
        }

        return 0;
    }

    public boolean Running() {
        return isRunning;
    }
    
}

package org.example;

import java.util.concurrent.atomic.AtomicBoolean;

public class Timekeeper extends Thread {
    private final long timeLimitMillis;
    private long startTimeMillis;
    private final AtomicBoolean gameInProgress;

    public Timekeeper(long timeLimitMillis, AtomicBoolean gameInProgress) {
        this.timeLimitMillis = timeLimitMillis;
        this.gameInProgress = gameInProgress;
    }

    @Override
    public void run() {
        startTimeMillis = System.currentTimeMillis();

        while (gameInProgress.get()) {
            long currentTimeMillis = System.currentTimeMillis();
            long elapsedTimeMillis = currentTimeMillis - startTimeMillis;

            if (elapsedTimeMillis >= timeLimitMillis) {
                System.out.println("Time limit exceeded. Stopping the game.");
                gameInProgress.set(false);
                break;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public boolean isGameFinished() {
        return !gameInProgress.get();
    }
}

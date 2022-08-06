package p21Concurrency.e16Waits;

import java.util.concurrent.TimeUnit;

class Notifier implements Runnable {

    private SleepingExecutor executor;
    private int timeInterval;

    Notifier(SleepingExecutor executor, int timeInterval) {
        this.executor = executor;
        this.timeInterval = timeInterval;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                executor.awaken();
                TimeUnit.SECONDS.sleep(timeInterval);
                executor.waitSleep();
            }
        } catch (InterruptedException e) {
            System.out.println("Notifier was interrupted!");
        }
    }
}

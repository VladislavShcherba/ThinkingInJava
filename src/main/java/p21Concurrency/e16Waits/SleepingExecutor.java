package p21Concurrency.e16Waits;

import java.util.concurrent.TimeUnit;

class SleepingExecutor implements Runnable {

    private boolean awakened;
    private final int requiredTime;

    SleepingExecutor(int requiredTime) {
        this.requiredTime = requiredTime;
    }

    private void execute() throws InterruptedException {
        System.out.println("Started my things!");
        TimeUnit.SECONDS.sleep(requiredTime);
        System.out.println("Ended my things!");
    }

    synchronized void waitAwakened() throws InterruptedException {
        while(!awakened) {
            wait();
        }
    }

    synchronized void waitSleep() throws InterruptedException {
        while(awakened) {
            wait();
        }
    }

    synchronized void awaken() {
        awakened = true;
        notify();
    }

    synchronized void goSleep() {
        awakened = false;
        notify();
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                waitAwakened();
                execute();
                goSleep();
            }
        } catch (InterruptedException e) {
            System.out.println("SleepingExecutor was interrupted!");
        }
    }
}
package p21Concurrency.e17MissedNotify;

class Sleeper implements Runnable {

    boolean sleep = true;

    // incorrect
    void waitSleep() throws InterruptedException {
        while (!sleep) {
            Thread.yield();
            synchronized (this) {
                wait();
            }
        }
    }

    // incorrect
    void waitAwakened() throws InterruptedException {
        while (sleep) {
            Thread.yield();
            synchronized (this) {
                wait();
            }
        }
    }

    synchronized void goSleep() {
        sleep = true;
        notify();
    }

    synchronized void awaken() {
        sleep = false;
        notify();
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                waitAwakened();
                System.out.println("Awakened!");
                goSleep();
            }
        } catch (InterruptedException e) {
            System.out.println("Sleeper was interrupted!");
        }
    }
}
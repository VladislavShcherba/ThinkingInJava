package p21Concurrency.e06Daemons;

import java.util.concurrent.TimeUnit;

class Main {

    public static void main(String[] args) {
        Thread daemonThread = new Thread(new Starter());
        daemonThread.setDaemon(true);
        daemonThread.start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException for main thread");
        } finally {
            System.out.println("finally handler for main thread");
        }
    }

}

class Starter implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Runner());
            thread.start();
        }
    }
}

class Runner implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread() + ", isDaemon() = " + Thread.currentThread().isDaemon());
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException handler for "
                    + Thread.currentThread() + ", isDaemon() = " + Thread.currentThread().isDaemon());
        } finally {
            System.out.println("finally handler for "
                    + Thread.currentThread() + ", isDaemon() = " + Thread.currentThread().isDaemon());
        }
    }
}

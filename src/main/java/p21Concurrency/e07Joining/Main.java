package p21Concurrency.e07Joining;

import java.util.Scanner;

class Main {

    static final int SLEEP = 10000;
    static final int PAUSE = SLEEP + 1000;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Thread sleeper = new Thread(new Sleeper(SLEEP));
        Thread joiner = new Thread(new Joiner(sleeper));
        joiner.start();

        pause();

        sleeper = new Thread(new Sleeper(SLEEP));
        joiner = new Thread(new Joiner(sleeper));
        sleeper.start();
        joiner.start();

        pause();

        sleeper = new Thread(new Sleeper(SLEEP));
        joiner = new Thread(new Joiner(sleeper));
        sleeper.start();
        joiner.start();
        sleeper.interrupt();
        System.out.println("sleeper.isInterrupted: " + sleeper.isInterrupted());

        pause();

        sleeper = new Thread(new Sleeper(SLEEP));
        joiner = new Thread(new Joiner(sleeper));
        sleeper.start();
        joiner.start();
        joiner.interrupt();
        System.out.println("joiner.isInterrupted: " + joiner.isInterrupted());

        pause();

        sleeper = new Thread(new Sleeper(SLEEP));
        joiner = new Thread(new Joiner(sleeper));
        sleeper.start();
        joiner.start();
        sleeper.interrupt();
        joiner.interrupt();
        System.out.println("sleeper.isInterrupted: " + sleeper.isInterrupted());
        System.out.println("joiner.isInterrupted: " + joiner.isInterrupted());
    }

    static void pause() {
        try {
            Thread.sleep(PAUSE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-----------------------------------------");
        sc.next();
    }

}

class Sleeper implements Runnable {

    private static int amount = 0;

    private int id = ++amount;
    private int millis;

    Sleeper(int millis) {
        this.millis = millis;
    }

    @Override
    public void run() {
        System.out.println("Sleeper{id:" + id + "} goes to sleep.");
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println("Sleep of Sleeper{id:" + id + "} has been interrupted.");
            System.out.println("isInterrupted inside 'catch': " + Thread.currentThread().isInterrupted());
        }
        System.out.println("Sleeper{id:" + id + "} has awakened.");
    }
}

class Joiner implements Runnable {

    private static int amount = 0;

    private int id = ++amount;
    private Thread thread;

    Joiner(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        System.out.println("Joiner{id:" + id + "} starts to join.");
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println("Join of Joiner{id:" + id + "} has been interrupted.");
            System.out.println("isInterrupted inside 'catch': " + Thread.currentThread().isInterrupted());
        }
        System.out.println("Joiner{id:" + id + "} has awakened.");
    }
}

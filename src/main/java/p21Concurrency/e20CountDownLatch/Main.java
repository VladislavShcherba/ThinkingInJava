package p21Concurrency.e20CountDownLatch;

import java.time.LocalTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Main {
    public static void main(String[] args) {
        Director director = new Director(10);
        director.start();
    }
}

class Director {
    private CountDownLatch startSignal;
    private CountDownLatch finishSignal;
    private int n;

    Director(int n) {
        startSignal = new CountDownLatch(1);
        finishSignal = new CountDownLatch(n);
        this.n = n;
    }

    void start() {
        System.out.println("Director gives instructions to workers " + LocalTime.now());
        ExecutorService executorService = Executors.newFixedThreadPool(n);
        for (int i = 0; i < n; i++) {
            executorService.execute(new Worker(i, startSignal, finishSignal));
        }
        executorService.shutdown();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            System.out.println("Director was interrupted while was preparing to start workers! " + LocalTime.now());
            executorService.shutdownNow();
            return;
        }
        System.out.println("Director starts workers " + LocalTime.now());
        startSignal.countDown();
        try {
            finishSignal.await();
        } catch (InterruptedException e) {
            System.out.println("Director was interrupted while was waiting for work completion! " + LocalTime.now());
            executorService.shutdownNow();
            return;
        }
        System.out.println("Director is informed that all work has been completed " + LocalTime.now());
    }
}

class Worker implements Runnable {

    private int taskId;
    private CountDownLatch startSignal;
    private CountDownLatch finishSignal;

    Worker(int taskId, CountDownLatch startSignal, CountDownLatch finishSignal) {
        this.taskId = taskId;
        this.startSignal = startSignal;
        this.finishSignal = finishSignal;
    }

    @Override
    public void run() {
        try {
            System.out.println(this + " is waiting for signal to start work " + LocalTime.now());
            startSignal.await();
            System.out.println(this + " starts work " + LocalTime.now());
            TimeUnit.SECONDS.sleep(taskId);
            System.out.println(this + " finishes work " + LocalTime.now());
        } catch (InterruptedException e) {
            System.out.println(this + " was interrupted!");
        } finally {
            finishSignal.countDown();
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + taskId;
    }
}
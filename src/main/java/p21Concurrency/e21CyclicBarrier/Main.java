package p21Concurrency.e21CyclicBarrier;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    static ExecutorService executorService;

    public static void main(String[] args) throws InterruptedException {
        int n = 10;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(n);
        executorService = Executors.newFixedThreadPool(n);
        List<Future<?>> futures = new ArrayList<>(n);
        for(int i=0; i<n; i++) {
            Future<?> future = executorService.submit(new Worker(i, cyclicBarrier));
            futures.add(future);
        }
        TimeUnit.SECONDS.sleep(10);
        // change 0 to 9 to interrupt while sleeping and see different results
        futures.get(0).cancel(true);
    }
}

class Worker implements Runnable {

    private int time;
    private CyclicBarrier barrier;

    Worker(int time, CyclicBarrier barrier) {
        this.time = time;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        while(!Thread.interrupted()) {
            System.out.println(this + " starts work " + LocalTime.now());
            try {
                TimeUnit.SECONDS.sleep(time);
            } catch (InterruptedException e) {
                System.out.println(this + " was interrupted while sleeping! " +
                        "barrier.isBroken(): " + barrier.isBroken() + " " + LocalTime.now());
                Main.executorService.shutdownNow();
                return;
            }
            System.out.println(this + " finished work and waits others " + LocalTime.now());
            try {
                barrier.await();
            } catch (InterruptedException e) {
                System.out.println(this + " was interrupted while waiting! " +
                        "barrier.isBroken(): " + barrier.isBroken() + " " + LocalTime.now());
                Main.executorService.shutdownNow();
                return;
            } catch (BrokenBarrierException e) {
                System.out.println(this + " catches BrokenBarrierException! " +
                        "barrier.isBroken(): " + barrier.isBroken() + " " + LocalTime.now());
                Main.executorService.shutdownNow();
                return;
            }
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + time;
    }
}

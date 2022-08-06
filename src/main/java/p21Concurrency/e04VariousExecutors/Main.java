package p21Concurrency.e04VariousExecutors;

import java.time.LocalTime;
import java.util.concurrent.*;

class Main {

    public static void main(String[] args) {
        testFixedThreadPool();
        testThreadPoolWithLimitedResources();
    }

    static void testFixedThreadPool() {
        System.out.println("Testing FixedThreadPool:");
        int n = 5;
        ExecutorService fixedPoolService = Executors.newFixedThreadPool(n);
        for(int i=0; i<=n; i++) {
            fixedPoolService.execute(new TimeTaker(i, 1000));
        }
        fixedPoolService.shutdown();
        try {
            fixedPoolService.awaitTermination(3000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End of FixedThreadPool test");
    }

    static void testThreadPoolWithLimitedResources() {
        System.out.println("Testing ThreadPoolWithLimitedResources:");
        int queueSize = 5;
        BlockingQueue<Runnable> fixedSizeQueue = new ArrayBlockingQueue<>(queueSize);
        int corePoolSize = 3;
        int maximumPoolSize = 5;
        long keepAlive = 3000;
        ExecutorService limitedPoolService = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAlive,
                TimeUnit.MILLISECONDS,
                fixedSizeQueue);
        try {
            // 5 task are executed in 5 threads, 5 are waiting inside queue, the 11th task will be rejected
            for (int i = 0; i <= queueSize + maximumPoolSize; i++) {
                limitedPoolService.execute(new TimeTaker(i, 1000));
            }
        } finally {
            limitedPoolService.shutdown();
            try {
                limitedPoolService.awaitTermination(3000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("End of ThreadPoolWithLimitedResources test");
        }
    }

}

class TimeTaker implements Runnable {

    private int id;
    private long millis;

    TimeTaker(int id, long millis) {
        this.id = id;
        this.millis = millis;
    }

    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
            System.out.println("TimeTaker(" + id + ") finished at " + LocalTime.now());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return getClass().getName() + "(" + id + ")";
    }
}

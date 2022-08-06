package p21Concurrency.e05Priorities;

import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

class Main {

    public static void main(String[] args) {
        PriorityThreadFactory minPriorityThreadFactory = new PriorityThreadFactory(Thread.MIN_PRIORITY);
        PriorityThreadFactory maxPriorityThreadFactory = new PriorityThreadFactory(Thread.MAX_PRIORITY);

        ExecutorService minPriorityPool = Executors.newCachedThreadPool(minPriorityThreadFactory);
        ExecutorService maxPriorityPool = Executors.newCachedThreadPool(maxPriorityThreadFactory);

        for (int i = 0; i < 10; i++) {
            minPriorityPool.submit(new Fibonacci(100000));
        }
        for (int i = 0; i < 10; i++) {
            maxPriorityPool.submit(new Fibonacci(100000));
        }

        minPriorityPool.shutdown();
        maxPriorityPool.shutdown();
    }

}

class PriorityThreadFactory implements ThreadFactory {

    private final int priority;

    PriorityThreadFactory(int priority) {
        this.priority = priority;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setPriority(priority);
        return thread;
    }
}

class Fibonacci implements Runnable {

    private final int nth;

    Fibonacci(int nth) {
        this.nth = nth;
    }

    @Override
    public void run() {
        BigInteger first = BigInteger.valueOf(1);
        BigInteger second = BigInteger.valueOf(1);
        BigInteger result = BigInteger.valueOf(1);
        for (int i = 3; i <= nth; i++) {
            result = first.add(second);
            first = second;
            second = result;
        }
        System.out.println(Thread.currentThread() + " : " + result);
    }
}

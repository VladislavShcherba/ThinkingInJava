package p21Concurrency.e02SimpleExecutor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class Main {

    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
        ScheduledFuture<?> future = pool.scheduleAtFixedRate(() -> new Ignition(10).run(), 2, 2, TimeUnit.SECONDS);
        ScheduledFuture<?> cancellation = pool.schedule(() -> future.cancel(true), 10, TimeUnit.SECONDS);
        try {
            System.out.print("cancellation : " + cancellation.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
    }
}

class Ignition implements Runnable {

    private static int amount = 0;

    private final int id = amount++;
    private int currentState;

    Ignition(int initialState) {
        currentState = initialState;
    }

    @Override
    public void run() {
        while (currentState-- > 0) {
            System.out.print("#" + id + "(" + (currentState == 0 ? "Ignition!)\n" : currentState + "), "));
        }
    }
}




package p21Concurrency.e16Waits;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Main {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        SleepingExecutor sleepingExecutor = new SleepingExecutor(3);
        Notifier notifier = new Notifier(sleepingExecutor, 5);
        executorService.execute(sleepingExecutor);
        executorService.execute(notifier);
        executorService.execute(new Ticker());
        TimeUnit.SECONDS.sleep(20);
        executorService.shutdownNow();
    }
}

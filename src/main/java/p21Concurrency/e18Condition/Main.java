package p21Concurrency.e18Condition;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Main {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        BlackWhiteNotifier notifier = new BlackWhiteNotifier(10, 15);
        notifier.executeActions(executorService);
        executorService.execute(notifier);
        TimeUnit.SECONDS.sleep(10);
        executorService.shutdownNow();
    }
}

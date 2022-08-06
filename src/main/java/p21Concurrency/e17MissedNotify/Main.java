package p21Concurrency.e17MissedNotify;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Main {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Sleeper sleeper = new Sleeper();
        Notifier notifier = new Notifier(sleeper);
        executorService.execute(sleeper);
        executorService.execute(notifier);
        TimeUnit.SECONDS.sleep(5);
        executorService.shutdownNow();
    }
}


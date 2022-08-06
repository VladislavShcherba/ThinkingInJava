package p21Concurrency.e11Lock;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock gives more flexibility than keyword 'synchronized'.
 * In the example different instances of different classes use the same lock object.
 */
class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        HasLock hasLock = new HasLock();
        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(() -> hasLock.threadSafeF(5));
        executorService.execute(() -> hasLock.threadSafeF(5));

        sc.next();

        LockKeeper lockKeeper = new LockKeeper(hasLock.getLock());
        executorService.execute(()-> lockKeeper.keep(5));
        executorService.execute(() -> hasLock.threadSafeF(5));

        executorService.shutdown();
    }
}

class HasLock {

    private Lock lock = new ReentrantLock();

    Lock getLock() {
        return lock;
    }

    void threadSafeF(int seconds) {
        System.out.println("Inside threadSafeF(). Trying to get lock");
        lock.lock();
        try {
            System.out.println("ThreadSafeF() has got lock and will be executing for " + seconds + " seconds");
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("ThreadSafeF() is going go give away lock");
            lock.unlock();
        }
    }
}

class LockKeeper {

    private Lock lock;

    LockKeeper(Lock lock) {
        this.lock = lock;
    }

    void keep(int seconds) {
        System.out.println("Inside keep(). Trying to get lock");
        lock.lock();
        try {
            System.out.println("keep() has got lock and will hold it for " + seconds + " seconds");
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("keep() is going go give away lock");
            lock.unlock();
        }
    }
}

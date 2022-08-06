package p21Concurrency.e15Interruption;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Main {

    static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        InterruptedSleep interruptedSleep = new InterruptedSleep();
        System.out.println("Interrupt while sleeping:");
        Future<?> submittedTask = executorService.submit(interruptedSleep);
        sleep(3);
        System.out.println("Going to cancel task");
        submittedTask.cancel(true);
        sleep(1);
        System.out.println("-----");

        System.out.println("Can not interrupt lock:");
        NotInterruptedLock notInterruptedLock = new NotInterruptedLock();
        notInterruptedLock.getLock().lock();
        submittedTask = executorService.submit(notInterruptedLock);
        sleep(3);
        System.out.println("Going to cancel task");
        submittedTask.cancel(true);
        sleep(3);
        System.out.println("Going to unlock inside main()");
        notInterruptedLock.getLock().unlock();
        sleep(1);
        System.out.println("-----");

        System.out.println("Can interrupt lock:");
        InterruptedLock interruptedLock = new InterruptedLock();
        interruptedLock.getLock().lock();
        submittedTask = executorService.submit(interruptedLock);
        sleep(3);
        System.out.println("Going to cancel task");
        submittedTask.cancel(true);
        sleep(1);
        System.out.println("-----");
        executorService.shutdown();
    }

}

class InterruptedSleep implements Runnable {
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println("Inside while(). Going to sleep");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("Sleep finished");
            }
            System.out.println("Outside while()");
        } catch (InterruptedException e) {
            System.out.println("Interrupted!");
        }
        System.out.println("Going to exit run()");
    }
}

class NotInterruptedLock implements Runnable {

    private Lock lock = new ReentrantLock();

    Lock getLock() {
        return lock;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            System.out.println("Going to lock()");
            lock.lock();
            System.out.println("Lock finished");
        }
        System.out.println("Outside while(). Going to exit run()");
    }
}

class InterruptedLock implements Runnable {

    private Lock lock = new ReentrantLock();

    Lock getLock() {
        return lock;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println("Going to lockInterruptibly()");
                lock.lockInterruptibly();
                System.out.println("Lock finished");
            }
            System.out.println("Outside while()");
        } catch (InterruptedException e) {
            System.out.println("Interrupted!");
        }
        System.out.println("Going to exit run()");
    }
}

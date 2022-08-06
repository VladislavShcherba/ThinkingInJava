package p21Concurrency.e10Synchronized;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * When a thread is executing inside a synchronized non-static method, then other threads are blocked
 * when they try to access any synchronized non-static method of the same instance, but the threads can access
 * synchronized non-static methods of other instances of that class or static methods of that class.
 *
 * When a thread is executing inside a static method, then other threads are blocked when they try to access
 * any synchronized static method of that class, but the threads can access synchronized non-static methods.
 */
class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        A a = new A();
        A aa = new A();
        ExecutorService executorService = Executors.newCachedThreadPool(new NamedTFactory());

        executorService.execute(() -> a.f(5));
        executorService.execute(() -> a.g(5));
        executorService.execute(() -> a.z(1));

        sc.next();

        executorService.execute(() -> A.sf(5));
        executorService.execute(() -> A.sg(5));
        executorService.execute(() -> A.sz(1));

        sc.next();

        executorService.execute(() -> a.f(5));
        executorService.execute(() -> A.sf(5));

        sc.next();

        executorService.execute(() -> a.f(5));
        executorService.execute(() -> aa.f(5));

        executorService.shutdown();
    }
}

class A {

    static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized static void sf(int seconds) {
        System.out.println("Inside synchronized static sf()");
        System.out.println("Thread: " + Thread.currentThread().getName());
        sleep(seconds);
        System.out.println("Leaving synchronized static sf()");
    }

    synchronized static void sg(int seconds) {
        System.out.println("Inside synchronized static sg()");
        System.out.println("Thread: " + Thread.currentThread().getName());
        sleep(seconds);
        System.out.println("Leaving synchronized static sg()");
    }

    static void sz(int seconds) {
        System.out.println("Inside static sz()");
        System.out.println("Thread: " + Thread.currentThread().getName());
        sleep(seconds);
        System.out.println("Leaving static sz()");
    }

    synchronized void f(int seconds) {
        System.out.println("Inside synchronized f()");
        System.out.println("Thread: " + Thread.currentThread().getName());
        sleep(seconds);
        System.out.println("Leaving synchronized f()");
    }

    synchronized void g(int seconds) {
        System.out.println("Inside synchronized g()");
        System.out.println("Thread: " + Thread.currentThread().getName());
        sleep(seconds);
        System.out.println("Leaving synchronized g()");
    }

    void z(int seconds) {
        System.out.println("Inside z()");
        System.out.println("Thread: " + Thread.currentThread().getName());
        sleep(seconds);
        System.out.println("Leaving z()");
    }
}

class NamedTFactory implements ThreadFactory {

    private static int count = 0;

    @Override
    public Thread newThread(Runnable runnable) {
        return new Thread(runnable, "thread-" + ++count);
    }
}















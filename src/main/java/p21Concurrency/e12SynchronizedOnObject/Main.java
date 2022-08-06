package p21Concurrency.e12SynchronizedOnObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static p21Concurrency.e12SynchronizedOnObject.Main.sleep;

/**
 * Synchronized methods and synchronized(this) both synchronize on the same object (this).
 * Synchronization on another object does not block with synchronized methods or synchronized(this).
 */
class Main {

    static void sleep() {
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        B b = new B();
        executorService.execute(b::f);
        executorService.execute(b::g);
        executorService.execute(b::h);
        executorService.execute(b::f1);
        executorService.execute(b::g1);
        executorService.execute(b::h1);
        executorService.shutdown();
    }
}

class A {

    protected Object syncObject = new Object();

    synchronized void f() {
        for (int i = 0; i < 5; i++) {
            System.out.println("synchronized void f()");
            sleep();
            Thread.yield();
        }
    }

    void g() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                System.out.println("void g() synchronized (this)");
                sleep();
                Thread.yield();
            }
        }
    }

    void h() {
        synchronized (syncObject) {
            for (int i = 0; i < 5; i++) {
                System.out.println("void h() synchronized (syncObject)");
                sleep();
                Thread.yield();
            }
        }
    }
}

class B extends A {

    synchronized void f1() {
        for (int i = 0; i < 5; i++) {
            System.out.println("synchronized void f1()");
            sleep();
            Thread.yield();
        }
    }

    void g1() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                System.out.println("void g1() synchronized (this)");
                sleep();
                Thread.yield();
            }
        }
    }

    void h1() {
        synchronized (syncObject) {
            for (int i = 0; i < 5; i++) {
                System.out.println("void h1() synchronized (syncObject)");
                sleep();
                Thread.yield();
            }
        }
    }
}

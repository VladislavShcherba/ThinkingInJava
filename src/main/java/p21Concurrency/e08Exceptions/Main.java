package p21Concurrency.e08Exceptions;

import java.util.concurrent.TimeUnit;

class Main {

    public static void main(String[] args) throws InterruptedException {
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionCatcher("Default"));

        Thread thread = new Thread(new ExceptionThrower());
        thread.setUncaughtExceptionHandler(new ExceptionCatcher("Individual"));
        thread.start();

        TimeUnit.MILLISECONDS.sleep(500);

        thread = new Thread(new ExceptionThrower());
        thread.start();
    }
}

class ExceptionThrower implements Runnable {

    @Override
    public void run() {
        System.out.println("ExceptionThrower.run() has been started");
        System.out.println("Thread : " + Thread.currentThread() +
                ", exception handler : " + Thread.currentThread().getUncaughtExceptionHandler());
        System.out.println("Going to throw exception");
        throw new RuntimeException();
    }
}

class ExceptionCatcher implements Thread.UncaughtExceptionHandler {

    private static int count = 0;

    private final int id = ++count;
    private final String name;

    ExceptionCatcher(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ExceptionCatcher{id: " + id + ", name: " + name + "}";
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(this + ".uncaughtException() has been started");
        System.out.println("Thread : " + t + ", Throwable : " + e);
    }
}

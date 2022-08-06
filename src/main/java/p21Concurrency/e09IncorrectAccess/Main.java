package p21Concurrency.e09IncorrectAccess;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Main {

    public static void main(String[] args) {
        EvenGenerator generator = new EvenGenerator();
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            pool.execute(new EvenChecker(generator));
        }
        pool.shutdown();
    }
}

class EvenGenerator {

    private int current = 0;
    private boolean canceled = false;

    // uncomment synchronized to solve the problem
    /*synchronized*/ int next() {
        current++;
        current++;
        return current;
    }

    void cancel() {
        canceled = true;
    }

    boolean isCanceled() {
        return canceled;
    }
}

class EvenChecker implements Runnable {

    private final EvenGenerator generator;

    EvenChecker(EvenGenerator generator) {
        this.generator = generator;
    }

    @Override
    public void run() {
        while (!generator.isCanceled()) {
            if (generator.next() % 2 != 0) {
                System.out.println("Not even!");
                generator.cancel();
            }
        }
    }
}

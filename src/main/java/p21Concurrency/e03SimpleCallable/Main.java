package p21Concurrency.e03SimpleCallable;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Main {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        List<Future<BigInteger>> fibonaccis = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            fibonaccis.add(pool.submit(new Fibonacci(i * 1000)));
        }
        pool.shutdown();
        for (int i = 0; i < 100; i++) {
            try {
                System.out.println("Fibonacci(" + i * 1000 + ") = " + fibonaccis.get(i).get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

}

class Fibonacci implements Callable<BigInteger> {

    private final int nth;

    Fibonacci(int nth) {
        this.nth = nth;
    }

    @Override
    public BigInteger call() {
        BigInteger first = BigInteger.valueOf(1);
        BigInteger second = BigInteger.valueOf(1);
        BigInteger result = BigInteger.valueOf(1);
        for (int i = 3; i <= nth; i++) {
            result = first.add(second);
            first = second;
            second = result;
        }
        return result;
    }
}




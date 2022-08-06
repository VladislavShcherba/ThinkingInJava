package p21Concurrency.e16Waits;

import java.util.concurrent.TimeUnit;

class Ticker implements Runnable {

    private int i;

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(i++);
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            System.out.println("Ticker was interrupted!");
        }
    }
}

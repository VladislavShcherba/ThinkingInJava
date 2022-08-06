package p21Concurrency.e19BlockingQueue;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class PolisherSystem {

    private BlockingQueue<WaxSpreader> freeWaxSpreaders;
    private BlockingQueue<Polisher> freePolishers;

    PolisherSystem(int amountOfWaxSpreaders, int amountOfPolishers, int maxHandleTime) {
        freeWaxSpreaders = new ArrayBlockingQueue<>(amountOfWaxSpreaders);
        freePolishers = new ArrayBlockingQueue<>(amountOfPolishers);
        Random random = new Random();

        for (int i = 0; i < amountOfWaxSpreaders; i++) {
            int requiredTime = random.nextInt(maxHandleTime) + 1;
            freeWaxSpreaders.add(new WaxSpreader(i, requiredTime));
        }

        for (int i = 0; i < amountOfPolishers; i++) {
            int requiredTime = random.nextInt(maxHandleTime) + 1;
            freePolishers.add(new Polisher(i, requiredTime));
        }
    }

    void requestWaxing(Car car) throws InterruptedException, WaxSpreader.AlreadyWaxedException {
            WaxSpreader waxSpreader = freeWaxSpreaders.take();
            waxSpreader.spreadWax(car);
            freeWaxSpreaders.add(waxSpreader);
    }

    void requestPolishing(Car car) throws InterruptedException, Polisher.NoWaxException {
            Polisher polisher = freePolishers.take();
            polisher.polish(car);
            freePolishers.add(polisher);
    }
}

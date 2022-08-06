package p21Concurrency.e19BlockingQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Main {

    private static final int AMOUNT_OF_CARS = 1000;
    private static final int MAX_DESIRED_NUMBER_OF_POLISHINGS = 5;
    private static final int AMOUNT_OF_POLISHERS = 100;
    private static final int AMOUNT_OF_WAX_SPREADERS = 90;
    private static final int MAX_HANDLE_TIME = 100;

    public static void main(String[] args) {
        PolisherSystem polisherSystem =
                new PolisherSystem(AMOUNT_OF_WAX_SPREADERS, AMOUNT_OF_POLISHERS, MAX_HANDLE_TIME);

        List<Car> cars = new ArrayList<>(AMOUNT_OF_CARS);
        Random random = new Random();
        for (int i=0; i<AMOUNT_OF_CARS; i++) {
            int desiredNumberOfPolishings = random.nextInt(MAX_DESIRED_NUMBER_OF_POLISHINGS) + 1;
            cars.add( new Car(i, desiredNumberOfPolishings, polisherSystem) );
        }

        ExecutorService executorService = Executors.newFixedThreadPool(AMOUNT_OF_CARS);
        for(Car car : cars) {
            executorService.execute(car);
        }

        executorService.shutdown();
    }
}

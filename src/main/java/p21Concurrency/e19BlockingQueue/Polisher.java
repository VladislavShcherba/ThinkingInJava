package p21Concurrency.e19BlockingQueue;

import java.util.concurrent.TimeUnit;

class Polisher {

    private int id;
    private int requiredTime;

    Polisher(int id, int requiredTime) {
        this.id = id;
        this.requiredTime = requiredTime;
    }

    void polish(Car car) throws NoWaxException, InterruptedException {
        System.out.println("Started polishing car (Polisher: " + this + ", car: " + car + ")");
        if(!car.isWaxOn()) {
            System.out.println("Car " + car + " has no wax (Polisher: " + this +")");
            throw new NoWaxException();
        }
        TimeUnit.MILLISECONDS.sleep(requiredTime);
        car.setWaxOn(false);
        car.incrementNumberOfPolishings();
        System.out.println("Ended polishing car (Polisher: " + this + ", car: " + car + ")");
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[" + id + "]";
    }

    static class NoWaxException extends Exception {}
}

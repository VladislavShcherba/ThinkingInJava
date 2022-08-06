package p21Concurrency.e19BlockingQueue;

import java.util.concurrent.TimeUnit;

class WaxSpreader {

    private int id;
    private int requiredTime;

    WaxSpreader(int id, int requiredTime) {
        this.id = id;
        this.requiredTime = requiredTime;
    }

    void spreadWax(Car car) throws AlreadyWaxedException, InterruptedException {
        System.out.println("Started spreading wax (WaxSpreader: " + this + ", car: " + car + ")");
        if(car.isWaxOn()) {
            System.out.println("Car " + car + " is already waxed (WaxSpreader: " + this +")");
            throw new AlreadyWaxedException();
        }
        TimeUnit.MILLISECONDS.sleep(requiredTime);
        car.setWaxOn(true);
        System.out.println("Ended spreading wax (WaxSpreader: " + this + ", car: " + car + ")");
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[" + id + "]";
    }

    static class AlreadyWaxedException extends Exception {}
}

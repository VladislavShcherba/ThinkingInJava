package p21Concurrency.e19BlockingQueue;

class Car implements Runnable {

    private final int id;
    private final int desiredNumberOfPolishings;
    private final PolisherSystem polisherSystem;

    private int numberOfPolishings = 0;
    private boolean waxOn = false;

    Car(int id, int desiredNumberOfPolishings, PolisherSystem polisherSystem) {
        this.id = id;
        this.desiredNumberOfPolishings = desiredNumberOfPolishings;
        this.polisherSystem = polisherSystem;
    }

    boolean isWaxOn() {
        return waxOn;
    }

    void setWaxOn(boolean waxOn) {
        this.waxOn = waxOn;
    }

    void incrementNumberOfPolishings() {
        numberOfPolishings++;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[" + id + "]";
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                if (numberOfPolishings < desiredNumberOfPolishings) {
                    if (!waxOn) {
                        System.out.println("Car " + this + " is requesting to be waxed");
                        polisherSystem.requestWaxing(this);
                    } else {
                        System.out.println("Car " + this + " is requesting to be polished");
                        polisherSystem.requestPolishing(this);
                    }
                } else {
                    System.out.println("Car " + this + " was handled (desired: " + desiredNumberOfPolishings +
                            ", gained: " + numberOfPolishings + ")");
                    return;
                }
            }
        } catch (WaxSpreader.AlreadyWaxedException | Polisher.NoWaxException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package p21Concurrency.e17MissedNotify;

class Notifier implements Runnable {

    private Sleeper sleeper;

    Notifier(Sleeper sleeper) {
        this.sleeper = sleeper;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                sleeper.awaken();
                System.out.println("Awaken!");
                sleeper.waitSleep();
            }
        } catch (InterruptedException e) {
            System.out.println("Notifier was interrupted!");
        }
    }
}

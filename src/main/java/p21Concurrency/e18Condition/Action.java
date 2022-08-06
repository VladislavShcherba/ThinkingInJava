package p21Concurrency.e18Condition;

import java.util.Random;
import java.util.concurrent.TimeUnit;

abstract class Action implements Runnable {

    private static final Random random = new Random();

    private int id;
    private BlackWhiteNotifier notifier;
    private boolean awaken = false;
    private int sleepTime;

    protected Action(int id, BlackWhiteNotifier notifier) {
        this.id = id;
        this.notifier = notifier;
        sleepTime = random.nextInt(1000) + 1;
    }

    protected int getId() {
        return id;
    }

    protected BlackWhiteNotifier getNotifier() {
        return notifier;
    }

    boolean isAwaken() {
        return awaken;
    }

    void setAwaken(boolean awaken) {
        this.awaken = awaken;
    }

    protected abstract void waitAwaken() throws InterruptedException;

    protected abstract void actionComplete();

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                waitAwaken();
                System.out.println(this + " awakened!");
                TimeUnit.MILLISECONDS.sleep(sleepTime);
                actionComplete();
            }
        } catch (InterruptedException e) {
            System.out.println(this + " interrupted!");
        }
    }

    static Action newAction(int id, BlackWhiteNotifier notifier, Color color) {
        if(color == Color.BLACK) {
            return new Black(id, notifier);
        }
        return new White(id, notifier);
    }

    enum Color {

        BLACK, WHITE;

        static Color random() {
            return random.nextInt(2) == 0 ? BLACK : WHITE;
        }
    }
}

package p21Concurrency.e01SimpleThread;

class Main {

    public static void main(String[] args) {
        for(int i = 0; i < 3; i++) {
            new Thread(new Ignition(10)).start();
        }
    }
}

class Ignition implements Runnable {

    private static int amount = 0;

    private final int id = amount++;
    private int currentState;

    Ignition(int initialState) {
        currentState = initialState;
    }

    @Override
    public void run() {
        while (currentState-- > 0) {
            System.out.print("#" + id + "(" + (currentState == 0 ? "Ignition!" : currentState) + "), ");
            Thread.yield();
        }
    }
}

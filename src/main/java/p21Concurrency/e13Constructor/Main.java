package p21Concurrency.e13Constructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Incorrect runaway of a reference to not fully initialized object in multithreading application.
 */
class Main {

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                new A();
            }
        }).start();
        while(true) {
            for(A a : new ArrayList<>(A.allA)) {
                if(a.getI() == 0) {
                    System.out.println("0!");
                    System.exit(0);
                }
            }
        }
    }
}

class A {

    static List<A> allA = new ArrayList<>();

    private int i;

    int getI() {
        return i;
    }

    A() {
        //Object is not fully initialized
        allA.add(this);
        Thread.yield();
        i = 1;
    }
}

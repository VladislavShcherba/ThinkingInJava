package p21Concurrency.e14ThreadLocal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class Main {

    public static void main(String[] args) {
        int nTests = 21;
        int nThreads = 5;
        TestCollection testCollection = new TestCollection(nTests);
        List<TestCollection> subCollections = testCollection.splitEvenly(nThreads);
        for(int i = 0; i < nThreads; i++) {
            final int finalI = i;
            new Thread(() -> {
                for(TestScript t : subCollections.get(finalI)) {
                    ThreadLocalDriver.getDriver().test(t);
                }
            }).start();
        }
    }
}

class ThreadLocalDriver {

    private static final ThreadLocal<Driver> threadLocalDriver = ThreadLocal.withInitial(Driver::new);

    static Driver getDriver() {
        return threadLocalDriver.get();
    }
}



class Driver {

    private static int count = 0;
    private final int id = count++;

    private final BrowserInstance browserInstance = new BrowserInstance();

    void test(TestScript script) {
        System.out.println(this + " tests " + script);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "#" + id + " controls " + browserInstance;
    }
}

class BrowserInstance {

    private static int count = 0;
    private final int id = count++;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "#" + id;
    }
}

class TestCollection extends ArrayList<TestScript> {

    TestCollection(int n) {
        super(n);
        for(int i=0; i<n; i++) {
            add(new TestScript("UI test " + i));
        }
    }

    private TestCollection(Collection<? extends TestScript> c) {
        super(c);
    }

    List<TestCollection> splitEvenly(int nParts) {
        int n = size() / nParts;
        ArrayList<TestCollection> subCollections = new ArrayList<>(nParts);
        for(int i = 0; i < nParts; i++) {
            subCollections.add(new TestCollection(
                    subList(n * i, i == nParts - 1 ? size() : n * (i + 1))
            ));
        }
        return subCollections;
    }
}

class TestScript {

    private final String name;

    TestScript(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + name + ")";
    }
}
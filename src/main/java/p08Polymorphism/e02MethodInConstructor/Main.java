package p08Polymorphism.e02MethodInConstructor;

/**
 * Danger of calling a method that can be overridden in a constructor.
 */
class Main {

    public static void main(String[] args) {
        new B(10,20);
    }
}

class A {

    int x;

    A(int x) {
        this.x = x;
        // 'f' is neither private, nor final, so can be overridden in a subclass
        f();
    }

    void f() {
        System.out.println("A.f() : x = " + x);
    }
}

class B extends A {

    int y;

    B(int x, int y) {
        super(x); // B.y is not initialized when building A instance
        this.y = y;
    }

    @Override
    void f() {
        System.out.println("B.f() : x = " + x + ", y = " + y);
    }
}
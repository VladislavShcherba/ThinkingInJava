package p09Interfaces.e01AutomaticImplementation;

/**
 * Interface implementation with already implemented method.
 */
class Main {

    public static void main(String[] args) {
        A a = new B();
        IA ia = new B();
        IB ib = new B();
        a.f();
        ia.f();
        ib.f();
    }
}

class A {
    public void f() {
        System.out.println("A.f()");
    }
}

interface IA {
    void f();
}

interface IB {
    void f();
}

// B implements IA and IB since it already has an implementation of the required method
class B extends A implements IA, IB {}
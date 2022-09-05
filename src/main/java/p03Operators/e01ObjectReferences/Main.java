package p03Operators.e01ObjectReferences;

/**
 * An object variable is a references to an object.
 * Assigning (copying) an object means assigning (copying) a reference to an object.
 */
class Main {

    public static void main(String[] args) {

        A a1 = new A(1);
        A a2 = new A(2);
        a2 = a1;
        a1.x = 11;
        System.out.println("a2.x = " + a2.x);

        A a3 = new A(3);
        doubleUpA(a3);
        System.out.println("a3.x = " + a3.x);

        HolderA holderA = new HolderA(10);
        A someA = holderA.getA();
        someA.x = 100;
        System.out.println("holderA.a.x = " + holderA.a.x);
    }

    static void doubleUpA(A a) {
        a.x *= 2;
    }
}

class A {
    int x;

    A(int x) {
        this.x = x;
    }
}

class HolderA {
    A a;

    HolderA(int x) {
        a = new A(x);
    }

    A getA() {
        return a;
    }
}

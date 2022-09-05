package p10InnerClasses.e02Extending;

/**
 * Extending inner classes.
 */
class Main {

    public static void main(String[] args) {
        A a = new A();
        A.B b = a.new B(1);
        A.B1 b1 = a.new B1(2);
        B2 b2 = new B2(a, 3);
    }
}

class A {

    class B {
        private int i;
        B(int i) {
            this.i = i;
            System.out.println("A.B(" + i + ")");
        }
    }

    class B1 extends B {
        B1(int i) {
            super(i);
            System.out.println("A.B1(" + i + ")");
        }
    }
}

class B2 extends A.B {

    B2(A a, int i) {
        a.super(i);
        System.out.println("B2(" + i + ")");
    }
}


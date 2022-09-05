package p03Operators.e03ObjectEquivalence;

/**
 * Operators == and != compare object references, not content.
 */
class Main {
    public static void main(String[] args) {
        A a1 = new A(1);
        A a2 = new A(1);
        System.out.println("a1 == a2 : " + (a1 == a2));
    }
}

class A {
    int x;

    A(int x) {
        this.x = x;
    }
}

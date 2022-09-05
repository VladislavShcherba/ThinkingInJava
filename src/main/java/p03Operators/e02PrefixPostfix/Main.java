package p03Operators.e02PrefixPostfix;

/**
 * Post-increment (post-decrement) first produces a value, then performs an operation.
 * Pre-increment (pre-decrement) vice versa.
 */
class Main {

    public static void main(String[] args) {
        int x = 1;
        int a = x++;
        System.out.println("a = " + a + ", x = " + x);
        int b = ++x;
        System.out.println("b = " + b + ", x = " + x);
    }
}

package p03Operators.e04ShortCircuiting;

/**
 * A logical expression stops evaluating after the resulting value becomes unambiguous.
 * A bitwise expression evaluates entirely.
 * Ternary if-else operator evaluates only one expression after '?'.
 */
class Main {

    public static void main(String[] args) {
        if(isA() || isB()){} // minimum required executions are performed
        if(isA() | isB()){}  // all executions are performed
        boolean b = isA() ? isA() : isB();
    }

    static boolean isA() {
        System.out.println("isA()");
        return true;
    }

    static boolean isB() {
        System.out.println("isB()");
        return true;
    }
}

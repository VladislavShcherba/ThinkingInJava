package p10InnerClasses.e01StaticMembers;

/**
 * Static members inside inner classes (non-static) are not allowed for JDK less than 16.
 */
class Main {
    public static void main(String[] args) {

    }
}

class A {
    class B {
        // static members inside inner classes are not allowed:
        //static int x;
        //static void f(){}
        //static class C{}
        //static{}
        // exception is static final fields initialized by constant expressions
        static final int X = 10;
    }
}

class A1 {
    static class B {
        // static members inside nested classes are allowed:
        static int x;
        static void f(){}
        static class C{}
        static{}
    }
}
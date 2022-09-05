package p07ReusingClasses.e02PrivateOverride;

/**
 * It is not possible to override a method that is not visible in the subclass.
 */
class Main {
    public static void main(String[] args) {
        new B().f();
    }
}

class A {
    private void f() {
        System.out.println("A.f()");
    }
}

class B extends A {
    //@Override // compile-time error : Method does not override method from its superclass
    // This is a completely new method, since A.f() is not visible from here:
    public void f() {
        System.out.println("B.f()");
    }
}
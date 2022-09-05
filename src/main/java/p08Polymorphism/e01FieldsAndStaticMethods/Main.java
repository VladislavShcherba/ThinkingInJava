package p08Polymorphism.e01FieldsAndStaticMethods;

/**
 * Polymorphism does not work for fields and static methods.
 */
class Main {
    public static void main(String[] args) {
        A a = new B();
        // 'a.a' refers to A instance of 'a'.
        System.out.println("a.a = " + a.a);
        // B.f() overrides A.f(), so B.f() returns B instance of 'a'.
        System.out.println("a.f() = " + a.f());
        // 'a.g()' refers to A.g(), since g() is static
        System.out.println(a.g());
    }
}

class A {

    int a;

    A() {
        a = 1;
    }

    int f() {
        return a;
    }

    static int g() {
        return 1;
    }
}

class B extends A {

    int a;

    B() {
        a = 2;
    }

    @Override
    int f() {
        return a;
    }

    static int g() {
        return 2;
    }
}

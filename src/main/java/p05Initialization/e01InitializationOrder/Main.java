package p05Initialization.e01InitializationOrder;

class Main {
    public static void main(String[] args) {
        AHolder aHolder = new AHolder(4444);
        System.out.println(AHolder.staticsToString());
        System.out.println(aHolder);
    }
}

class A {

    int mark;

    A(int mark) {
        this.mark = mark;
        System.out.println("A(" + mark + ")");
    }

    @Override
    public String toString() {
        return "A[" + mark + "]";
    }
}

class AHolder {

    static A a1 = new A(1);
    static {
        System.out.println("Inside static{}");
        a1 = new A(11);
        a2 = new A(22);
        System.out.println("Ending static{}");
    }
    static A a2 = new A(2);
    static {
        System.out.println("Inside static{} x2");
        a1 = new A(111);
        System.out.println("Ending static{} x2");
    }
    static A a3 = new A(3);

    A a4 = new A(4);
    {
        System.out.println("Inside {}");
        a4 = new A(44);
        a5 = new A(55);
        System.out.println("Ending {}");
    }
    A a5 = new A(5);
    {
        System.out.println("Inside {} x2");
        a4 = new A(444);
        System.out.println("Ending {} x2");
    }
    A a6 = new A(6);

    AHolder(int i4) {
        System.out.println("Inside AHolder(int)");
        a4 = new A(i4);
        System.out.println("Ending AHolder(int)");
    }

    static String staticsToString() {
        return "statics: " + a1 + " " + a2 + " " + a3;
    }

    @Override
    public String toString() {
        return "non-statics: " + a4 + " " + a5 + " " + a6;
    }
}

package p07ReusingClasses.e01SubClassInitializationOrder;

class Main {
    public static void main(String[] args) {
        new B(33,66);
    }
}

class A {

    protected int x1 = 1, x2;

    {
        System.out.println("Entering A{} with x1 = " + x1 + ", x2 = " + x2);
        x2 = 2;
        System.out.println("Ending A{} with x1 = " + x1 + ", x2 = " + x2);
    }

    protected int x3 = 3;

    A(int x){
        System.out.println("Entering A(){} with x1 = " + x1 + ", x2 = " + x2 + ", x3 = " + x3);
        x3 = x;
        System.out.println("Ending A(){} with x1 = " + x1 + ", x2 = " + x2 + ", x3 = " + x3);
    }
}

class B extends A {

    protected int x4 = 4, x5;

    {
        System.out.println("Entering B{} with x1 = " + x1 + ", x2 = " + x2 + ", x3 = " + x3 +
                ", x4 = " + x4 + ", x5 = " + x5);
        x5 = 5;
        System.out.println("Ending B{} with x1 = " + x1 + ", x2 = " + x2 + ", x3 = " + x3 +
                ", x4 = " + x4 + ", x5 = " + x5);
    }

    protected int x6 = 6;

    B(int x, int xx){
        super(x);
        System.out.println("Entering B(){} with x1 = " + x1 + ", x2 = " + x2 + ", x3 = " + x3 +
                ", x4 = " + x4 + ", x5 = " + x5 + ", x6 = " + x6);
        x6 = xx;
        System.out.println("Ending B(){} with x1 = " + x1 + ", x2 = " + x2 + ", x3 = " + x3 +
                ", x4 = " + x4 + ", x5 = " + x5 + ", x6 = " + x6);
    }
}
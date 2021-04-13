package p20Annotations.p4Unit;

import p20Annotations.p4Unit.Unit.Test;

public class Class1 {

    private int return42() {
        return 42;
    }

    public String returnHello() {
        return "Hello";
    }

    @Test boolean test1() {
        return return42() == 42;
    }

    @Test boolean test2() {
        return returnHello().equals("Hello");
    }

    @Test boolean test3() {
        return returnHello().length() == 3;
    }

}

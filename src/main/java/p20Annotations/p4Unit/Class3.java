package p20Annotations.p4Unit;

import p20Annotations.p4Unit.Unit.Test;
import p20Annotations.p4Unit.Unit.TestObjectCleanUp;
import p20Annotations.p4Unit.Unit.TestObjectCreate;

public class Class3 {

    private Class3(){}

    private int return0() {
        return 0;
    }

    public String returnJava() {
        return "Java";
    }

    private void additionalClean() {
        System.out.println("Cleaning");
    }

    @TestObjectCreate static Class3 create() {
        return new Class3();
    }

    @TestObjectCleanUp static void clean(Class3 class3) {
        class3.additionalClean();
    }

    @Test boolean test1() {
        return return0() == 0;
    }

    @Test boolean test2() {
        return returnJava().equals("Java");
    }

    @Test boolean test3() {
        return returnJava().length() == 3;
    }

}

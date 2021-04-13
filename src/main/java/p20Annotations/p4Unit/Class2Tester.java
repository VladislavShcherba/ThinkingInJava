package p20Annotations.p4Unit;

import p20Annotations.p4Unit.Unit.Test;

public class Class2Tester extends Class2 {

    @Test void test1() {
        assert return365()==365;
    }

    @Test void test2() {
        assert returnNewYear().equals("New Year!");
    }

    @Test void test3() {
        if( returnNewYear().length() != 3 ) {
            throw new RuntimeException();
        }
    }

}

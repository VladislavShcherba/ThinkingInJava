package p20Annotations.p4Unit;

import p20Annotations.p4Unit.Unit.Handler;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
        List<String> list = new ArrayList<>();
        list.add("p20Annotations.p4Unit.Class1");
        list.add("p20Annotations.p4Unit.Class2Tester");
        list.add("p20Annotations.p4Unit.Class3");

        for(String s : list) {
            new Handler(s).executeTests();
        }
    }
}

package p20Annotations.p1SimpleAnnotation;

public class AnnotationUser {

    public void f1() {
        System.out.println("f1");
    }

    @Simple
    public void f2() {
        System.out.println("f2");
    }

    @Simple(id=30)
    public void f3() {
        System.out.println("f3");
    }

    @Simple(id=40, name = "F40")
    public void f4() {
        System.out.println("f4");
    }

}

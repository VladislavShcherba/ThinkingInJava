package p20Annotations.p1SimpleAnnotation;

import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        Class<AnnotationUser> annotationUserClass = AnnotationUser.class;
        for(Method method : annotationUserClass.getDeclaredMethods()) {
            Simple simple = method.getAnnotation(Simple.class);
            if(simple == null) {
                System.out.println(method.getName() + " has no Simple annotation");
            } else {
                System.out.println(method.getName() + " { " + simple.id() + ", " + simple.name() + " }");
            }
        }
    }
}

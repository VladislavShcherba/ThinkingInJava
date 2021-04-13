package p20Annotations.p2AnnotationDB;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLString {
    // value is a special name, now this annotation can be used as '@SQLString(30)'
    int value() default 0;
    String name() default "";
    Constraint constraint() default @Constraint;
}

package p20Annotations.p1SimpleAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//can be more than one target, list targets using comma
//if no target specified, then the annotation can be used anywhere
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Simple {
    //if default value is not specified, then value must be specified in the place where annotation is used
    int id() default -1;
    // can not be null
    String name() default "";
}

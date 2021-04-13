package p20Annotations.p2AnnotationDB;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class ObjectDBParser {
    public void parse(String className) throws ClassNotFoundException {
        Class<?> c = Class.forName(className);
        TableDB tableDB = c.getAnnotation(TableDB.class);
        if(tableDB == null) {
            System.out.println("There is no TableDB annotation");
            return;
        }
        if(tableDB.name().length() > 0) {
            System.out.println("Table name: " + tableDB.name());
        } else {
            System.out.println("Table name: " + c.getSimpleName());
        }
        for(Field field : c.getDeclaredFields()) {
            for(Annotation annotation : field.getDeclaredAnnotations()) {
                if(annotation instanceof SQLInteger) {
                    SQLInteger sqlInteger = (SQLInteger) annotation;
                    String name = sqlInteger.name();
                    if(name.length() < 1) {
                        name = field.getName();
                    }
                    System.out.println("INT " + name + " " + toString(sqlInteger.constraint()));
                }
                if(annotation instanceof SQLString) {
                    SQLString sqlString = (SQLString) annotation;
                    String name = sqlString.name();
                    if(name.length() < 1) {
                        name = field.getName();
                    }
                    System.out.println("String[" + sqlString.value() + "] " + name + " " + toString(sqlString.constraint()));
                }
            }

        }
    }

    public String toString(Constraint c) {
        String s = "";
        if(c.primaryKey()) {
            s += "primary key";
        }
        if(c.allowNull()) {
            s += ", null is allowed";
        }
        if(c.unique()) {
            s += ", unique";
        }
        return s;
    }
}

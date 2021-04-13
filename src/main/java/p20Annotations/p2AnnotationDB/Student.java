package p20Annotations.p2AnnotationDB;

@TableDB
public class Student {
    @SQLInteger(constraint = @Constraint(primaryKey = true))
    private int id;

    @SQLString(100)
    private String name;

    @SQLInteger(name = "student_age")
    private int age;

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}

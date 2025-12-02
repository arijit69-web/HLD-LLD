package Lec_1_OOPs;

// Teacher class
class Teacher {
    private String name;

    public Teacher(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void teach(Student student) {
        System.out.println(name + " is teaching " + student.getName());
    }
}

// Student class
class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

public class AssociationExample {
    public static void main(String[] args) {
        Teacher teacher = new Teacher("Miss Neha");
        Student student = new Student("Rahul");

        // Association represents a relationship between two or more classes. In this case, each object in one class is associated with one or more objects of another class.

        // Association between teacher and student
        teacher.teach(student); // Output: Mr. Smith is teaching John
    }
}
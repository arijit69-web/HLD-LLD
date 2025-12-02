package Lec_1_OOPs;

import java.util.List;
import java.util.Arrays;

// Professor class
class Professor {
    private String name;

    public Professor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// Department class (Aggregation relationship)
class Department {
    private String name;
    private List<Professor> professors;

    public Department(String name, List<Professor> professors) {
        this.name = name;
        this.professors = professors;
    }

    public void showProfessors() {
        System.out.println("Department: " + name);
        for (Professor prof : professors) {
            System.out.println(prof.getName());
        }
    }
}



public class AggregationExample {
    public static void main(String[] args) {
        Professor prof1 = new Professor("Dr. Jones");
        Professor prof2 = new Professor("Dr. Brown");

        List<Professor> professors = Arrays.asList(prof1, prof2);
        Department department = new Department("Computer Science", professors);
        // Aggregation is a weak "has-a" relationship where one class contains objects of another class. However, the contained objects can exist independently of the container object.
        // Aggregation relationship: department has professors, but professors exist independently
        department.showProfessors();
        // Output:
        // Department: Computer Science
        // Dr. Jones
        // Dr. Brown
    }
}
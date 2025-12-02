package Lec_1_OOPs;

// Superclass: Animal
class Animal {
    public void eat() {
        System.out.println("This animal is eating");
    }
}

// Subclass: Dog (inherits from Animal)
class Dog extends Animal {
    public void bark() {
        System.out.println("The dog is barking");
    }
}

// Main class
public class InheritanceExample {
    public static void main(String[] args) {

        // Inheritance defines an "is-a" relationship where a subclass inherits properties and behaviors (methods) from a superclass.
        Dog dog = new Dog();
        dog.eat(); // Inherited method from Animal class
        dog.bark(); // Method from Dog class
        // Output:
        // This animal is eating
        // The dog is barking
    }
}
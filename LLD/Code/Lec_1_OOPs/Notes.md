# IMPORTANT POINTs:

- Java auto-creates a default constructor ONLY when the class has no constructors at all.

- If you write ANY constructor (even one parameterized), Java will NOT auto-create a default constructor.

- If the parent class does not have a default constructor, Java requires the child to call a parameterized constructor using super(...). Because Java automatically inserts super() in the child constructor. If the parent does not have a no-argument constructor, this will cause a compile-time error.
Concrete means complete — fully implemented, not abstract.

- A concrete class is a regular class with all methods implemented.

- A concrete class can be instantiated (you can create objects of it).

- A concrete method has its full method body (not abstract).

- Concrete classes cannot contain unimplemented (abstract) methods.

- All classes that implement abstract methods or extend abstract classes must become concrete by providing all implementations.

- Static binding → compile-time → no polymorphism.

- Dynamic binding → runtime → polymorphism.
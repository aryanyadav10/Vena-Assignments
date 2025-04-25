package q9;

// Base class
class Animal {
    public void makeSound() {
        System.out.println("Animal makes a sound");
    }
}

// Subclass: Cat
class Cat extends Animal {
    @Override
    public void makeSound() {
        super.makeSound(); // Calls method from Animal class
        System.out.println("Cat says: Meow");
    }
}

// Subclass: Dog
class Dog extends Animal {
    @Override
    public void makeSound() {
        super.makeSound(); // Calls method from Animal class
        System.out.println("Dog says: Woof");
    }
}

// Main class to test
public class AnimalSounds {
    public static void main(String[] args) {
        Cat cat = new Cat();
        Dog dog = new Dog();

        System.out.println("Cat Sound:");
        cat.makeSound();

        System.out.println("\nDog Sound:");
        dog.makeSound();
    }
}

package q4;

// Base class
class Shape {
    public void draw() {
        System.out.println("Drawing a shape");
    }
}

// Subclass - Circle
class Circle extends Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Circle");
    }
}

// Subclass - Square
class Square extends Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Square");
    }
}

// Subclass - Triangle
class Triangle extends Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Triangle");
    }
}

// Main class
public class DrawingApp {
    public static void main(String[] args) {
        // Create an array of Shape
        Shape[] shapes = new Shape[3];

        // Add different shape objects
        shapes[0] = new Circle();
        shapes[1] = new Square();
        shapes[2] = new Triangle();

        // Loop and call draw() method
        System.out.println("Drawing all shapes:");
        for (Shape shape : shapes) {
            shape.draw(); // Polymorphic call
        }
    }
}

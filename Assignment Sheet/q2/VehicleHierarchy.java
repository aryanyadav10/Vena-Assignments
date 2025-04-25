package q2;

// Base class: Vehicle
abstract class Vehicle {
    // Fields for speed and fuelCapacity
    protected double speed;
    protected double fuelCapacity;

    // Constructor to initialize the fields
    public Vehicle(double speed, double fuelCapacity) {
        this.speed = speed;
        this.fuelCapacity = fuelCapacity;
    }

    // Method to display basic vehicle information
    public void displayInfo() {
        System.out.println("Speed: " + speed + " km/h");
        System.out.println("Fuel Capacity: " + fuelCapacity + " liters");
    }

    // Abstract method to get mileage (to be implemented in subclasses)
    public abstract double getMileage();
}

// Subclass: Car
class Car extends Vehicle {
    // Additional fields for Car
    private double engineEfficiency;

    // Constructor to initialize Car-specific fields, using the superclass constructor
    public Car(double speed, double fuelCapacity, double engineEfficiency) {
        super(speed, fuelCapacity);
        this.engineEfficiency = engineEfficiency;
    }

    // Overriding getMileage method for Car
    @Override
    public double getMileage() {
        // Formula for Car mileage: mileage = (fuelCapacity * engineEfficiency) / speed
        return (fuelCapacity * engineEfficiency) / speed;
    }

    // Method to display Car-specific info
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Engine Efficiency: " + engineEfficiency + " km/l");
    }
}

// Subclass: Truck
class Truck extends Vehicle {
    // Additional fields for Truck
    private double loadCapacity;

    // Constructor to initialize Truck-specific fields, using the superclass constructor
    public Truck(double speed, double fuelCapacity, double loadCapacity) {
        super(speed, fuelCapacity);
        this.loadCapacity = loadCapacity;
    }

    // Overriding getMileage method for Truck
    @Override
    public double getMileage() {
        // Formula for Truck mileage: mileage = (fuelCapacity * loadCapacity) / (speed * 2)
        return (fuelCapacity * loadCapacity) / (speed * 2);
    }

    // Method to display Truck-specific info
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Load Capacity: " + loadCapacity + " tons");
    }
}

// Main class to test the program
public class VehicleHierarchy {
    public static void main(String[] args) {
        // Creating objects of Car and Truck
        Vehicle car = new Car(120, 50, 15);  // Speed: 120 km/h, Fuel Capacity: 50 liters, Engine Efficiency: 15 km/l
        Vehicle truck = new Truck(80, 150, 10);  // Speed: 80 km/h, Fuel Capacity: 150 liters, Load Capacity: 10 tons

        // Displaying information and mileage of Car
        System.out.println("Car Details:");
        car.displayInfo();
        System.out.println("Mileage: " + car.getMileage() + " km/l\n");

        // Displaying information and mileage of Truck
        System.out.println("Truck Details:");
        truck.displayInfo();
        System.out.println("Mileage: " + truck.getMileage() + " km/l");
    }
}

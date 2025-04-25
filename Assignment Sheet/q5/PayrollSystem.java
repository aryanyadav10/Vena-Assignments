package q5;

// Abstract class
abstract class Employee {
    String name;
    int id;

    // Constructor
    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    // Abstract methods
    public abstract double calculateSalary();
    public abstract void getDetails();
}

// Full-time employee class
class FullTimeEmployee extends Employee {
    private double monthlySalary;

    public FullTimeEmployee(String name, int id, double monthlySalary) {
        super(name, id);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary; // fixed monthly salary
    }

    @Override
    public void getDetails() {
        System.out.println("Full-Time Employee: " + name + ", ID: " + id);
        System.out.println("Salary: $" + calculateSalary());
    }
}

// Freelancer class
class Freelancer extends Employee {
    private double hourlyRate;
    private int hoursWorked;

    public Freelancer(String name, int id, double hourlyRate, int hoursWorked) {
        super(name, id);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateSalary() {
        return hourlyRate * hoursWorked; // based on hours
    }

    @Override
    public void getDetails() {
        System.out.println("Freelancer: " + name + ", ID: " + id);
        System.out.println("Salary: $" + calculateSalary());
    }
}

// Main class to test
public class PayrollSystem {
    public static void main(String[] args) {
        // Create employee objects
        Employee emp1 = new FullTimeEmployee("Alice", 101, 5000);
        Employee emp2 = new Freelancer("Bob", 102, 50, 80);

        // Call getDetails() for both
        emp1.getDetails();
        System.out.println();
        emp2.getDetails();
    }
}

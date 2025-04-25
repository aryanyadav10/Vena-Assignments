package q6;

public class StudentRegistry {
    // Static variable to track total students
    private static int totalStudents = 0;

    // Instance variables
    private int studentId;
    private String name;

    // Constructor
    public StudentRegistry(String name) {
        totalStudents++; // Increase count for every new student
        this.studentId = totalStudents; // Assign unique ID
        this.name = name;
    }

    // Method to display student details
    public void displayInfo() {
        System.out.println("Student ID: " + studentId + ", Name: " + name);
    }

    // Static method to display total number of students
    public static void displayTotalStudents() {
        System.out.println("Total number of students: " + totalStudents);
    }

    // Main method to test
    public static void main(String[] args) {
        // Create student objects
        StudentRegistry s1 = new StudentRegistry("Alice");
        StudentRegistry s2 = new StudentRegistry("Bob");
        StudentRegistry s3 = new StudentRegistry("Charlie");

        // Display each student's info
        s1.displayInfo();
        s2.displayInfo();
        s3.displayInfo();

        // Display total number of students
        StudentRegistry.displayTotalStudents();
    }
}

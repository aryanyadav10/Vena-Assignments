package main;

import model.Course;
import model.Student;
import service.RegistrationSystem;

public class Main {
    public static void main(String[] args) {
        RegistrationSystem system = new RegistrationSystem();

        system.addStudent(new Student("S1", "Alice", "alice@example.com", "2nd", "CSE"));
        system.addStudent(new Student("S2", "Bob", "bob@example.com", "3rd", "ECE"));
        system.addStudent(new Student("S3", "Charlie", "charlie@example.com", "2nd", "CSE"));

        system.addCourse(new Course("C101", "Data Structures", "Dr. Smith", 2, 4));
        system.addCourse(new Course("C102", "Mathematics", "Dr. Ray", 2, 3));

        system.register("S1", "C101");
        system.register("S2", "C101");
        system.register("S3", "C101"); // goes to waitlist

        System.out.println();

        system.drop("S1", "C101"); // S3 promoted

        System.out.println();

        system.listStudents("C101");
        System.out.println();
        system.listByBranch("CSE");
        System.out.println();
        system.listCoursesSortedByEnrolledCount();
        System.out.println();
        system.filterCoursesByCredits(4);
        System.out.println();
        system.filterCoursesByInstructor("Dr. Ray");
        System.out.println();
        system.groupCoursesByInstructor();
    }
}

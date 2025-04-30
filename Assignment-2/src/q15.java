import java.util.*;

//Problem 15: College Subject Allotment (Map<String, List<String>>)
//•	Store which subjects are allotted to each student
//•	Provide reverse lookup: which students chose a specific subject

// Main class
public class q15 {
    // Map to store subjects chosen by each student
    private Map<String, List<String>> studentSubjects;

    // Constructor
    public q15() {
        studentSubjects = new HashMap<>();
    }

    // Add a subject for a student
    public void allotSubject(String studentName, String subject) {
        List<String> subjects = studentSubjects.get(studentName);

        if (subjects == null) {
            subjects = new ArrayList<>();
            studentSubjects.put(studentName, subjects);
        }

        if (!subjects.contains(subject)) {
            subjects.add(subject);
            System.out.println("Subject " + subject + " allotted to " + studentName);
        } else {
            System.out.println(studentName + " already has " + subject);
        }
    }

    // Print all subjects allotted to each student
    public void printStudentAllotments() {
        System.out.println("\nSubject allotments per student:");
        for (String student : studentSubjects.keySet()) {
            System.out.println(student + " -> " + studentSubjects.get(student));
        }
    }

    // Reverse lookup: Get students who chose a specific subject
    public void findStudentsBySubject(String subject) {
        System.out.println("\nStudents who chose subject: " + subject);
        boolean found = false;

        for (String student : studentSubjects.keySet()) {
            List<String> subjects = studentSubjects.get(student);
            if (subjects.contains(subject)) {
                System.out.println("- " + student);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No student has chosen this subject.");
        }
    }

    // Main method to test the system
    public static void main(String[] args) {
        q15 college = new q15();

        // Allot subjects
        college.allotSubject("Alice", "Math");
        college.allotSubject("Alice", "Physics");
        college.allotSubject("Bob", "Math");
        college.allotSubject("Bob", "Chemistry");
        college.allotSubject("Charlie", "Biology");
        college.allotSubject("Charlie", "Math");

        // Print allotments
        college.printStudentAllotments();

        // Reverse lookup: who chose Math
        college.findStudentsBySubject("Math");

        // Reverse lookup: who chose History
        college.findStudentsBySubject("History");
    }
}

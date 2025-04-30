import java.util.*;

//Problem 1: Advanced Student Management (List, Comparator, Map)
//
//Create a Student class with id, name, age, grade, and branch.
//        •	Store at least 15 students in an ArrayList.
//        •	Sort students by branch, then by descending grade.
//•	Group students by branch using a Map<String, List<Student>>.

class Student {
    private int id;
    private String name;
    private int age;
    private String branch;
    private String grade;

    Student(int id, String name, int age, String branch, String grade) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.branch = branch;
        this.grade = grade;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getBranch() { return branch; }
    public String getGrade() { return grade; }

    // Print format
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', age=" + age +
                ", branch='" + branch + "', grade='" + grade + "'}";
    }
}

public class q1 {
    public static void main(String[] args) {
        // Create list of students
        List<Student> students = new ArrayList<>();

        // Adding 15 students
        students.add(new Student(1, "Aarav Sharma", 20, "Computer Science", "A"));
        students.add(new Student(2, "Meera Verma", 21, "Electronics", "B"));
        students.add(new Student(3, "Kunal Joshi", 19, "Mechanical", "A"));
        students.add(new Student(4, "Tanya Kapoor", 22, "Civil", "B"));
        students.add(new Student(5, "Ishaan Roy", 20, "Computer Science", "A"));
        students.add(new Student(6, "Priya Mehta", 23, "Electrical", "B"));
        students.add(new Student(7, "Raghav Singh", 21, "Information Tech", "A"));
        students.add(new Student(8, "Sneha Das", 20, "Computer Science", "B"));
        students.add(new Student(9, "Aditya Khanna", 22, "Electronics", "B"));
        students.add(new Student(10, "Neha Batra", 19, "Mechanical", "A"));
        students.add(new Student(11, "Vikram Nair", 21, "Civil", "B"));
        students.add(new Student(12, "Simran Kaur", 20, "Information Tech", "A"));
        students.add(new Student(13, "Arjun Patel", 22, "Electrical", "A"));
        students.add(new Student(14, "Ritika Sharma", 23, "Computer Science", "A"));
        students.add(new Student(15, "Dev Malhotra", 21, "Electronics", "B"));

        // 🔹 Sort students by branch (ascending), then by grade (descending)
        students.sort((s1, s2) -> {
            int branchCompare = s1.getBranch().compareTo(s2.getBranch());
            if (branchCompare != 0) {
                return branchCompare;  // if branches are different
            }
            // If branch is same, compare grade in descending order (A > B > C)
            return s2.getGrade().compareTo(s1.getGrade());
        });

        // 🔹 Group students by branch
        Map<String, List<Student>> groupedByBranch = new TreeMap<>(); // TreeMap gives sorted branch names

        for (Student student : students) {
            groupedByBranch
                    .computeIfAbsent(student.getBranch(), k -> new ArrayList<>())
                    .add(student);
        }

        // 🔹 Print grouped students
        for (String branch : groupedByBranch.keySet()) {
            System.out.println("\nBranch: " + branch);
            for (Student s : groupedByBranch.get(branch)) {
                System.out.println("  " + s);
            }
        }
    }
}

import java.util.*;
import java.util.stream.Collectors;

class Student {
    private int id;
    private String name;
    private int age;
    private String branch;
    private String grade;

    Student(int id, String name, int age, String branch, String grade){
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

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', age=" + age + ", branch='" + branch + "', grade='" + grade + "'}";
    }
}

public class q1 {
    public static void main(String[] args){
        ArrayList<Student> students = new ArrayList<>();

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

        // Sort by branch, then by grade descending
        students.sort(Comparator.comparing(Student::getBranch)
                .thenComparing(Comparator.comparing(Student::getGrade).reversed()));

        // Group by branch
        Map<String, List<Student>> groupedByBranch = students.stream()
                .collect(Collectors.groupingBy(Student::getBranch));

        // Output the grouped students
        for (Map.Entry<String, List<Student>> entry : groupedByBranch.entrySet()) {
            System.out.println("Branch: " + entry.getKey());
            for (Student s : entry.getValue()) {
                System.out.println("  " + s);
            }
        }
    }
}

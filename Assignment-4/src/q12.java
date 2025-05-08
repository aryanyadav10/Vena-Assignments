// File: q12.java

import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.*;

//Data Processing & Transformations
//Given a list of transactions (with amount and type), calculate the total amount for type "DEBIT" using streams.
//From a list of students with names and grades, return a list of names of students who scored above 80, sorted alphabetically.
//Create a stream pipeline to flatten a list of lists of integers and return the distinct even numbers.
//Given a list of Employee objects, group them by department and count how many employees are in each department.
//Calculate the average salary of employees in each department using Collectors.groupingBy() and averagingDouble().

class Transaction {
    double amount;
    String type; // "DEBIT" or "CREDIT"

    Transaction(double amount, String type) {
        this.amount = amount;
        this.type = type;
    }
}

class Student {
    String name;
    int grade;

    Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }
}

class Employee {
    String name;
    String department;
    double salary;

    Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }
}

public class q12 {
    public static void main(String[] args) {

        // 1. Total amount for DEBIT transactions
        List<Transaction> transactions = Arrays.asList(
                new Transaction(100.0, "DEBIT"),
                new Transaction(200.0, "CREDIT"),
                new Transaction(150.0, "DEBIT")
        );

        double totalDebit = transactions.stream()
                .filter(t -> t.type.equals("DEBIT"))
                .mapToDouble(t -> t.amount)
                .sum();

        System.out.println("Total DEBIT amount: " + totalDebit);

        // 2. Students scoring above 80, sorted alphabetically
        List<Student> students = Arrays.asList(
                new Student("John", 75),
                new Student("Alice", 90),
                new Student("Bob", 85),
                new Student("Zara", 60)
        );

        List<String> topStudents = students.stream()
                .filter(s -> s.grade > 80)
                .map(s -> s.name)
                .sorted()
                .collect(toList());

        System.out.println("Students scoring above 80: " + topStudents);

        // 3. Flatten list of lists and return distinct even numbers
        List<List<Integer>> nestedList = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5, 6),
                Arrays.asList(2, 8, 10)
        );

        List<Integer> evenDistinct = nestedList.stream()
                .flatMap(List::stream)
                .filter(n -> n % 2 == 0)
                .distinct()
                .collect(toList());

        System.out.println("Distinct even numbers: " + evenDistinct);

        // 4. Group employees by department and count
        List<Employee> employees = Arrays.asList(
                new Employee("Alex", "HR", 50000),
                new Employee("Brian", "IT", 60000),
                new Employee("Carol", "HR", 55000),
                new Employee("David", "IT", 65000),
                new Employee("Eva", "Sales", 70000)
        );

        Map<String, Long> empCountByDept = employees.stream()
                .collect(groupingBy(e -> e.department, counting()));

        System.out.println("Employee count by department: " + empCountByDept);

        // 5. Average salary by department
        Map<String, Double> avgSalaryByDept = employees.stream()
                .collect(groupingBy(e -> e.department, averagingDouble(e -> e.salary)));

        System.out.println("Average salary by department: " + avgSalaryByDept);
    }
}

import java.util.*;

//Problem 3: Department Directory with Sorted Employees (Map, List)
//Maintain a Map<String, List<Employee>> where the key is department name.
//•	Sort each department’s employees by salary descending.
//•	Provide a method to return top N paid employees across all departments.

import java.util.*;

// Employee class with fields and constructor
class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    // Print format
    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', department='" + department + "', salary=" + salary + "}";
    }
}

public class q3 {
    // Map to store employees department-wise
    private Map<String, List<Employee>> departmentMap = new HashMap<>();

    // Add employee to map
    public void addEmployee(Employee emp) {
        if (!departmentMap.containsKey(emp.getDepartment())) {
            departmentMap.put(emp.getDepartment(), new ArrayList<>());
        }
        departmentMap.get(emp.getDepartment()).add(emp);
    }

    // Sort employees in each department by salary descending
    public void sortEmployeesBySalaryInEachDepartment() {
        for (List<Employee> employeeList : departmentMap.values()) {
            employeeList.sort((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()));
        }
    }

    // Return top N paid employees across all departments
    public List<Employee> getTopPaidEmployees(int n) {
        List<Employee> allEmployees = new ArrayList<>();
        for (List<Employee> empList : departmentMap.values()) {
            allEmployees.addAll(empList); // Combine all department employees
        }

        // Sort all employees by salary descending
        allEmployees.sort((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()));

        // Return top N employees or all if less than N
        return allEmployees.subList(0, Math.min(n, allEmployees.size()));
    }

    // Display employees department-wise
    public void printDepartmentWiseEmployees() {
        for (String dept : departmentMap.keySet()) {
            System.out.println("\nDepartment: " + dept);
            for (Employee emp : departmentMap.get(dept)) {
                System.out.println("  " + emp);
            }
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        q3 directory = new q3();

        // Adding some employees
        directory.addEmployee(new Employee(1, "Alice", "HR", 50000));
        directory.addEmployee(new Employee(2, "Bob", "IT", 80000));
        directory.addEmployee(new Employee(3, "Charlie", "HR", 60000));
        directory.addEmployee(new Employee(4, "David", "Finance", 75000));
        directory.addEmployee(new Employee(5, "Eva", "IT", 95000));
        directory.addEmployee(new Employee(6, "Frank", "Finance", 55000));
        directory.addEmployee(new Employee(7, "Grace", "HR", 67000));

        // Sort employees in each department
        directory.sortEmployeesBySalaryInEachDepartment();

        // Print department-wise employees
        directory.printDepartmentWiseEmployees();

        // Print top 3 highest paid employees
        System.out.println("\nTop 3 Paid Employees:");
        List<Employee> topPaid = directory.getTopPaidEmployees(3);
        for (Employee emp : topPaid) {
            System.out.println(emp);
        }
    }
}


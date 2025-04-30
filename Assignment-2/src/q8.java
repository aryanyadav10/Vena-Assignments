import java.util.*;

//Problem 8: Employee Hierarchy System (Map, TreeMap)
//Maintain a hierarchy with Map<Manager, TreeMap<Level, List<Employee>>>.
//•	Print employees reporting to a manager at each level.


class MyEmployee {
    private String name;

    public MyEmployee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // This makes printing the object easier
    @Override
    public String toString() {
        return name;
    }
}

public class q8 {
    // Map from manager name -> (level -> list of MyEmployee)
    private Map<String, TreeMap<Integer, List<MyEmployee>>> managerMap;

    public q8() {
        managerMap = new HashMap<>();
    }

    // Method to add employee under a manager at a specific level
    public void addEmployee(String managerName, int level, MyEmployee employee) {
        // Get the level map for the manager. If not exists, create it.
        TreeMap<Integer, List<MyEmployee>> levelMap = managerMap.get(managerName);

        if (levelMap == null) {
            levelMap = new TreeMap<>(); // keeps levels in order
            managerMap.put(managerName, levelMap);
        }

        // Get list of employees at that level. If not exists, create it.
        List<MyEmployee> empList = levelMap.get(level);

        if (empList == null) {
            empList = new ArrayList<>();
            levelMap.put(level, empList);
        }

        // Add the employee
        empList.add(employee);
    }

    // Method to print the full hierarchy
    public void printHierarchy() {
        for (String managerName : managerMap.keySet()) {
            System.out.println("Manager: " + managerName);

            TreeMap<Integer, List<MyEmployee>> levelMap = managerMap.get(managerName);

            for (Integer level : levelMap.keySet()) {
                System.out.println("  Level " + level + ":");

                List<MyEmployee> empList = levelMap.get(level);

                for (MyEmployee emp : empList) {
                    System.out.println("    - " + emp.getName());
                }
            }
        }
    }

    // Main method to test
    public static void main(String[] args) {
        q8 system = new q8();

        system.addEmployee("Alice", 1, new MyEmployee("Bob"));
        system.addEmployee("Alice", 2, new MyEmployee("Charlie"));
        system.addEmployee("Alice", 2, new MyEmployee("David"));
        system.addEmployee("Alice", 3, new MyEmployee("Eve"));

        system.addEmployee("John", 1, new MyEmployee("Kevin"));
        system.addEmployee("John", 2, new MyEmployee("Lucy"));

        system.printHierarchy();
    }
}

import java.util.*;
import java.util.stream.*;

//Level 5 – Custom Functional Interfaces and Real-Life Use Cases
//Create your own functional interface called TriFunction<T, U, V, R> (takes 3 args).
//Implement a function pipeline to process orders (e.g., filter by status, sort by amount, map to customer name).

// 1. Custom functional interface
@FunctionalInterface
interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}

// Order class for real-life use case
class Order {
    String customerName;
    String status;
    double amount;

    Order(String customerName, String status, double amount) {
        this.customerName = customerName;
        this.status = status;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return customerName + " | " + status + " | $" + amount;
    }
}

public class q11 {

    public static void main(String[] args) {

        // --- Using TriFunction ---
        TriFunction<Integer, Integer, Integer, Integer> sumThree = (a, b, c) -> a + b + c;
        int result = sumThree.apply(5, 10, 15);
        System.out.println("Sum of 3 numbers using TriFunction: " + result);

        // --- Order processing pipeline ---
        List<Order> orders = Arrays.asList(
                new Order("Alice", "DELIVERED", 250.0),
                new Order("Bob", "PENDING", 100.0),
                new Order("Charlie", "DELIVERED", 500.0),
                new Order("David", "CANCELLED", 300.0),
                new Order("Eve", "DELIVERED", 150.0)
        );

        // Pipeline: filter DELIVERED -> sort by amount -> map to customer names
        List<String> deliveredCustomers = orders.stream()
                .filter(order -> order.status.equals("DELIVERED"))
                .sorted(Comparator.comparingDouble(order -> order.amount))
                .map(order -> order.customerName)
                .collect(Collectors.toList());

        System.out.println("Delivered order customers (sorted by amount): " + deliveredCustomers);
    }
}

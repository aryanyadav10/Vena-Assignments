import java.util.*;

//Problem 14: Restaurant Order System (Deque, List)
//Use a Deque<Order> for managing current orders.
//•	Allow adding/removing orders from both ends.
//•	Store history in a List<Order>.

// Order class to store order details
class Order {
    private int orderId;
    private String itemName;

    public Order(int orderId, String itemName) {
        this.orderId = orderId;
        this.itemName = itemName;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getItemName() {
        return itemName;
    }

    @Override
    public String toString() {
        return "Order #" + orderId + " - " + itemName;
    }
}

// Main class
public class q14 {
    private Deque<Order> currentOrders;       // Orders currently being processed
    private List<Order> orderHistory;         // Completed orders history

    public q14() {
        currentOrders = new ArrayDeque<>();
        orderHistory = new ArrayList<>();
    }

    // Add order at front
    public void addOrderFront(Order order) {
        currentOrders.addFirst(order);
        System.out.println("Added to front: " + order);
    }

    // Add order at end
    public void addOrderEnd(Order order) {
        currentOrders.addLast(order);
        System.out.println("Added to end: " + order);
    }

    // Process order from front
    public void processOrderFromFront() {
        if (!currentOrders.isEmpty()) {
            Order order = currentOrders.removeFirst();
            orderHistory.add(order);
            System.out.println("Processed (front): " + order);
        } else {
            System.out.println("No orders to process at front.");
        }
    }

    // Process order from end
    public void processOrderFromEnd() {
        if (!currentOrders.isEmpty()) {
            Order order = currentOrders.removeLast();
            orderHistory.add(order);
            System.out.println("Processed (end): " + order);
        } else {
            System.out.println("No orders to process at end.");
        }
    }

    // Print current orders
    public void printCurrentOrders() {
        System.out.println("\nCurrent Orders:");
        for (Order order : currentOrders) {
            System.out.println(order);
        }
    }

    // Print order history
    public void printOrderHistory() {
        System.out.println("\nOrder History:");
        for (Order order : orderHistory) {
            System.out.println(order);
        }
    }

    // Main method to test
    public static void main(String[] args) {
        q14 restaurant = new q14();

        restaurant.addOrderEnd(new Order(101, "Pizza"));
        restaurant.addOrderFront(new Order(102, "Burger"));
        restaurant.addOrderEnd(new Order(103, "Pasta"));
        restaurant.addOrderFront(new Order(104, "Sandwich"));

        restaurant.printCurrentOrders();

        restaurant.processOrderFromFront();
        restaurant.processOrderFromEnd();

        restaurant.printCurrentOrders();
        restaurant.printOrderHistory();
    }
}

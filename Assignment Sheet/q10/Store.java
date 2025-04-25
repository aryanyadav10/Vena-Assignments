package q10;

// Product class
class Product {
    int id;
    String name;
    double price;

    // Constructor
    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Display product details
    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Price: $" + price);
    }
}

// Store class
public class Store {
    Product[] products;

    // Constructor to initialize products
    public Store() {
        products = new Product[5];
        products[0] = new Product(1, "Laptop", 800.00);
        products[1] = new Product(2, "Phone", 500.00);
        products[2] = new Product(3, "Monitor", 300.00);
        products[3] = new Product(4, "Keyboard", 40.00);
        products[4] = new Product(5, "Mouse", 25.00);
    }

    // Display all products
    public void displayAllProducts() {
        System.out.println("All Products:");
        for (Product p : products) {
            p.display();
        }
    }

    // Find product with highest price
    public void findMostExpensiveProduct() {
        Product expensive = products[0];
        for (Product p : products) {
            if (p.price > expensive.price) {
                expensive = p;
            }
        }
        System.out.println("\nMost Expensive Product:");
        expensive.display();
    }

    // Search product by name
    public void searchProductByName(String name) {
        boolean found = false;
        System.out.println("\nSearch Results for \"" + name + "\":");
        for (Product p : products) {
            if (p.name.equalsIgnoreCase(name)) {
                p.display();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No product found with that name.");
        }
    }

    // Main method
    public static void main(String[] args) {
        Store store = new Store();

        store.displayAllProducts();
        store.findMostExpensiveProduct();
        store.searchProductByName("Laptop");  // Try changing this name to test search
    }
}

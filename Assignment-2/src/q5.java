import java.util.*;

//Problem 5: Product Catalog System (TreeMap, Comparator)
//Create a Product class with id, name, price, rating, and stock.
//        •	Store products in a TreeMap<Integer, Product> keyed by id.
//        •	Sort and print products by descending rating, and then by name.

class Product {
    private int id;
    private String name;
    private double price;
    private double rating;  // e.g., 4.5
    private int stock;

    public Product(int id, String name, double price, double rating, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.stock = stock;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public double getRating() { return rating; }
    public int getStock() { return stock; }

    @Override
    public String toString() {
        return "Product{id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", stock=" + stock + '}';
    }
}

public class q5 {
    public static void main(String[] args) {
        // TreeMap stores products sorted by id
        TreeMap<Integer, Product> productMap = new TreeMap<>();

        // Adding some products
        productMap.put(101, new Product(101, "Laptop", 65000.0, 4.5, 20));
        productMap.put(102, new Product(102, "Smartphone", 25000.0, 4.7, 50));
        productMap.put(103, new Product(103, "Headphones", 1500.0, 4.2, 100));
        productMap.put(104, new Product(104, "Keyboard", 800.0, 4.2, 75));
        productMap.put(105, new Product(105, "Monitor", 12000.0, 4.7, 30));

        // Convert map values to list for custom sorting
        List<Product> productList = new ArrayList<>(productMap.values());

        // Sort by descending rating, then by name
        productList.sort((p1, p2) -> {
            if (Double.compare(p2.getRating(), p1.getRating()) != 0) {
                return Double.compare(p2.getRating(), p1.getRating());  // Descending rating
            } else {
                return p1.getName().compareTo(p2.getName());  // Alphabetical name
            }
        });

        // Print sorted products
        System.out.println("Products sorted by rating (desc), then name:");
        for (Product product : productList) {
            System.out.println(product);
        }
    }
}

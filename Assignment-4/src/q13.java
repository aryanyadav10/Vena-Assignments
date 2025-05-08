import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.*;

//Custom Types & Nested Streams
//You have a list of Order objects, each with a list of Items. Return a flat list of all item names.
//Find the order with the highest total item price.
//Given a list of products with category and rating, return a map of category → highest-rated product name.
//Create a function that takes a list of people and returns a map of age group (<20, 20-40, 40+) → list of people.
//Convert a CSV string of numbers ("1,2,3,4") into a List<Integer> using streams and lambdas.

public class q13 {

    // Make all model classes static so they can be accessed in static main
    static class Item {
        String name;
        double price;

        Item(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }

    static class Order {
        int id;
        List<Item> items;

        Order(int id, List<Item> items) {
            this.id = id;
            this.items = items;
        }
    }

    static class Product {
        String name;
        String category;
        double rating;

        Product(String name, String category, double rating) {
            this.name = name;
            this.category = category;
            this.rating = rating;
        }
    }

    static class Person {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    public static void main(String[] args) {

        // 1. Flatten item names from orders
        List<Order> orders = Arrays.asList(
                new Order(1, Arrays.asList(new Item("Pen", 10), new Item("Book", 30))),
                new Order(2, Arrays.asList(new Item("Pencil", 5), new Item("Notebook", 40)))
        );

        List<String> allItemNames = orders.stream()
                .flatMap(order -> order.items.stream())
                .map(item -> item.name)
                .collect(toList());

        System.out.println("All item names: " + allItemNames);

        // 2. Find order with highest total item price
        Optional<Order> highestOrder = orders.stream()
                .max(Comparator.comparingDouble(order -> order.items.stream().mapToDouble(i -> i.price).sum()));

        System.out.println("Order with highest total: Order ID = " +
                highestOrder.map(o -> o.id).orElse(-1));

        // 3. Map category → highest-rated product name
        List<Product> products = Arrays.asList(
                new Product("iPhone", "Electronics", 4.5),
                new Product("Samsung", "Electronics", 4.2),
                new Product("Shirt", "Clothing", 4.8),
                new Product("Jeans", "Clothing", 4.1)
        );

        Map<String, String> categoryTopProduct = products.stream()
                .collect(groupingBy(p -> p.category,
                        collectingAndThen(
                                maxBy(Comparator.comparingDouble(p -> p.rating)),
                                opt -> opt.map(p -> p.name).orElse("None")
                        )
                ));

        System.out.println("Category → Top Product: " + categoryTopProduct);

        // 4. Group people by age range
        List<Person> people = Arrays.asList(
                new Person("A", 15),
                new Person("B", 25),
                new Person("C", 35),
                new Person("D", 45),
                new Person("E", 60)
        );

        Map<String, List<Person>> ageGroups = people.stream()
                .collect(groupingBy(p -> {
                    if (p.age < 20) return "<20";
                    else if (p.age <= 40) return "20-40";
                    else return "40+";
                }));

        System.out.println("Age groups: ");
        ageGroups.forEach((group, persons) -> {
            System.out.print(group + ": ");
            persons.forEach(p -> System.out.print(p.name + " "));
            System.out.println();
        });

        // 5. Convert CSV string to List<Integer>
        String csv = "1,2,3,4,5";

        List<Integer> numbers = Arrays.stream(csv.split(","))
                .map(Integer::parseInt)
                .collect(toList());

        System.out.println("CSV to List<Integer>: " + numbers);
    }
}

import java.util.*;
import java.util.stream.*;
import java.time.LocalDate;
import java.util.function.Predicate;

//Advanced Optional & Stream API
//Safely get the third element of a list using stream().skip(2).findFirst() and return Optional<T>.
//Given a list of URLs (strings), use streams to validate them using a Predicate<String> and return only valid ones.
//Chain operations on a list of Orders: filter by date range, map to total amount, sum all totals.

public class q16 {
    // 1. Safely get the third element of a list using stream().skip(2).findFirst() and return Optional<T>.
    public static <T> Optional<T> getThirdElement(List<T> list) {
        return list.stream()
                .skip(2)  // Skip the first two elements
                .findFirst();  // Get the next element (third element in the original list)
    }

    // 2. Validate URLs using a Predicate and return only valid ones.
    public static List<String> validateUrls(List<String> urls, Predicate<String> isValidUrl) {
        return urls.stream()
                .filter(isValidUrl)  // Filter valid URLs
                .collect(Collectors.toList());
    }

    // 3. Chain operations on a list of orders: filter by date range, map to total amount, and sum all totals.
    static class Order {
        LocalDate date;
        double totalAmount;

        Order(LocalDate date, double totalAmount) {
            this.date = date;
            this.totalAmount = totalAmount;
        }

        public LocalDate getDate() {
            return date;
        }

        public double getTotalAmount() {
            return totalAmount;
        }
    }

    public static double getTotalAmountForOrdersInDateRange(List<Order> orders, LocalDate startDate, LocalDate endDate) {
        return orders.stream()
                .filter(order -> !order.getDate().isBefore(startDate) && !order.getDate().isAfter(endDate))  // Filter by date range
                .mapToDouble(Order::getTotalAmount)  // Map to total amount
                .sum();  // Sum all totals
    }

    public static void main(String[] args) {
        // 1. Get the third element of a list
        List<String> items = Arrays.asList("apple", "banana", "cherry", "date");
        Optional<String> thirdElement = getThirdElement(items);
        thirdElement.ifPresentOrElse(
                item -> System.out.println("Third Element: " + item),
                () -> System.out.println("No third element found")
        );

        // 2. Validate URLs
        List<String> urls = Arrays.asList("http://example.com", "https://valid-url.com", "ftp://invalid-url.com", "invalid-url");
        Predicate<String> isValidUrl = url -> url.startsWith("http") || url.startsWith("https");
        List<String> validUrls = validateUrls(urls, isValidUrl);
        System.out.println("Valid URLs: " + validUrls);

        // 3. Sum total amounts of orders in a given date range
        List<Order> orders = Arrays.asList(
                new Order(LocalDate.of(2023, 1, 1), 100.0),
                new Order(LocalDate.of(2023, 2, 1), 150.0),
                new Order(LocalDate.of(2023, 3, 1), 200.0)
        );
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 2, 28);
        double totalAmount = getTotalAmountForOrdersInDateRange(orders, startDate, endDate);
        System.out.println("Total Amount for Orders in Date Range: " + totalAmount);
    }
}

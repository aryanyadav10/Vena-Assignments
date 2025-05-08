import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.*;

// Collectors & Summarization
// Given a list of books, generate a summary object with total count, average rating, and total price.
// Write a program that groups a list of employees by joining year, and then finds the max salary in each year.
// From a list of comments, count the number of comments by each user.

public class q15 {

    // 1. Generate a summary object with total count, average rating, and total price for a list of books.
    static class Book {
        String title;
        double price;
        double rating;

        Book(String title, double price, double rating) {
            this.title = title;
            this.price = price;
            this.rating = rating;
        }

        // Getter methods
        public double getPrice() {
            return price;
        }

        public double getRating() {
            return rating;
        }
    }

    static class BookSummary {
        long count;
        double averageRating;
        double totalPrice;

        BookSummary(long count, double averageRating, double totalPrice) {
            this.count = count;
            this.averageRating = averageRating;
            this.totalPrice = totalPrice;
        }

        @Override
        public String toString() {
            return "Total Count: " + count + ", Average Rating: " + averageRating + ", Total Price: " + totalPrice;
        }
    }

    // 2. Group employees by joining year, and find the max salary in each year.
    static class Employee {
        String name;
        int joiningYear;
        double salary;

        Employee(String name, int joiningYear, double salary) {
            this.name = name;
            this.joiningYear = joiningYear;
            this.salary = salary;
        }

        // Getter methods
        public int getJoiningYear() {
            return joiningYear;
        }

        public double getSalary() {
            return salary;
        }
    }

    // 3. Count the number of comments by each user.
    static class Comment {
        String username;
        String content;

        Comment(String username, String content) {
            this.username = username;
            this.content = content;
        }

        // Getter method
        public String getUsername() {
            return username;
        }
    }

    public static void main(String[] args) {
        // 1. Generate a summary object for a list of books
        List<Book> books = Arrays.asList(
                new Book("Java Programming", 30.0, 4.5),
                new Book("Data Structures", 25.0, 4.7),
                new Book("Algorithms", 40.0, 4.8)
        );

        BookSummary bookSummary = books.stream()
                .collect(collectingAndThen(
                        summarizingDouble(Book::getPrice),
                        summary -> new BookSummary(
                                books.size(),
                                books.stream().mapToDouble(Book::getRating).average().orElse(0),
                                summary.getSum()
                        )
                ));

        System.out.println("Book Summary: " + bookSummary);

        // 2. Group employees by joining year, and find the max salary in each year.
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", 2018, 60000),
                new Employee("Bob", 2019, 70000),
                new Employee("Charlie", 2018, 80000),
                new Employee("David", 2019, 75000),
                new Employee("Eve", 2020, 90000)
        );

        Map<Integer, Employee> highestSalaryByYear = employees.stream()
                .collect(groupingBy(
                        Employee::getJoiningYear,
                        collectingAndThen(
                                maxBy(Comparator.comparingDouble(Employee::getSalary)),
                                Optional::get
                        )
                ));

        highestSalaryByYear.forEach((year, employee) -> {
            System.out.println("Year " + year + ": " + employee.name + " with salary " + employee.salary);
        });

        // 3. Count the number of comments by each user.
        List<Comment> comments = Arrays.asList(
                new Comment("user1", "Great product!"),
                new Comment("user2", "Good service."),
                new Comment("user1", "Fast delivery."),
                new Comment("user3", "Excellent quality."),
                new Comment("user2", "Very satisfied.")
        );

        Map<String, Long> commentsByUser = comments.stream()
                .collect(groupingBy(Comment::getUsername, counting()));

        System.out.println("Comments by user: " + commentsByUser);
    }
}

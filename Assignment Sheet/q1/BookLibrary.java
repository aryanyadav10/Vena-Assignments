package q1;
import java.util.*;

public class BookLibrary {

    // Book Class
    public static class Book {
        // Fields for title, author, and ISBN
        private String title;
        private String author;
        private String isbn;

        // Constructor to initialize the fields
        public Book(String title, String author, String isbn) {
            this.title = title;
            this.author = author;
            this.isbn = isbn;
        }

        // Method to print details of the book
        public void printDetails() {
            System.out.println("Book Title: " + this.title);
            System.out.println("Author: " + this.author);
            System.out.println("ISBN: " + this.isbn);
        }

        // Getter for title (to use in Library for searching)
        public String getTitle() {
            return title;
        }
    }

    // Library Class
    public static class Library {
        // A list to store the books in the library
        private ArrayList<Book> books;

        // Constructor to initialize the library with an empty list of books
        public Library() {
            books = new ArrayList<>();
        }

        // Method to add a book to the library
        public void addBook(Book book) {
            books.add(book);
        }

        // Method to search for a book by title
        public void searchBookByTitle(String title) {
            boolean found = false;
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(title)) {
                    book.printDetails();  // Print details if the book is found
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Book not found!");
            }
        }
    }

    // Main Class to test the program
    public static void main(String[] args) {
        // Create an instance of Library
        Library library = new Library();

        // Create some books and add them to the library
        Book book1 = new Book("Java Programming", "John Doe", "1234567890");
        Book book2 = new Book("Advanced Java", "Jane Smith", "0987654321");

        library.addBook(book1);
        library.addBook(book2);

        // Search for a book by title
        System.out.println("Searching for 'Java Programming':");
        library.searchBookByTitle("Java Programming");

        System.out.println("\nSearching for 'Python Basics':");
        library.searchBookByTitle("Python Basics");
    }
}

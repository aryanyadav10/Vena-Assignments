import java.util.*;

//Problem 6: Library Catalog (Map<String, Set<Book>>)
//Each genre maps to a set of Book objects.
//•	Prevent duplicate books using equals and hashCode.
//•	Print all books in a genre, sorted by publication year.

class Book {
    private String title;
    private String author;
    private int publicationYear;

    // Constructor
    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.publicationYear = year;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPublicationYear() { return publicationYear; }

    // equals() and hashCode() to prevent duplicate books in a Set
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;
        return title.equals(book.title) &&
                author.equals(book.author) &&
                publicationYear == book.publicationYear;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, publicationYear);
    }

    @Override
    public String toString() {
        return title + " by " + author + " (" + publicationYear + ")";
    }
}

public class q6 {
    private Map<String, Set<Book>> genreMap;

    public q6() {
        genreMap = new HashMap<>();
    }

    // Add a book to a specific genre
    public void addBook(String genre, Book book) {
        genreMap
                .computeIfAbsent(genre, k -> new HashSet<>())  // Create new set if genre doesn't exist
                .add(book);  // HashSet ensures no duplicates
    }

    // Print books in a genre, sorted by publication year
    public void printBooksByGenre(String genre) {
        Set<Book> books = genreMap.get(genre);

        if (books == null || books.isEmpty()) {
            System.out.println("No books found for genre: " + genre);
            return;
        }

        // Convert Set to List for sorting
        List<Book> sortedBooks = new ArrayList<>(books);

        // Sort using a Comparator (ascending by year)
        sortedBooks.sort(new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                return Integer.compare(b1.getPublicationYear(), b2.getPublicationYear());
            }
        });

        System.out.println("Books in genre: " + genre);
        for (Book book : sortedBooks) {
            System.out.println(book);
        }
    }

    public static void main(String[] args) {
        q6 catalog = new q6();

        catalog.addBook("Fiction", new Book("The Alchemist", "Paulo Coelho", 1988));
        catalog.addBook("Fiction", new Book("1984", "George Orwell", 1949));
        catalog.addBook("Fiction", new Book("The Alchemist", "Paulo Coelho", 1988)); // Duplicate

        catalog.addBook("Science", new Book("A Brief History of Time", "Stephen Hawking", 1988));
        catalog.addBook("Science", new Book("The Selfish Gene", "Richard Dawkins", 1976));

        catalog.printBooksByGenre("Fiction");
        catalog.printBooksByGenre("Science");
    }
}

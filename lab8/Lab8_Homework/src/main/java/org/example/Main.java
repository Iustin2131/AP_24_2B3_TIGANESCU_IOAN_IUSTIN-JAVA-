package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = Database.getConnection(); // Obtain the connection explicitly
            conn.setAutoCommit(false); // Managing transactions manually

            BooksInfoDAO booksInfo = new BooksInfoDAO();

            // Creating books
            booksInfo.create(new Book(1, "Cartea ballast", "Tiganescu Iustin", "Romana", "2002.12.25"), conn);
            booksInfo.create(new Book(2, "Harry Potter", "J.K. Rowling", "Romana", "1997.06.26"), conn);
            booksInfo.create(new Book(3, "Moara cu noroc", "Ioan Slavici", "Romana", "197"), conn);


            // Reading books from a CSV file and adding them to the database
            Path path = Paths.get("C:\\Users\\IUSTIN\\Desktop\\JAVA\\Lab8_Homework\\books.csv");
            processBooksFromFile(path, conn, booksInfo);

            // Display all books from the database
            List<Book> books = booksInfo.findAll(conn);
            for (Book book : books) {
                System.out.println("ID: " + book.getId());
                System.out.println("Title: " + book.getTitle());
                System.out.println("Authors: " + book.getAuthors());
                System.out.println("Language: " + book.getLanguage());
                System.out.println("Publication Date: " + book.getPublicationDate());
                System.out.println("-------------------------------------------------------");
            }

            // Finding a book by ID
            System.out.println("\nSearching for book by ID: 3");
            Book bookById = booksInfo.findById(3, conn);
            if (bookById != null) {
                System.out.println(bookById);
            } else {
                System.out.println("No book found with ID: 3");
            }

            // Finding a book by name
            System.out.println("\nSearching for book by name: 'Cartea ballast'");
            Book bookByName = booksInfo.findByName("Cartea ballast", conn);
            if (bookByName != null) {
                System.out.println(bookByName);
            } else {
                System.out.println("No book found with name: 'Cartea ballast'");
            }

            // Commit the transaction if everything went well
            Database.commit(conn);
        } catch (SQLException | IOException e) {
            System.err.println("SQL or I/O Error: " + e.getMessage());
            try {
                // Rollback the transaction in case of error
                Database.rollback(conn);
            } catch (SQLException ex) {
                System.err.println("Failed to rollback: " + ex.getMessage());
            }
        } finally {
            try {
                // Ensure connection is closed after operation
                Database.closeConnection(conn);
            } catch (SQLException e) {
                System.err.println("Failed to close database connection: " + e.getMessage());
            }
        }
    }

    private static void processBooksFromFile(Path path, Connection conn, BooksInfoDAO booksInfo) throws IOException {
        try (Stream<String> lines = Files.lines(path)) {
            lines
                    .skip(1) // Skip the header row
                    .forEach(line -> processLineAndCreateBook(line, conn, booksInfo));
        }
    }

    private static void processLineAndCreateBook(String line, Connection conn, BooksInfoDAO booksInfo) {
        String[] fields = line.split(",");
        Integer.parseInt(fields[0]);

        Book book = new Book(
                Integer.parseInt(fields[0]),
                fields[1],
                fields[2],
                fields[6],
                fields[10]
        );

        try {
            booksInfo.create(book, conn);
        } catch (SQLException e) {
            System.err.println("Error inserting book from line: " + e.getMessage());
        }
    }
}

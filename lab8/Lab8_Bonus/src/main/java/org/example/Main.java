package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = Database.getConnection();
            conn.setAutoCommit(false);

            BooksInfoDAO booksInfoDAO = new BooksInfoDAO();
            List<Book> allBooks = booksInfoDAO.findAll(conn);

            List<ReadingList> readingLists = createUnrelatedReadingLists(allBooks);


            for (ReadingList rl : readingLists) {
                rl.save(conn);
            }

            Database.commit(conn) ;
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            try {
                if (conn != null) {
                    Database.rollback(conn);
                }
            } catch (SQLException ex) {
                System.err.println("Failed to rollback: " + ex.getMessage());
            }

        } finally {
            try {
                if (conn != null) {
                    Database.closeConnection(conn);
                }
            } catch (SQLException e) {
                System.err.println("Failed to close database connection: " + e.getMessage());
            }
        }
    }

    private static List<ReadingList> createUnrelatedReadingLists(List<Book> books) {
        List<ReadingList> lists = new ArrayList<>();
        int idCounter = 1;
        while (!books.isEmpty()) {
            Book currentBook = books.remove(0);
            ReadingList rl = new ReadingList(idCounter++, "Unrelated List for " + currentBook.getTitle(), "2022-12-01"); // Modify this line to include the ID
            rl.addBook(currentBook.getId());


            books.removeIf(b -> b.getAuthors().equals(currentBook.getAuthors()) || b.getLanguage().equals(currentBook.getLanguage()));
            lists.add(rl);
        }
        return lists;
    }
}
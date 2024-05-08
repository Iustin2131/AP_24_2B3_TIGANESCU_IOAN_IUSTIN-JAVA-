package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = Database.getConnection();
            conn.setAutoCommit(false);

            BooksInfoDAO booksInfoDAO = new BooksInfoDAO();
            List<Book> allBooks = booksInfoDAO.findAll(conn);
            int limit = Math.min(allBooks.size(), 100);
            List<Book> first100Books = allBooks.subList(0, limit);

            for (Book r : first100Books) {

                List<Integer> unrelatedBookIdsWithR = createUnrelatedBookIdsWithR(first100Books, r, 10);

                ReadingList rl = new ReadingList(r.getId(), "Unrelated List for " + r.getTitle(), "2021-09-01");

                for (Integer bookId : unrelatedBookIdsWithR) {
                    rl.addBook(bookId);
                }

                rl.save(conn);
            }

            Database.commit(conn);
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

    private static List<Integer> createUnrelatedBookIdsWithR(List<Book> books, Book r, int maxDifference) {
        return books.stream()
                .filter(b -> !b.getAuthors().equals(r.getAuthors()) && !b.getLanguage().equals(r.getLanguage()) && b.getId() != r.getId())
                .map(Book::getId)
                .limit(maxDifference)
                .collect(Collectors.toList());
    }
}

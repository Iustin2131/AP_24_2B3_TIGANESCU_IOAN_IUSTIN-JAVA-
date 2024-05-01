package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BooksInfoDAO implements DAO<Book> {

    @Override
    public void create(Book book, Connection con) throws SQLException {
        String sql = "INSERT INTO BooksInfo (id, title, authors, language, publicationDate) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, book.getId());
            pstmt.setString(2, book.getTitle());
            pstmt.setString(3, book.getAuthors());
            pstmt.setString(4, book.getLanguage());
            pstmt.setString(5, book.getPublicationDate());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error when creating a book entry with ID " + book.getId() + ": " + e.getMessage(), e);
        }
    }

    @Override
    public List<Book> findAll(Connection con) throws SQLException {
        List<Book> books = new ArrayList<>();
        String query = "SELECT id, title, authors, language, publicationDate FROM BooksInfo";
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("authors"),
                        rs.getString("language"),
                        rs.getString("publicationDate")
                        ));
            }
        } catch (SQLException e) {
            throw new SQLException("Error when retrieving all books: " + e.getMessage(), e);
        }
        return books;
    }

    @Override
    public Book findById(int id, Connection con) throws SQLException {
        String query = "SELECT * FROM BooksInfo WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Book(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("authors"),
                            rs.getString("language"),
                            rs.getString("publicationDate")
                    );
                } else {
                    throw new SQLException("No book found with ID: " + id);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error when finding a book by ID " + id + ": " + e.getMessage(), e);
        }
    }

    @Override
    public Book findByName(String name, Connection con) throws SQLException {
        String query = "SELECT * FROM BooksInfo WHERE title = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Book(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("authors"),
                            rs.getString("language"),
                            rs.getString("publicationDate")
                    );
                } else {
                    throw new SQLException("No book found with title: " + name);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error when finding a book by name '" + name + "': " + e.getMessage(), e);
        }
    }
}

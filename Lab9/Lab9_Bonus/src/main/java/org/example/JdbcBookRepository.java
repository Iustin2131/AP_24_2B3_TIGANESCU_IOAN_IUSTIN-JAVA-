package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcBookRepository implements BookRepository {
    private static final String URL = "jdbc:sqlserver://localhost:3301;databaseName=java";
    private static final String USERNAME = "java";
    private static final String PASSWORD = "java";

    @Override
    public void save(Book book) {
        String sql = "INSERT INTO books (title, author_id) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, book.getTitle());
            statement.setLong(2, book.getAuthor().getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while saving book", e);
        }
    }
    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong("id"));
                book.setTitle(resultSet.getString("title"));
                // You need to fetch and set the author of the book here
                books.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching books", e);
        }

        return books;
    }
}

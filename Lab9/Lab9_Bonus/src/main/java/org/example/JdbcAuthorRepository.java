package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcAuthorRepository implements AuthorRepository {
    private static final String URL = "jdbc:sqlserver://localhost:3301;databaseName=java";
    private static final String USERNAME = "java";
    private static final String PASSWORD = "java";

    @Override
    public void save(Author author) {
        String sql = "INSERT INTO authors (name) VALUES (?)";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, author.getName());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while saving author", e);
        }
    }

    @Override
    public List<Author> findAll() {
        return null;
    }


}
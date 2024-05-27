package com.example.Lab11.Services;

import com.example.Lab11.ConnectionPool;
import com.example.Lab11.Model.Author;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
    private final ConnectionPool connectionPool;

    public AuthorService() {
        this.connectionPool = new ConnectionPool();
    }

    public List<Author> getAuthorsFromDB() {
        List<Author> authors = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, name FROM authors")) {

            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                authors.add(new Author(id, name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authors;
    }

    public Author getAuthorById(Integer id) {
        Author author = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT id, name FROM authors WHERE id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                author = new Author(rs.getInt("id"), rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return author;
    }

    public Author createAuthor(Author author) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement insertStmt = connection.prepareStatement("INSERT INTO authors (id, name) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            insertStmt.setInt(1, author.getId());
            insertStmt.setString(2, author.getName());
            insertStmt.executeUpdate();
            try (ResultSet generatedKeys = insertStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    author.setId(generatedKeys.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return author;
    }

    public Author updateAuthor(Integer id, Author author) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement("UPDATE authors SET name = ? WHERE id = ?")) {
            stmt.setString(1, author.getName());
            stmt.setInt(2, id);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                author.setId(id);
                return author;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteAuthor(Integer id) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement("DELETE FROM authors WHERE id = ?")) {
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

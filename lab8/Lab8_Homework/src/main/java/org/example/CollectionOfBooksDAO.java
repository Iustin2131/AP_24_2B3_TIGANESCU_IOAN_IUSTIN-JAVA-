package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CollectionOfBooksDAO implements DAO<String> {

    @Override
    public void create(String name, Connection con) throws SQLException {
        String sql = "INSERT INTO BooksList (name) VALUES (?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error when inserting new book name: " + e.getMessage(), e);
        }
    }

    @Override
    public List<String> findAll(Connection con) throws SQLException {
        List<String> names = new ArrayList<>();
        String query = "SELECT name FROM BooksList";
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                names.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new SQLException("Error when fetching all book names: " + e.getMessage(), e);
        }
        return names;
    }

    @Override
    public String findById(int id, Connection con) throws SQLException {
        String query = "SELECT name FROM BooksList WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("name");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error when finding a book by ID: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public String findByName(String name, Connection con) throws SQLException {
        String query = "SELECT name FROM BooksList WHERE name = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("name");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error when finding a book by name: " + e.getMessage(), e);
        }
        return null;
    }
}

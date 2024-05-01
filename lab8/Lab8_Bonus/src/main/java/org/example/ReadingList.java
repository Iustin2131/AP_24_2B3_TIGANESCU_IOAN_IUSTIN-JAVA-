package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReadingList {
    private int id;
    private String name;
    private String publicationDate;
    private List<Integer> bookIds;

    public ReadingList(int id, String name, String publicationDate) {
        this.id = id;
        this.name = name;
        this.publicationDate = publicationDate;
        this.bookIds = new ArrayList<>();
    }

    public void addBook(int bookId) {
        this.bookIds.add(bookId);
    }

    public void save(Connection conn) throws SQLException {
        String sql = "INSERT INTO ReadingList (id, name, creationTimestamp, books) VALUES (?, ?, TO_TIMESTAMP(?, 'YYYY-MM-DD'), ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, this.id);
            pstmt.setString(2, this.name);
            pstmt.setString(3, this.publicationDate);
            // Choose either comma-separated or space-separated format here
            String bookIdsString = bookIds.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(","));
            pstmt.setString(4, bookIdsString);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error when saving the reading list: " + e.getMessage(), e);
        }
    }
}

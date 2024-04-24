package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BooksInfoDAO {

    public void create( int id, String title, String authors, String language, String publicationDate) throws SQLException {
        Connection con = Database.getConnection();

        try (PreparedStatement pstmt = con.prepareStatement("insert into BooksInfo (id, title,authors,language,publicationDate) values (?,?,?,?,?)")) {
            pstmt.setInt(1, id);
            pstmt.setString(2, title);
            pstmt.setString(3, authors);
            pstmt.setString(4, language);
            pstmt.setString(5, publicationDate);

            pstmt.executeUpdate();
        }
    }

    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select id from BooksInfo where title='" + name + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }

    public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select title from BooksInfo where id='" + id + "'")) {
            return rs.next() ? rs.getString(1) : null;
        }
    }

    public void Print() throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select id, title, authors, language, publicationDate from BooksInfo")) {
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5));
            }
        }
    }
}

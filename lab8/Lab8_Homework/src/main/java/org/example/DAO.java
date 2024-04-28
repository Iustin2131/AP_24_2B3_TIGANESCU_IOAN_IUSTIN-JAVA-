package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    void create(T var1, Connection conn) throws SQLException;
    List<T> findAll(Connection conn) throws SQLException;
    T findById(int var1, Connection conn) throws SQLException;
    T findByName(String var1, Connection conn) throws SQLException;
}

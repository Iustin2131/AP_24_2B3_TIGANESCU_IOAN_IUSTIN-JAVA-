package org.example;

import java.util.List;

public interface BookRepository {
    void save(Book book);
    public List<Book> findAll();
}

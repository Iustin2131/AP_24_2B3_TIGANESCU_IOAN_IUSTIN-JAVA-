package org.example;

import java.util.List;

public class JpaBookRepository implements BookRepository {
    @Override
    public void save(Book book) {
        System.out.println("Saving book to database: " + book);
    }

    @Override
    public List<Book> findAll() {
        System.out.println("Retrieving all books from database");
        return null;
    }
}

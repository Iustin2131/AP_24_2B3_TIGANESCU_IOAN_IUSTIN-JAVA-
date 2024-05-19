package org.example;

import java.util.List;

public interface AuthorRepository {
    void save(Author author);
    List<Author> findAll();
}


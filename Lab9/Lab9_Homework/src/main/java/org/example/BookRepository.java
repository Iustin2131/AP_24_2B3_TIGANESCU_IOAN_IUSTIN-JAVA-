package org.example;

import jakarta.persistence.EntityManager;

public class BookRepository {
    private EntityManager entityManager;

    public BookRepository(jakarta.persistence.EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(Book book) {
        entityManager.getTransaction().begin();
        entityManager.persist(book);
        entityManager.getTransaction().commit();
    }

    public Book find(Long id) {
        return entityManager.find(Book.class, id);
    }
}
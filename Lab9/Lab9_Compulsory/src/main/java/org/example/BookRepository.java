package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.model.Book;

import java.util.List;

public class BookRepository
{
    private EntityManager entityManager;

    public BookRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(Book book) {
        entityManager.getTransaction().begin();
        entityManager.persist(book);
        entityManager.getTransaction().commit();
    }

    public Book findById(Long id) {
        return entityManager.find(Book.class, id);
    }

    public List<Book> findByName(String namePattern) {
        return entityManager
                .createNamedQuery("Book.findLikeName", Book.class)
                .setParameter("namePattern", namePattern)
                .getResultList();
    }
}

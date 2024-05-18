package org.example;

import jakarta.persistence.EntityManager;

public class AuthorRepository {
    private EntityManager entityManager;

    public AuthorRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(Author author) {
        entityManager.getTransaction().begin();
        entityManager.persist(author);
        entityManager.getTransaction().commit();
    }

    public Author find(Long id) {
        return entityManager.find(Author.class, id);
    }
}
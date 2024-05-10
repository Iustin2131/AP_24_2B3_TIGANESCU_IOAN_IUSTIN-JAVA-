package org.example;

import org.example.model.Book;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Singleton.getEntityManagerFactory();

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        BookRepository bookRepository = new BookRepository(entityManager);

        Book book = new Book();
        book.setTitle("Lord of the Rings");
        book.setAuthors("J.R.R. Tolkien");
        book.setLanguage("English");
        book.setPublicationdate("1954-07-29");

        bookRepository.create(book);

        Book foundBook = bookRepository.findById(1L);
        System.out.println("Found book: " + foundBook);

        List<Book> booksByName = bookRepository.findByName("Lord of the Rings");
        System.out.println("Books with name pattern : " + booksByName);

        entityManager.close();
        Singleton.closeEntityManagerFactory();
    }
}

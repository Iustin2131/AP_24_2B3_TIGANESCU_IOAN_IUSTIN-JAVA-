package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    private static final Logger logger = LoggerSetup.getLogger(Main.class.getName());

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Singleton.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            BookRepository bookRepository = new BookRepository(entityManager);
            PublishingHouseRepository publishingHouseRepository = new PublishingHouseRepository(entityManager);
            AuthorRepository authorRepository = new AuthorRepository(entityManager); // Assuming you have an AuthorRepository

            PublishingHouse publishingHouse = new PublishingHouse();
            publishingHouse.setName("Penguin Random House");
            publishingHouseRepository.create(publishingHouse);

            Author author1 = new Author();
            author1.setName("J.R.R. Tolkien");
            authorRepository.create(author1);

            Author author2 = new Author();
            author2.setName("Jony Tony");
            authorRepository.create(author2);

            // ManytoMany relationship
            Book book1 = new Book();
            book1.setTitle("Lord of the Rings");
            book1.setAuthors(Arrays.asList(author1, author2));
            book1.setAuthor(author1.getName());
            book1.setLanguage("English");
            book1.setPublicationdate("1954-07-29");
            book1.setPublishingHouse(publishingHouse); // ManyToOne relationship
            bookRepository.create(book1);

            // ManytoMany relationship
            Book book2 = new Book();
            book2.setTitle("Harry Potter");
            book2.setAuthors(Arrays.asList(author1)); // Set one author
            book2.setAuthor(author2.getName());
            book2.setLanguage("French");
            book2.setPublicationdate("2000-01-01");
            book2.setPublishingHouse(publishingHouse);  // ManyToOne relationship
            bookRepository.create(book2);

            // Add books to authors
            author1.setBooks(Arrays.asList(book1, book2));
            author2.setBooks(Arrays.asList(book1));

            // Add books to publishing house
            publishingHouse.setBooks(Arrays.asList(book1, book2));

            Book foundBook = bookRepository.find(1L);
            System.out.println("Found book: " + foundBook);

            logger.info("Application executed successfully.");

        } catch (Exception e) {
            logger.severe("Error during application execution: " + e.getMessage());
        } finally {
            entityManager.close();
            Singleton.closeEntityManagerFactory();
        }
    }

    public static class LoggerSetup {
        public static Logger getLogger(String className) {
            Logger logger = Logger.getLogger(className);
            FileHandler fileHandler;
            try {
                fileHandler = new FileHandler("application.log", true);
                logger.addHandler(fileHandler);
                SimpleFormatter formatter = new SimpleFormatter();
                fileHandler.setFormatter(formatter);
            } catch (IOException e) {
                logger.severe("Error setting up logger: " + e.getMessage());
            }
            return logger;
        }
    }
}
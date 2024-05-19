package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String dataAccessType = properties.getProperty("dataAccessType");
        RepositoryFactory factory;

        if ("JPA".equalsIgnoreCase(dataAccessType)) {
            factory = new JpaRepositoryFactory();
        } else if ("JDBC".equalsIgnoreCase(dataAccessType)) {
            factory = new JdbcRepositoryFactory();
        } else {
            throw new IllegalArgumentException("Unknown data access type: " + dataAccessType);
        }

        AuthorRepository authorRepository = factory.createAuthorRepository();
        BookRepository bookRepository = factory.createBookRepository();
        PublishingHouseRepository publishingHouseRepository = factory.createPublishingHouseRepository();

        Author author = new Author();
        author.setName("John Doe");
        authorRepository.save(author);

        Book book = new Book();
        book.setTitle("Example Book");
        book.setAuthor(author);
        bookRepository.save(book);

        // Create a new publishing house
        PublishingHouse publishingHouse = new PublishingHouse();
        publishingHouse.setName("Example Publishing House");
        publishingHouseRepository.save(publishingHouse);

        // Print all authors, books, and publishing houses
        System.out.println("Authors:");
        for (Author a : authorRepository.findAll()) {
            System.out.println(a.getName());
        }

        System.out.println("Books:");
        for (Book b : bookRepository.findAll()) {
            System.out.println(b.getTitle());
        }

        System.out.println("Publishing Houses:");
        for (PublishingHouse ph : publishingHouseRepository.findAll()) {
            System.out.println(ph.getName());
        }
    }
}
package org.example;

public interface RepositoryFactory {
    AuthorRepository createAuthorRepository();
    BookRepository createBookRepository();
    PublishingHouseRepository createPublishingHouseRepository();
}

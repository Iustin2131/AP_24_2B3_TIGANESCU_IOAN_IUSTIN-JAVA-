package org.example;

public class JdbcRepositoryFactory implements RepositoryFactory {
    @Override
    public AuthorRepository createAuthorRepository() {
        return (AuthorRepository) new JdbcAuthorRepository();
    }

    @Override
    public BookRepository createBookRepository() {
        return new JdbcBookRepository();
    }

    @Override
    public PublishingHouseRepository createPublishingHouseRepository() {
        return new JdbcPublishingHouseRepository();
    }
}

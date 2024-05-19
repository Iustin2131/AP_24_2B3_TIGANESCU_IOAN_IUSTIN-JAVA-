package org.example;

public class JpaRepositoryFactory implements RepositoryFactory {
    @Override
    public AuthorRepository createAuthorRepository() {
        return (AuthorRepository) new JpaAuthorRepository();
    }

    @Override
    public BookRepository createBookRepository() {
        return new JpaBookRepository();
    }

    @Override
    public PublishingHouseRepository createPublishingHouseRepository() {
        return new JpaPublishingHouseRepository();
    }
}

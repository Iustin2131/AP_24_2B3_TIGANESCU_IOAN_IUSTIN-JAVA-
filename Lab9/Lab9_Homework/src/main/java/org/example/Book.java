package org.example;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="books")
@NamedQueries({
        @NamedQuery(name= "Book.findLikeName",
                query = "SELECT b FROM Book b WHERE b.title LIKE concat('%', :namePattern, '%')")
})
@Getter
@Setter
public class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="title", nullable = false, length = 1000)
    private String title;
    @Column(name="authors", nullable = false, length = 1000)
    private String author;
    @Column(name="language", nullable = false, length = 1000)
    private String language;
    @Column(name="publicationdate", nullable = false, length = 1000)
    private String publicationdate;

    @ManyToOne
    private PublishingHouse publishingHouse;

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors; // kept as 'authors'

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Book(Long id, String title, String author, String language, String publicationdate, PublishingHouse publishingHouse) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.language = language;
        this.publicationdate = publicationdate;
        this.publishingHouse = publishingHouse;
    }

    public Book() {}

    public void setTitle(String title) {
        this.title = title;
    }


    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublicationdate(String publicationdate) {
        this.publicationdate = publicationdate;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setPublishingHouse(PublishingHouse publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + authors + '\'' +
                ", language='" + language + '\'' +
                ", publicationdate=" + publicationdate +
                '}';
    }
}
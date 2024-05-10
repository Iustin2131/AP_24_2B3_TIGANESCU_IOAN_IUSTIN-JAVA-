package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    private String authors;
    @Column(name="language", nullable = false, length = 1000)
    private String language;
    @Column(name="publicationdate", nullable = false, length = 1000)
    private String publicationdate;

    public Book(Long id, String title, String authors, String language, String publicationdate) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.language = language;
        this.publicationdate = publicationdate;
    }

    public Book() {}

    public void setTitle(String title) {
        this.title = title;
    }


    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public void setPublicationdate(String publicationdate) {
        this.publicationdate = publicationdate;
    }

    public void setLanguage(String language) {
        this.language = language;
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

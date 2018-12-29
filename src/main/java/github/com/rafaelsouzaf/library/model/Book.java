package github.com.rafaelsouzaf.library.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lib_book")
public class Book {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(max=100)
    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    private Date releaseDate = new Date();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "lib_book_author_id")

    @JoinTable(
            name = "Book_Author",
            joinColumns = { @JoinColumn(name = "book_id") },
            inverseJoinColumns = { @JoinColumn(name = "author_id") }
    )
    private Set<BookAuthor> bookAuthor = new HashSet<>();

    protected Book() {}

    public Book(String title, BookAuthor bookAuthor) {
        this.title = title;
        this.bookAuthor.add(bookAuthor);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<BookAuthor> getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(Set<BookAuthor> bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

}

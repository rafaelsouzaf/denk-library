package github.com.rafaelsouzaf.library.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "lib_book")
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max=100)
    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    private Date releaseDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lib_book_author_id")
    private BookAuthor bookAuthor;

    protected Book() {}

    public Book(String title, BookAuthor bookAuthor) {
        this.title = title;
        this.bookAuthor = bookAuthor;
        this.releaseDate = new Date();
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

    public BookAuthor getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(BookAuthor bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}

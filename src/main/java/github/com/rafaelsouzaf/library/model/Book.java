package github.com.rafaelsouzaf.library.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "lib_book")
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max=100)
    private String title;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lib_book_author_id")
    private BookAuthor bookAuthor;

    public Book(String title, BookAuthor bookAuthor) {
        this.title = title;
        this.bookAuthor = bookAuthor;
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
}

package github.com.rafaelsouzaf.library.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "lib_borrow")
public class Borrow {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    // TODO the old java.util.Date should not be used; https://www.baeldung.com/java-8-date-time-intro
    // for older projects joda-time is the way to go; also make sure that TZ is stored in the database (even in a non-international project)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date date = new Date();

    @NotNull
    private Long bookId;

    @NotNull
    private Long userId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private BorrowStatus status;

    protected Borrow() {}

    public Borrow(Book book, User user, BorrowStatus status) {
        this.bookId = book.getId();
        this.userId = user.getId();
        this.date = new Date();
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BorrowStatus getStatus() {
        return status;
    }

    public void setStatus(BorrowStatus status) {
        this.status = status;
    }
}

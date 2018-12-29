package github.com.rafaelsouzaf.library.controller;

import github.com.rafaelsouzaf.library.model.Book;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    public static Specification<Book> titleExact(String title) {
        return (book, cq, cb) -> cb.equal(book.get("title"), title);
    }

}

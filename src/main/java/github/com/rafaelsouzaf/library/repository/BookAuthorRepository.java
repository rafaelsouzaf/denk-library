package github.com.rafaelsouzaf.library.repository;

import github.com.rafaelsouzaf.library.model.Book;
import github.com.rafaelsouzaf.library.model.BookAuthor;
import org.springframework.data.repository.CrudRepository;

public interface BookAuthorRepository extends CrudRepository<BookAuthor, Long> {

}

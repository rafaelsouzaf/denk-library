package github.com.rafaelsouzaf.library.repository;

import github.com.rafaelsouzaf.library.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

}

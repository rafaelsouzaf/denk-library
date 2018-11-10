package github.com.rafaelsouzaf.library.repository;

import github.com.rafaelsouzaf.library.model.Book;
import github.com.rafaelsouzaf.library.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {

}

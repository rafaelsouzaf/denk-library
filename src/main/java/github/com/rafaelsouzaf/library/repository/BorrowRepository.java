package github.com.rafaelsouzaf.library.repository;

import github.com.rafaelsouzaf.library.model.Book;
import github.com.rafaelsouzaf.library.model.Borrow;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BorrowRepository extends CrudRepository<Borrow, Long> {

}

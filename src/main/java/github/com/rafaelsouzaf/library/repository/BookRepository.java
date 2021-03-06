package github.com.rafaelsouzaf.library.repository;

import github.com.rafaelsouzaf.library.model.Book;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    @Query("select a from Book a ORDER BY a.releaseDate ASC")
    List<Book> findAllOrderByASC();

    @Query("select a from Book a ORDER BY a.releaseDate DESC")
    List<Book> findAllOrderByDESC();


    // TODO next step: Combine several filters; org.springframework.data.jpa.domain.Specification and javax.persistence.criteria.Predicate *may* be useful
}

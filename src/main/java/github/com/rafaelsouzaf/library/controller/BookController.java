package github.com.rafaelsouzaf.library.controller;

import github.com.rafaelsouzaf.library.exception.BookNotFoundException;
import github.com.rafaelsouzaf.library.model.Book;
import github.com.rafaelsouzaf.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@RestController
@RequestMapping(path="/book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private EntityManager entityManager;

    @GetMapping("/list")
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @GetMapping("/list/order/asc")
    public Iterable<Book> findAllOrderByASC() {
        return bookRepository.findAllOrderByASC();
    }

    @GetMapping("/list/order/desc")
    public Iterable<Book> findAllOrderByDESC() {
        return bookRepository.findAllOrderByDESC();
    }

    @GetMapping("/get/{id}")
    public Book get(@PathVariable Long id) throws BookNotFoundException {
        return bookRepository
                .findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));  // TODO stacktraces are leaked to the client
    }

    @GetMapping("/get/title/{title}")
    public List<Book> findByTitle(@PathVariable String title) throws BookNotFoundException {

        if (!StringUtils.hasText(title)) {
            throw new BookNotFoundException("Title can not be empty.");
        }

        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> book = criteriaQuery.from(Book.class);
        Predicate titlePredicate = criteriaBuilder.like(book.get("title"), "%" + title + "%");
        criteriaQuery.where(titlePredicate);

        TypedQuery<Book> query = this.entityManager.createQuery(criteriaQuery);
        List<Book> listBooks = query.getResultList();
        if (listBooks.size() == 0) {
            throw new BookNotFoundException(title);
        }

        return listBooks;

    }

    @PutMapping("/add")
    public Book add(@RequestBody Book user) {
        return bookRepository.save(user);
    }

    @PostMapping("/edit/{id}")
    public Book edit(@RequestBody Book newBook, @PathVariable Long id ) throws BookNotFoundException {
        return bookRepository.findById(id)
                .map(book -> {
//                    book.getBookAuthor().setFirstName(newBook.getBookAuthor().getFirstName());
//                    book.getBookAuthor().setLastName(newBook.getBookAuthor().getLastName());
                    book.setTitle(newBook.getTitle());
                    return bookRepository.save(book);
                })
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) throws BookNotFoundException {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException(id);
        }
        bookRepository.deleteById(id);
    }

}
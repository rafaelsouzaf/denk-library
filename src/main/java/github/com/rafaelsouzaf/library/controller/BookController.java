package github.com.rafaelsouzaf.library.controller;

import github.com.rafaelsouzaf.library.exception.BookNotFoundException;
import github.com.rafaelsouzaf.library.model.Book;
import github.com.rafaelsouzaf.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

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
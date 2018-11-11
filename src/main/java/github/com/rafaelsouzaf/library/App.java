package github.com.rafaelsouzaf.library;

import github.com.rafaelsouzaf.library.model.*;
import github.com.rafaelsouzaf.library.repository.BookRepository;
import github.com.rafaelsouzaf.library.repository.BorrowRepository;
import github.com.rafaelsouzaf.library.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        log.info("REST SERVER IS STARTED.");
    }

    @Bean
    public CommandLineRunner demo(UserRepository userRepository,
                                  BookRepository bookRepository,
                                  BorrowRepository borrowRepository) {
        return (args) -> {

            userRepository.save(new User("Rafael", "Souza Fijalkowski", "xyxy", UserRole.ADMIN));
            userRepository.save(new User("Chloe", "O'Brian", "xyxy", UserRole.LIBRARIAN));
            userRepository.save(new User("Jack", "Bauer", "xyxy", UserRole.VISITOR));
            userRepository.save(new User("Kim", "Bauer", "xyxy", UserRole.VISITOR));
            userRepository.save(new User("David", "Palmer", "xyxy", UserRole.VISITOR));

            bookRepository.save(new Book("book title 1", new BookAuthor("John 1", "Bingo")));
            bookRepository.save(new Book("book title 2", new BookAuthor("John 2", "Bingo")));
            bookRepository.save(new Book("book title 3", new BookAuthor("John 3", "Bingo")));
            bookRepository.save(new Book("book title 4", new BookAuthor("John 4", "Bingo")));

            User user = new User("Tom", "Bueno", "xyxy", UserRole.VISITOR);
            Book book = new Book("book title 4", new BookAuthor("John 4", "Bingo"));
            userRepository.save(user);
            bookRepository.save(book);
            borrowRepository.save(new Borrow(book, user, BorrowStatus.AVAILABLE));

        };
    }

}

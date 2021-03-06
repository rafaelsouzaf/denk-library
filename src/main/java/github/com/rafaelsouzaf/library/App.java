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

            /*
             * NEW USERS
             */
            userRepository.save(new User("admin@gmail.com", "Rafael", "Souza Fijalkowski", "admin", UserRole.ROLE_ADMIN, true));
            userRepository.save(new User("librarian@gmail.com", "Chloe", "O'Brian", "librarian", UserRole.ROLE_LIBRARIAN, true));
            userRepository.save(new User("visitor@gmail.com", "Chloe", "O'Brian", "visitor", UserRole.ROLE_VISITOR, true));
            userRepository.save(new User("jack@gmail.com", "Jack", "Bauer", "xyxy", UserRole.ROLE_VISITOR, true));
            userRepository.save(new User("kim@gmail.com", "Kim", "Bauer", "xyxy", UserRole.ROLE_VISITOR, true));
            userRepository.save(new User("david@gmail.com", "David", "Palmer", "xyxy", UserRole.ROLE_VISITOR, true));

            /*
             * NEW BOOKS
             */
            bookRepository.save(new Book("book title 1", new BookAuthor("John 1", "Bingo")));
            bookRepository.save(new Book("book title 2", new BookAuthor("John 2", "Bingo")));
            bookRepository.save(new Book("book title 3", new BookAuthor("John 3", "Bingo")));
            bookRepository.save(new Book("book title 4", new BookAuthor("John 4", "Bingo")));

            /*
             * RENT
             */
            User user = new User("tom@gmail.com", "Tom", "Bueno", "xyxy", UserRole.ROLE_VISITOR, true);
            Book book = new Book("book title 4", new BookAuthor("John 4", "Bingo"));
            userRepository.save(user);
            bookRepository.save(book);
            borrowRepository.save(new Borrow(book, user, BorrowStatus.AVAILABLE));

            user = new User("gina@gmail.com", "Gina", "Boa", "xyxy", UserRole.ROLE_VISITOR, true);
            book = new Book("book title new", new BookAuthor("Persa", "Boom"));
            userRepository.save(user);
            bookRepository.save(book);
            borrowRepository.save(new Borrow(book, user, BorrowStatus.RENTED));

        };
    }

}

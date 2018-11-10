package github.com.rafaelsouzaf.library;

import github.com.rafaelsouzaf.library.model.Book;
import github.com.rafaelsouzaf.library.model.BookAuthor;
import github.com.rafaelsouzaf.library.model.User;
import github.com.rafaelsouzaf.library.model.UserRole;
import github.com.rafaelsouzaf.library.repository.BookRepository;
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
    public CommandLineRunner demo(UserRepository repository,
                                  BookRepository bookRepository) {
        return (args) -> {

            repository.save(new User("Rafael", "Souza Fijalkowski", "xyxy", UserRole.ADMIN));
            repository.save(new User("Chloe", "O'Brian", "xyxy", UserRole.LIBRARIAN));
            repository.save(new User("Jack", "Bauer", "xyxy", UserRole.VISITOR));
            repository.save(new User("Kim", "Bauer", "xyxy", UserRole.VISITOR));
            repository.save(new User("David", "Palmer", "xyxy", UserRole.VISITOR));

            bookRepository.save(new Book("book title 1", new BookAuthor("John 1", "Bingo")));
            bookRepository.save(new Book("book title 2", new BookAuthor("John 2", "Bingo")));
            bookRepository.save(new Book("book title 3", new BookAuthor("John 3", "Bingo")));
            bookRepository.save(new Book("book title 4", new BookAuthor("John 4", "Bingo")));

        };
    }

}

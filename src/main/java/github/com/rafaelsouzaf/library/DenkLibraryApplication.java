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
public class DenkLibraryApplication {

    private static final Logger log = LoggerFactory.getLogger(DenkLibraryApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DenkLibraryApplication.class, args);
        log.info("REST SERVER IS STARTED.");
    }

    @Bean
    public CommandLineRunner demo(UserRepository repository, BookRepository bookRepository) {
        return (args) -> {
            // save a couple of users
            repository.save(new User("Rafael", "Souza Fijalkowski", "xyxy", UserRole.ADMIN));
            repository.save(new User("Chloe", "O'Brian", "xyxy", UserRole.LIBRARIAN));
            repository.save(new User("Jack", "Bauer", "xyxy", UserRole.VISITOR));
            repository.save(new User("Kim", "Bauer", "xyxy", UserRole.VISITOR));
            repository.save(new User("David", "Palmer", "xyxy", UserRole.VISITOR));
            repository.save(new User("Michelle", "Dessler"));

            Book book1 = new Book("titulo do livro aqui");
//            book1.setAuthor(new BookAuthor("John", "Coffee"));

            Book book2 = new Book("titulo do livro aqui");
//            book2.setAuthor(new BookAuthor("Maria", "Pé"));

            bookRepository.save(book1);
            bookRepository.save(book2);



        };
    }

}

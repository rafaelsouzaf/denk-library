package github.com.rafaelsouzaf.library;

import github.com.rafaelsouzaf.library.model.User;
import github.com.rafaelsouzaf.library.model.UserRole;
import github.com.rafaelsouzaf.library.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class DenkLibraryApplication {

    private static final Logger log = LoggerFactory.getLogger(DenkLibraryApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DenkLibraryApplication.class, args);
        log.info("REST SERVER IS STARTED.");
    }

    @Bean
    public CommandLineRunner demo(UserRepository repository) {
        return (args) -> {
            // save a couple of users
            repository.save(new User("Rafael", "Souza Fijalkowski", UserRole.ADMIN));
            repository.save(new User("Chloe", "O'Brian", UserRole.LIBRARIAN));
            repository.save(new User("Jack", "Bauer", UserRole.VISITOR));
            repository.save(new User("Kim", "Bauer", UserRole.VISITOR));
            repository.save(new User("David", "Palmer", UserRole.VISITOR));
            repository.save(new User("Michelle", "Dessler"));
        };
    }

}

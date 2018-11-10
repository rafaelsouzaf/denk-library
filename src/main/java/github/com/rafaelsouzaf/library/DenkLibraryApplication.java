package github.com.rafaelsouzaf.library;

import github.com.rafaelsouzaf.library.entity.User;
import github.com.rafaelsouzaf.library.entity.UserRepository;
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
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }

    @Bean
    public CommandLineRunner demo(UserRepository repository) {
        return (args) -> {
            // save a couple of customers
            repository.save(new User("Jack", "Bauer"));
            repository.save(new User("Chloe", "O'Brian"));
            repository.save(new User("Kim", "Bauer"));
            repository.save(new User("David", "Palmer"));
            repository.save(new User("Michelle", "Dessler"));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (User user : repository.findAll()) {
                log.info(user.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            repository.findById(1L)
                    .ifPresent(user -> {
                        log.info("User found with findById(1L):");
                        log.info("--------------------------------");
                        log.info(user.toString());
                        log.info("");
                    });

            // fetch customers by last name
            log.info("User found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            repository.findByLastName("Bauer").forEach(bauer -> {
                log.info(bauer.toString());
            });
            // for (Customer bauer : repository.findByLastName("Bauer")) {
            // 	log.info(bauer.toString());
            // }
            log.info("");
        };
    }

}

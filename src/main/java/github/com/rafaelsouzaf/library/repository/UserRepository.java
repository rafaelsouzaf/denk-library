package github.com.rafaelsouzaf.library.repository;

import github.com.rafaelsouzaf.library.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByLastName(String lastName);

}

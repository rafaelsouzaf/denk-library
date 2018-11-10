package github.com.rafaelsouzaf.library.repository;

import java.util.List;

import github.com.rafaelsouzaf.library.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByLastName(String lastName);

}

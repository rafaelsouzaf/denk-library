package github.com.rafaelsouzaf.library.controller;

import github.com.rafaelsouzaf.library.exception.UserNotFoundException;
import github.com.rafaelsouzaf.library.model.User;
import github.com.rafaelsouzaf.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/list")
    @Secured({"ROLE_ADMIN", "ROLE_LIBRARIAN"})
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/get/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_LIBRARIAN"})
    public User get(@PathVariable Long id) throws UserNotFoundException {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/add")
    @Secured("ROLE_ADMIN")
    public User add(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/edit/{id}")
    @Secured("ROLE_ADMIN")
    public User edit(@RequestBody User newUser, @PathVariable Long id ) throws UserNotFoundException {
        return userRepository.findById(id)
                .map(user -> {
                    user.setEmail(newUser.getEmail());
                    user.setFirstName(newUser.getFirstName());
                    user.setLastName(newUser.getLastName());
                    user.setUserRole(newUser.getUserRole());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/delete/{id}")
    @Secured("ROLE_ADMIN")
    public void delete(@PathVariable Long id) throws UserNotFoundException {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }

}
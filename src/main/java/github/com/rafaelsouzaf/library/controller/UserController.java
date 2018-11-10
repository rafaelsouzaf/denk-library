package github.com.rafaelsouzaf.library.controller;

import github.com.rafaelsouzaf.library.model.User;
import github.com.rafaelsouzaf.library.model.UserRole;
import github.com.rafaelsouzaf.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/list")
    public String index() {
        userRepository.findAll().forEach(user -> {
            System.out.println(user);
        });
        return "Listing users!";
    }

    @PostMapping("/add")
    public String add() {
        User user = new User("Lero1", "Lero2", UserRole.VISITOR);
        userRepository.save(user);
        return "Adding users!";
    }

    @PostMapping("/edit")
    public String edit() {
        return "Edit users!";
    }

    @DeleteMapping("/delete")
    public String delete() {
        return "Delete users!";
    }

}
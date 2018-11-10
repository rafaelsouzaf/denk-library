package github.com.rafaelsouzaf.library.controller;

import github.com.rafaelsouzaf.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/login")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String login() {
        return "Login!";
    }

    @PostMapping("/logout")
    public String logout() {
        return "Logout!";
    }

}


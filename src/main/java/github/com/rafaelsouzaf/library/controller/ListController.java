package github.com.rafaelsouzaf.library.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class ListController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}
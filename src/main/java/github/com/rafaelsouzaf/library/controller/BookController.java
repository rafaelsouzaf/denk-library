package github.com.rafaelsouzaf.library.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @RequestMapping("/book/list")
    public String index() {
        return "Listing books!";
    }

}
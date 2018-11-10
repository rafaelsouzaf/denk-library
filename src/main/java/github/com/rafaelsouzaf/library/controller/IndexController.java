package github.com.rafaelsouzaf.library.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/")
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "HomePage!";
    }

}
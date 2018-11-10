package github.com.rafaelsouzaf.library.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping("/user/list")
    public String index() {
        return "Listing users!";
    }

    @RequestMapping("/user/add")
    public String add() {
        return "Adding users!";
    }

    @RequestMapping("/user/edit")
    public String edit() {
        return "Edit users!";
    }

    @RequestMapping("/user/delete")
    public String delete() {
        return "Delete users!";
    }

}
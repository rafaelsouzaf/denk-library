package github.com.rafaelsouzaf.library.controller;

import github.com.rafaelsouzaf.library.exception.BookNotFoundException;
import github.com.rafaelsouzaf.library.exception.BorrowNotFoundException;
import github.com.rafaelsouzaf.library.exception.UserNotFoundException;
import github.com.rafaelsouzaf.library.model.Book;
import github.com.rafaelsouzaf.library.model.Borrow;
import github.com.rafaelsouzaf.library.model.BorrowStatus;
import github.com.rafaelsouzaf.library.model.User;
import github.com.rafaelsouzaf.library.repository.BookRepository;
import github.com.rafaelsouzaf.library.repository.BorrowRepository;
import github.com.rafaelsouzaf.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/borrow")
public class BorrowController {

    @Autowired
    private BorrowRepository borrowRepository;

    @GetMapping("/get/{id}")
    public Borrow get(@PathVariable Long id) throws BookNotFoundException {
        return borrowRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/add")
    public Borrow add(@RequestBody Borrow borrow) {
        return borrowRepository.save(borrow);
    }

    @PostMapping("/change-status/{borrowId}/{newStatus}")
    public Borrow edit(@PathVariable Long borrowId, @PathVariable BorrowStatus newStatus) throws BorrowNotFoundException {
        // TODO service layer may soon be needed; this will be where to put more logic, checks/ constraints, etc.
        return borrowRepository.findById(borrowId)
                .map(borrow -> {
                    borrow.setStatus(newStatus);
                    return borrowRepository.save(borrow);
                })
                .orElseThrow(() -> new UserNotFoundException(borrowId));
    }

}
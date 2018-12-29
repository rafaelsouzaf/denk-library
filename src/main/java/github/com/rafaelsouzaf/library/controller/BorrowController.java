package github.com.rafaelsouzaf.library.controller;

import github.com.rafaelsouzaf.library.exception.BookNotFoundException;
import github.com.rafaelsouzaf.library.exception.BorrowNotFoundException;
import github.com.rafaelsouzaf.library.exception.UserNotFoundException;
import github.com.rafaelsouzaf.library.model.Borrow;
import github.com.rafaelsouzaf.library.model.BorrowStatus;
import github.com.rafaelsouzaf.library.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/borrow")
public class BorrowController {

    @Autowired
    private BorrowRepository borrowRepository;

    @GetMapping("/get/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_LIBRARIAN", "ROLE_VISITOR"})
    public Borrow get(@PathVariable Long id) throws BookNotFoundException {
        return borrowRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/add")
    @Secured({"ROLE_ADMIN", "ROLE_LIBRARIAN", "ROLE_VISITOR"})
    public Borrow add(@RequestBody Borrow borrow) {
        return borrowRepository.save(borrow);
    }

    @PostMapping("/change-status/{borrowId}/{newStatus}")
    @Secured({"ROLE_ADMIN", "ROLE_LIBRARIAN", "ROLE_VISITOR"})
    public Borrow edit(@PathVariable Long borrowId, @PathVariable BorrowStatus newStatus) throws BorrowNotFoundException {
        // TODO service layer may soon be needed; this will be where to put more logic, checks/ constraints, etc.
        return borrowRepository.findById(borrowId)
                .map(borrow -> {
                    borrow.setStatus(newStatus);
                    return borrowRepository.save(borrow);
                })
                .orElseThrow(() -> new BorrowNotFoundException(borrowId));
    }

}
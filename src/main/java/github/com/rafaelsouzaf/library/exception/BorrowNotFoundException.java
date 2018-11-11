package github.com.rafaelsouzaf.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BorrowNotFoundException extends RuntimeException {
    public BorrowNotFoundException(Long id) {
        super("Borrow not found: " + id);
    }
}
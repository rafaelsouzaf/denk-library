package github.com.rafaelsouzaf.library.model;

public enum BorrowStatus {

    AVAILABLE(0),
    RENTED(1);

    private int status;
    BorrowStatus(int status) {
        this.status = status;
    }

}

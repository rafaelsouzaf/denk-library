package github.com.rafaelsouzaf.library.model;

public enum UserRole {

    /**
     * Can do everything
     */
    ROLE_ADMIN,

    /**
     * Can create/edits Books, lends books to user x or returns them
     */
    ROLE_LIBRARIAN,

    /**
     * Can lend a book
     */
    ROLE_VISITOR

}

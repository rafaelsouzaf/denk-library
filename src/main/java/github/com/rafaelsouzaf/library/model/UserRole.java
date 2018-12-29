package github.com.rafaelsouzaf.library.model;

public enum UserRole {

    /**
     * Login/logout
     */
    ROLE_EVERYONE,

    /**
     * Creates/Edits Users
     */
    ROLE_ADMIN,

    /**
     * Create/Edits Books, lends books to user x or returns them
     */
    ROLE_LIBRARIAN,

    /**
     * Lends books for himself
     */
    ROLE_VISITOR

}

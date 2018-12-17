package github.com.rafaelsouzaf.library.model;

public enum UserRole {

    /**
     * Login/logout
     */
    EVERYONE(0),

    /**
     * Creates/Edits Users
     */
    ADMIN(1),

    /**
     * Create/Edits Books, lends books to user x or returns them
     */
    LIBRARIAN(2),

    /**
     * Lends books for himself
     */
    VISITOR(3);

    private int value;  // TODO what is the value gonna be used for? So far it's the same as UserRole.ordinal
    UserRole(int value) {
        this.value = value;
    }
}

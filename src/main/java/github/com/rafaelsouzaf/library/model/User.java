package github.com.rafaelsouzaf.library.model;

import javax.persistence.*;

@Entity
@Table(name = "lib_user")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private UserRole userRole;

    protected User() {}

    public User(String firstName, String lastName) {
        this(firstName, lastName, UserRole.EVERYONE);
    }

    public User(String firstName, String lastName, UserRole userRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%d, firstName='%s', lastName='%s', role='%s']",
                id, firstName, lastName, userRole);
    }

}
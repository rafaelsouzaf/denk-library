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
    private String password;

    protected User() {}

    public User(String firstName, String lastName) {
        this(firstName, lastName, null,  UserRole.EVERYONE);
    }

    public User(String firstName, String lastName, String password, UserRole userRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.userRole = userRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
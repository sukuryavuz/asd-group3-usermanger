package it.fh.campus.entities;

public class User {

    private final String firstName;
    private final String lastName;
    private final String username;
    private String password;

    public User(String firstName, String lastname, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastname;
        this.username = username;
        this.password = password;
    }

    public String getFirstname() {
        return firstName;
    }

    public String getLastname() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

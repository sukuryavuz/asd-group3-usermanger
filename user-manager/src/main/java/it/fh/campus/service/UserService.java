package it.fh.campus.service;

import it.fh.campus.entities.User;

import java.io.IOException;
import java.util.Optional;

public interface UserService {

    void createAccount(String firstName, String lastName, String username, String password) throws IOException;

    boolean isUsernameUnique(String username);

    void deleteAccount(User user) throws IOException;

    Optional<User> login(String username, String password);

    void changePassword(User user, String newPassword) throws IOException;
}

package it.fh.campus.service;

import it.fh.campus.entities.User;

import java.io.IOException;

public interface UserService {

    void createAccount(String firstname, String lastname, String username, String password) throws IOException;

    boolean isUsernameUnique(String username);

    boolean deleteAccount(User user);

    User login(String username, String password);

    boolean logout(User user);

    boolean changePassword(User user, String newPassword);
}

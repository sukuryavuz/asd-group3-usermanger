package it.fh.campus.service;

import it.fh.campus.entities.User;

import java.io.IOException;

public interface UserService {

    void createAccount(String firstname, String lastname, String username, String password) throws IOException;

    boolean isUsernameUnique(String username);

    void deleteAccount(User user) throws IOException;

    User login(String username, String password);

    void changePassword(User user, String newPassword) throws IOException;
}

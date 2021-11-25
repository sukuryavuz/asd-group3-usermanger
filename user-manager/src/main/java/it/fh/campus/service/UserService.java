package it.fh.campus.service;

import it.fh.campus.entities.User;

import java.io.IOException;

public interface UserService {

    boolean createAccount(String firstname, String lastname, String username, String password) throws IOException;

    boolean deleteAccount(User user);

    User login(String username, String password);

    boolean logout(User user);

    boolean changePassword(User user, String newPassword);
}

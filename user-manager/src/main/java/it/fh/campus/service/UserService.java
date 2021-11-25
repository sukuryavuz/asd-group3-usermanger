package it.fh.campus.service;

import it.fh.campus.entities.User;

public interface UserService {

    User createAccount(String firstname, String lastname, String username, String password);

    boolean deleteAccount(User user);

    User login(String username, String password);

    boolean logout(User user);

    boolean changePassword(User user, String newPassword);
}

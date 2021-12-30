package it.fh.campus.service;

import it.fh.campus.entities.User;
import it.fh.campus.exceptions.UserNameNotUniqueException;
import it.fh.campus.exceptions.UserNameOrPasswordNotCorrectException;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface UserService {

    User createAccount(String firstName, String lastName, String userName, String password) throws IOException, ParseException;

    void checkUserNameUnique(String userName) throws IOException, ParseException, UserNameNotUniqueException;

    void deleteAccount(User user) throws IOException, ParseException;

    User login(String userName, String password) throws UserNameOrPasswordNotCorrectException, IOException, ParseException;

    void changePassword(User user, String newPassword) throws IOException, ParseException;
}

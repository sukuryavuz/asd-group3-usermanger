package it.fh.campus.service;

import it.fh.campus.utils.Rijndael;
import it.fh.campus.utils.UserFileHandler;
import it.fh.campus.entities.User;
import it.fh.campus.exceptions.UserNameNotUniqueException;
import it.fh.campus.exceptions.UserNameOrPasswordNotCorrectException;
import it.fh.campus.mapper.JsonToUserMapper;
import it.fh.campus.mapper.UserToJsonMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    @Override
    public User createAccount(String firstName, String lastName, String userName, String password) throws IOException, ParseException {
        User user = new User(firstName, lastName, userName, Rijndael.encrypt(password));
        JSONObject userJson = UserToJsonMapper.map(user);
        UserFileHandler.addUser(userJson);
        return user;
    }

    @Override
    public void checkUserNameUnique(String userName) throws IOException, ParseException, UserNameNotUniqueException {
        UserFileHandler.findUserByUsername(userName).ifPresent(user1 -> {throw new UserNameNotUniqueException();});
    }

    @Override
    public void deleteAccount(User user) throws IOException, ParseException {
        JSONObject userJson = UserToJsonMapper.map(user);
        UserFileHandler.removeUser(userJson);
    }

    @Override
    public User login(String userName, String password) throws UserNameOrPasswordNotCorrectException, IOException, ParseException {
        Optional<JSONObject> userJson = UserFileHandler.findUserByUsername(userName);
        Optional<User> user = JsonToUserMapper.map(userJson);
        if (user.isPresent() && password.equals(Rijndael.decrypt(user.get().getPassword()))) {
            return user.get();
        }
        throw new UserNameOrPasswordNotCorrectException();
    }

    @Override
    public void changePassword(User user, String newPassword) throws IOException, ParseException {
        UserFileHandler.removeUser(UserToJsonMapper.map(user));
        user.setPassword(Rijndael.encrypt(newPassword));
        UserFileHandler.addUser(UserToJsonMapper.map(user));
    }
}

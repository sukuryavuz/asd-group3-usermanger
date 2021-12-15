package it.fh.campus.service;

import it.fh.campus.utilities.Rijndael;
import it.fh.campus.utilities.UserFileHandler;
import it.fh.campus.entities.User;
import it.fh.campus.mapper.JsonToUserMapper;
import it.fh.campus.mapper.UserToJsonMapper;
import org.json.simple.JSONObject;

public class UserServiceImpl implements UserService {

    @Override
    public void createAccount(String firstname, String lastname, String username, String password) {
        User user = new User(firstname, lastname, username, Rijndael.encrypt(password));
        JSONObject userJson = UserToJsonMapper.map(user);
        UserFileHandler.addUser(userJson);
    }

    @Override
    public boolean isUsernameUnique(String username) {
        return UserFileHandler.findUserByUsername(username) == null;
    }

    @Override
    public void deleteAccount(User user) {
        JSONObject userJson = UserToJsonMapper.map(user);
        UserFileHandler.removeUser(userJson);
    }

    @Override
    public User login(String username, String password) {
        JSONObject userJson = UserFileHandler.findUserByUsername(username);
        User user = JsonToUserMapper.map(userJson);
        if (user != null && password.equals(Rijndael.decrypt(user.getPassword()))) {
            return user;
        }
        return null;
    }

    @Override
    public void changePassword(User user, String newPassword) {
        UserFileHandler.removeUser(UserToJsonMapper.map(user));
        user.setPassword(Rijndael.encrypt(newPassword));
        UserFileHandler.addUser(UserToJsonMapper.map(user));
    }
}

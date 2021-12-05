package it.fh.campus.service;

import it.fh.campus.Rijndael;
import it.fh.campus.Main;
import it.fh.campus.UserFileHandler;
import it.fh.campus.entities.User;
import it.fh.campus.mapper.JsonToUserMapper;
import it.fh.campus.mapper.UserToJsonMapper;
import org.json.simple.JSONObject;

import java.io.IOException;

public class UserServiceImpl implements UserService {

    private final String key = Main.getKey();

    @Override
    public void createAccount(String firstname, String lastname, String username, String password) throws IOException {
        User user = new User(firstname, lastname, username, Rijndael.encrypt(password, key));
        JSONObject userJson = UserToJsonMapper.map(user);
        UserFileHandler.addUser(userJson);
    }

    @Override
    public boolean isUsernameUnique(String username) {
        return UserFileHandler.findUserByUsername(username) == null;
    }

    @Override
    public void deleteAccount(User user) throws IOException {
        JSONObject userJson = UserToJsonMapper.map(user);
        UserFileHandler.removeUser(userJson);
    }

    @Override
    public User login(String username, String password) {
        JSONObject userJson = UserFileHandler.findUserByUsername(username);
        User user = JsonToUserMapper.map(userJson);
        if (user != null && password.equals(Rijndael.decrypt(user.getPassword(), key))) {
            return user;
        }
        return null;
    }

    @Override
    public void changePassword(User user, String newPassword) throws IOException {
        UserFileHandler.removeUser(UserToJsonMapper.map(user));
        user.setPassword(Rijndael.encrypt(newPassword, key));
        UserFileHandler.addUser(UserToJsonMapper.map(user));
    }
}

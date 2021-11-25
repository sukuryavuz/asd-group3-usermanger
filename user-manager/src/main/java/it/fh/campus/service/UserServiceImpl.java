package it.fh.campus.service;

import it.fh.campus.AES;
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
    public boolean createAccount(String firstname, String lastname, String username, String password) throws IOException {
        JSONObject searchedUser =  UserFileHandler.findUserByUsername(username);
        if (searchedUser != null){
            return false;
        }
        User user = new User(firstname, lastname, username, AES.encrypt(password, key));
        JSONObject userJson = UserToJsonMapper.map(user);
        UserFileHandler.addUser(userJson);
        return true;
    }

    @Override
    public boolean deleteAccount(User user) {
        return false;
    }

    @Override
    public User login(String username, String password) {
        JSONObject userJson = UserFileHandler.findUserByUsername(username);
        User user = JsonToUserMapper.map(userJson);
        if (user != null && password.equals(AES.decrypt(user.getPassword(), key))){
            return user;
        }
        return null;
    }

    @Override
    public boolean logout(User user) {
        return false;
    }

    @Override
    public boolean changePassword(User user, String newPassword) {
        return false;
    }
}

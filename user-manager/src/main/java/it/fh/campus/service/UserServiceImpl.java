package it.fh.campus.service;

import it.fh.campus.UserFileHandler;
import it.fh.campus.entities.User;
import it.fh.campus.mapper.JsonToUserMapper;
import org.json.simple.JSONObject;

public class UserServiceImpl implements UserService {

    @Override
    public User createAccount(String firstname, String lastname, String username, String password) {
        return null;
    }

    @Override
    public boolean deleteAccount(User user) {
        return false;
    }

    @Override
    public User login(String username, String password) {
        JSONObject userJson = UserFileHandler.findUserByUsernameAndPassword(username, password);
        return JsonToUserMapper.map(userJson);
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

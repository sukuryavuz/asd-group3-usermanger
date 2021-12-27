package it.fh.campus.service;

import it.fh.campus.utilities.Rijndael;
import it.fh.campus.utilities.UserFileHandler;
import it.fh.campus.entities.User;
import it.fh.campus.mapper.JsonToUserMapper;
import it.fh.campus.mapper.UserToJsonMapper;
import org.json.simple.JSONObject;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    @Override
    public void createAccount(String firstname, String lastname, String username, String password) {
        UserFileHandler.addUser(UserToJsonMapper.map(new User(firstname, lastname, username, Rijndael.encrypt(password))));
    }

    @Override
    public boolean isUsernameUnique(String username) {
        return UserFileHandler.findUserByUsername(username).isEmpty();
    }

    @Override
    public void deleteAccount(User user) {
        UserFileHandler.removeUser(UserToJsonMapper.map(user));
    }

    @Override
    public Optional<User> login(String username, String password) {

        Optional<JSONObject> userJson = UserFileHandler.findUserByUsername(username);
        Optional<User> user;
        if (userJson.isPresent()) {
            user = JsonToUserMapper.map(userJson.get());

            if (user.isPresent() && password.equals(Rijndael.decrypt(user.get().getPassword()))) {
                return user;
            }
        }
        return Optional.empty();
    }

    @Override
    public void changePassword(User user, String newPassword) {
        UserFileHandler.removeUser(UserToJsonMapper.map(user));
        user.setPassword(Rijndael.encrypt(newPassword));
        UserFileHandler.addUser(UserToJsonMapper.map(user));
    }

}

package it.fh.campus.mapper;

import it.fh.campus.entities.User;
import org.json.simple.JSONObject;

import java.util.Optional;

public class JsonToUserMapper {

    private static final String FIRST_NAME = "firstname";
    private static final String LAST_NAME = "lastname";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private JsonToUserMapper() { throw new IllegalStateException("JsonToUserMapper class");}

    public static Optional<User> map(JSONObject jsonObject){
        if (jsonObject == null){
            return Optional.empty();
        }
        return Optional.of(new User(
                (String) jsonObject.get(FIRST_NAME),
                (String) jsonObject.get(LAST_NAME),
                (String) jsonObject.get(USERNAME),
                (String) jsonObject.get(PASSWORD)));
    }
}

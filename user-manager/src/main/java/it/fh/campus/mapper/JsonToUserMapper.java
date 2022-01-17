package it.fh.campus.mapper;

import it.fh.campus.entities.User;
import org.json.simple.JSONObject;

import java.util.Optional;

public class JsonToUserMapper {

    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String USERNAME = "userName";
    public static final String PASSWORD = "password";

    private JsonToUserMapper() {
        throw new IllegalStateException("Mapper class");
    }

    public static Optional<User> map(Optional<JSONObject> jsonObject) {
        if (jsonObject.isPresent()) {
            JSONObject jsonUser = jsonObject.get();
            return Optional.of(new User((String) jsonUser.get(FIRST_NAME), (String) jsonUser.get(LAST_NAME), (String) jsonUser.get(USERNAME), (String) jsonUser.get(PASSWORD)));
        }
        return Optional.empty();
    }
}

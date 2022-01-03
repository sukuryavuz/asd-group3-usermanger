package it.fh.campus.mapper;

import it.fh.campus.entities.User;
import org.json.simple.JSONObject;

public class UserToJsonMapper {

    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    private UserToJsonMapper() { throw new IllegalStateException("UserToJsonMapper class");}

    public static JSONObject map(User user){
        if (user == null){
            return new JSONObject();
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(FIRST_NAME, user.getFirstName());
        jsonObject.put(LAST_NAME, user.getLastName());
        jsonObject.put(USERNAME, user.getUsername());
        jsonObject.put(PASSWORD, user.getPassword());
        return jsonObject;
    }
}

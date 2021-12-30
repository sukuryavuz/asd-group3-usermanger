package it.fh.campus.mapper;

import it.fh.campus.entities.User;
import org.json.simple.JSONObject;

public class UserToJsonMapper {

    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String USERNAME = "userName";
    public static final String PASSWORD = "password";

    private UserToJsonMapper() {
        throw new IllegalStateException("Mapper class");
    }

    public static JSONObject map(User user){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(FIRST_NAME, user.getFirstName());
        jsonObject.put(LAST_NAME, user.getLastName());
        jsonObject.put(USERNAME, user.getUserName());
        jsonObject.put(PASSWORD, user.getPassword());
        return jsonObject;
    }
}

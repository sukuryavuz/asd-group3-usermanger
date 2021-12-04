package it.fh.campus.mapper;

import it.fh.campus.entities.User;
import org.json.simple.JSONObject;

public class UserToJsonMapper {

    public static JSONObject map(User user){
        if (user == null){
            return null;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstname", user.getFirstname());
        jsonObject.put("lastname", user.getLastname());
        jsonObject.put("username", user.getUsername());
        jsonObject.put("password", user.getPassword());
        return jsonObject;
    }
}

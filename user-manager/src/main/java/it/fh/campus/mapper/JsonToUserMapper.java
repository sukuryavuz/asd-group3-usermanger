package it.fh.campus.mapper;

import it.fh.campus.entities.User;
import org.json.simple.JSONObject;

public class JsonToUserMapper {

    public static User map(JSONObject jsonObject){
        if (jsonObject == null){
            return null;
        }
        User user = new User();
        user.setFirstname((String) jsonObject.get("firstname"));
        user.setLastname((String) jsonObject.get("lastname"));
        user.setUsername((String) jsonObject.get("username"));
        user.setPassword((String) jsonObject.get("password"));
        return user;
    }
}

package it.fh.campus.mapper;

import it.fh.campus.entities.User;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test class for class JsonToUserMapper")
class JsonToUserMapperTest {

    private final String FIRST_NAME_KEY = "firstname";
    private final String FIRST_NAME_VALUE = "Max";
    private final String LAST_NAME_KEY = "lastname";
    private final String LAST_NAME_VALUE = "Mastermunn";
    private final String USERNAME_KEY = "username";
    private final String USERNAME_VALUE = "maxmustermann";
    private final String PASSWORD_KEY = "password";
    private final String PASSWORD_VALUE = "max123";

    private Optional<User> user;

    @Test
    void mapTest(){
        //Arrange
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(FIRST_NAME_KEY, FIRST_NAME_VALUE);
        jsonObject.put(LAST_NAME_KEY, LAST_NAME_VALUE);
        jsonObject.put(USERNAME_KEY, USERNAME_VALUE);
        jsonObject.put(PASSWORD_KEY, PASSWORD_VALUE);

        //Act
        user = JsonToUserMapper.map(jsonObject);

        //Assert
        assertAll(()->{
            assertEquals(FIRST_NAME_VALUE,user.get().getFirstname());
            assertEquals(LAST_NAME_VALUE, user.get().getLastname());
            assertEquals(USERNAME_VALUE, user.get().getUsername());
            assertEquals(PASSWORD_VALUE, user.get().getPassword());
        });
    }

    @Test
    void jsonObjectIsNullTest(){
        //Arrange

        //Act
        user = JsonToUserMapper.map(null);

        //Assert
        assertTrue(user.isEmpty());
    }
}

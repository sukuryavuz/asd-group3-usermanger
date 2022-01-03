package it.fh.campus.mapper;

import it.fh.campus.entities.User;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test class for class JsonToUserMapper")
class JsonToUserMapperTest {

    private static final String FIRST_NAME_KEY = "firstName";
    private static final String FIRST_NAME_VALUE = "Max";
    private static final String LAST_NAME_KEY = "lastName";
    private static final String LAST_NAME_VALUE = "Mastermunn";
    private static final String USERNAME_KEY = "username";
    private static final String USERNAME_VALUE = "maxmustermann";
    private static final String PASSWORD_KEY = "password";
    private static final String PASSWORD_VALUE = "max123";

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
            assertEquals(FIRST_NAME_VALUE,user.get().getFirstName());
            assertEquals(LAST_NAME_VALUE, user.get().getLastName());
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

package it.fh.campus.mapper;

import it.fh.campus.entities.User;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test class for class UserToJsonMapper")
class UserToJsonMapperTest {

    private static final String FIRST_NAME_KEY = "firstName";
    private static final String FIRST_NAME_VALUE = "Max";
    private static final String LAST_NAME_KEY = "lastName";
    private static final String LAST_NAME_VALUE = "Mastermunn";
    private static final String USERNAME_KEY = "username";
    private static final String USERNAME_VALUE = "maxmustermann";
    private static final String PASSWORD_KEY = "password";
    private static final String PASSWORD_VALUE = "max123";

    private JSONObject jsonObject;

    @Test
    void mapTest(){
        //Arrange
        User user = new User(FIRST_NAME_VALUE, LAST_NAME_VALUE, USERNAME_VALUE, PASSWORD_VALUE);

        //Act
        jsonObject = UserToJsonMapper.map(user);

        //Assert
        assertAll(()->{
            assertEquals(FIRST_NAME_VALUE, jsonObject.get(FIRST_NAME_KEY));
            assertEquals(LAST_NAME_VALUE, jsonObject.get(LAST_NAME_KEY));
            assertEquals(USERNAME_VALUE, jsonObject.get(USERNAME_KEY));
            assertEquals(PASSWORD_VALUE, jsonObject.get(PASSWORD_KEY));
        });
    }

    @Test
    void userIsNullTest(){
        //Arrange

        //Act
        jsonObject = UserToJsonMapper.map(null);

        //Assert
        assertTrue(jsonObject.isEmpty());
    }

}

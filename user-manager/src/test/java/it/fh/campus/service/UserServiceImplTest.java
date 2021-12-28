package it.fh.campus.service;

import it.fh.campus.entities.User;
import it.fh.campus.utilities.Rijndael;
import it.fh.campus.utilities.UserFileHandler;
import org.json.simple.JSONObject;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test class for class UserServiceImpl")
class UserServiceImplTest {

    private static final String FILE_PATH = "src/test/resources/userFileTest.json";

    private UserServiceImpl service;

    @BeforeEach
    void setUp(){
        UserFileHandler.initParser(FILE_PATH);
        service = new UserServiceImpl();
    }

    @Ignore("Testcase already available -> UserFileHandlerTest")
    void createAccountTest(){

    }

    @Test
    void isUsernameUniqueTest(){
        //Act
        String username = "R!p8W!8*2!Z88#82s2";
        boolean result;

        //Arrange
        result = service.isUsernameUnique(username);

        //Assert
        assertTrue(result);
    }

    @Test
    void isUsernameNotUniqueTest(){
        //Act
        String username = "Michael";
        boolean result;

        //Arrange
        result = service.isUsernameUnique(username);

        //Assert
        assertFalse(result);
    }

    @Ignore("Testcase already available -> UserFileHandlerTest")
    void deleteAccountTest(){

    }

    @Test
    void loginTest(){
        //Act
        JSONObject user = (JSONObject) UserFileHandler.getListOfUser().get(0);

        Optional<User> expectedUser;
        String username = (String) user.get("username");
        String password = (String) user.get("password");

        //Arrange
        expectedUser = service.login(username,Rijndael.decrypt(password));

        //Assert
        assertEquals(username,expectedUser.orElseThrow().getUsername());
    }

    @Test
    void loginFailTest(){
        //Act
        Optional<User> user;
        String username = "j+lbKrzhMUBDuNm68hx28w==";
        String password = "j+lbKrzhMUBDuNm68hx28w==";

        //Arrange
        user = service.login(username,Rijndael.decrypt(password));

        //Assert
        assertTrue(user.isEmpty());
    }

    @Test
    void changePasswordTest(){
        //Act
        JSONObject user = (JSONObject) UserFileHandler.getListOfUser().get(0);

        String username = (String) user.get("username");
        String oldPassword = (String) user.get("password");
        String newPassword = String.valueOf((Math.random() * (1000 - 1 + 1) + 1));
        Optional<User> oldUser = service.login(username, Rijndael.decrypt(oldPassword));

        //Arrange
        service.changePassword(oldUser.orElseThrow(), Rijndael.encrypt(newPassword));
        Optional<User> newUser = service.login(username, Rijndael.encrypt(newPassword));

        //Assert
        assertEquals(Rijndael.encrypt(newPassword), Rijndael.decrypt(newUser.orElseThrow().getPassword()));
    }

}

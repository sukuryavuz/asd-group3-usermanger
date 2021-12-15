package it.fh.campus;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test class for class UserFileHandler")
class UserFileHandlerTest {

    private final String FILE_PATH = "src/test/resources/userFileTest.json";

    @BeforeEach
    void setUp() {
        UserFileHandler.initParser(FILE_PATH);
    }

    @Test
    @DisplayName("Test method - Find a user with username in json file")
    void findUserByUsernameTest() {
        //Arrange
        JSONObject result;
        //Act
        result = UserFileHandler.findUserByUsername("Michael");
        //Assert
        assertEquals("Schneider", result.get("lastname"));
    }

    @Test
    @DisplayName("Test method - Add a user to json file")
    void addUserTest(){
        //Arrange
        JSONObject result = new JSONObject();
        result.put("firstname", "Lukas");
        result.put("lastname", "Mustermann");
        result.put("username", "lukasm");
        result.put("password", "434dsd2d");
        //Act
        UserFileHandler.addUser(result);
        JSONObject foundUser = UserFileHandler.findUserByUsername("lukasm");
        //Assert
        assertEquals(foundUser, result);
    }

    @Test
    @DisplayName("Test method - Remove a user from json file")
    void removeUserTest() {
        //Arrange
        int oldSize = UserFileHandler.size();
        JSONObject result = UserFileHandler.findUserByUsername("lukasm");
        //Act
        UserFileHandler.removeUser(result);
        int newSize = UserFileHandler.size();
        //Assert
        assertEquals(oldSize,newSize + 1);
    }
}

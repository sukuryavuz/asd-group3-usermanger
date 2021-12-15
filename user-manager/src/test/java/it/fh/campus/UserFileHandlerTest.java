package it.fh.campus;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Test class for class UserFileHandler")
class UserFileHandlerTest {

    private static final String FILE_PATH = "src/test/resources/userFileTest.json";

    @BeforeEach
    void setUp() {
        UserFileHandler.initParser(FILE_PATH);
    }

    @Test
    void findUserByUsernameTest() {
        //Arrange
        JSONObject result;
        //Act
        result = UserFileHandler.findUserByUsername("Michael");
        //Assert
        assertEquals("Schneider", result.get("lastname"));
    }

    @Test
    void addUserTest() throws IOException {
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
    void removeUserTest() throws IOException {
        //Arrange
        int oldSize = UserFileHandler.size();
        JSONObject result = UserFileHandler.findUserByUsername("lukasm");
        //Act
        UserFileHandler.removeUser(result);
        int newSize = UserFileHandler.size();
        //Assert
        assertTrue(oldSize == newSize + 1);
    }
}

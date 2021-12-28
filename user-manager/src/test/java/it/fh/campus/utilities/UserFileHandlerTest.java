package it.fh.campus.utilities;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Test class for class UserFileHandler")
class UserFileHandlerTest {

    private static final String FILE_PATH = "src/test/resources/userFileTest.json";

    @BeforeEach
    void setUp() {
        UserFileHandler.initParser(FILE_PATH);
    }

    @Test
    @Order(2)
    @DisplayName("Test method - Find a user with username in json file")
    void findUserByUsernameTest() {
        //Arrange
        JSONObject result;
        //Act
        result = UserFileHandler.findUserByUsername("lukasm").orElseThrow();
        //Assert
        assertEquals("Mustermann", result.get("lastName"));
    }

    @Test
    @Order(1)
    @DisplayName("Test method - Add a user to json file")
    void addUserTest(){
        //Arrange
        JSONObject result = new JSONObject();
        result.put("firstName", "Lukas");
        result.put("lastName", "Mustermann");
        result.put("username", "lukasm");
        result.put("password", "434dsd2d");
        //Act
        UserFileHandler.addUser(result);
        JSONObject foundUser = UserFileHandler.findUserByUsername("lukasm").orElseThrow();
        //Assert
        assertEquals(foundUser, result);
    }

    @Test
    @Order(3)
    @DisplayName("Test method - Remove a user from json file")
    void removeUserTest() {
        //Arrange
        int oldSize = UserFileHandler.size();
        JSONObject result = UserFileHandler.findUserByUsername("lukasm").orElseThrow();
        //Act
        UserFileHandler.removeUser(result);
        int newSize = UserFileHandler.size();
        //Assert
        assertEquals(oldSize,newSize + 1);
    }
}

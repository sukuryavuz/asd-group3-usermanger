import it.fh.campus.Init;
import it.fh.campus.Rijndael;
import it.fh.campus.UserFileHandler;
import it.fh.campus.entities.User;
import it.fh.campus.mapper.JsonToUserMapper;
import it.fh.campus.service.UserServiceImpl;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static it.fh.campus.Init.initializeUserManager;
import static it.fh.campus.UserFileHandler.findUserByUsername;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserManagerTest {
    UserServiceImpl userService = new UserServiceImpl();
    User testUser = new User ("TestUser", "TestUser", "TestUser", Rijndael.encrypt("TestUser", Init.getKey()));

    @BeforeAll
     static void init() {
        initializeUserManager();
    }

    @BeforeEach
     void createUser() throws IOException {
        userService.createAccount(testUser.getFirstname(), testUser.getLastname(), testUser.getUsername(), Rijndael.decrypt(testUser.getPassword(), Init.getKey()));
        JSONObject userJson = findUserByUsername("TestUser");
        User user = JsonToUserMapper.map(userJson);
        assertEquals("TestUser", user.getFirstname());
        assertEquals("TestUser", user.getLastname());
        assertEquals("TestUser", user.getUsername());
    }

    @AfterEach
    void deleteAccount() throws IOException {
        JSONObject userJson = findUserByUsername("TestUser");
        User user = JsonToUserMapper.map(userJson);
        userService.deleteAccount(user);
    }


    @Test
    void testCreateAccount () throws IOException {
        User newUser = new User ("NewFirstName", "NewLastName", "NewUserName", Rijndael.encrypt("NewPassword", Init.getKey()));
        userService.createAccount(newUser.getFirstname(), newUser.getLastname(), newUser.getUsername(), Rijndael.decrypt(newUser.getPassword(), Init.getKey()));
        JSONObject userJson = findUserByUsername("NewUserName");
        User user = JsonToUserMapper.map(userJson);
        assertEquals("NewFirstName", user.getFirstname());
        assertEquals("NewLastName", user.getLastname());
        assertEquals("NewUserName", user.getUsername());
    }


    @Test
    void testUsernameNotUnique () {
        boolean username = userService.isUsernameUnique(testUser.getUsername());
        assertEquals(username, false);
    }

    @Test
    void testUsernameUnique () {
        assertEquals(userService.isUsernameUnique("neuerName08173"), true);
    }

    @Test
    void testLoginSuccessful () {
        User user = userService.login(testUser.getUsername(), Rijndael.decrypt(testUser.getPassword(), Init.getKey()));
        assertEquals(user.getFirstname(), testUser.getFirstname());
        assertEquals(user.getLastname(), testUser.getLastname());
    }

    @Test
    void testChangePasswordSuccessful() throws IOException {
        String newPassword = "newPassword";
        userService.changePassword(testUser, newPassword);

        User user = userService.login(testUser.getUsername(), Rijndael.decrypt(testUser.getPassword(), Init.getKey()));
        assertEquals("TestUser", testUser.getFirstname());
        assertEquals("TestUser", testUser.getLastname());
    }

    @Test
    void testDeleteAccount() throws IOException {
        JSONObject userJson = findUserByUsername("NewUserName");
        User user = JsonToUserMapper.map(userJson);
        userService.deleteAccount(user);

        assertEquals(userService.isUsernameUnique("NewUserName"), true);

    }
}

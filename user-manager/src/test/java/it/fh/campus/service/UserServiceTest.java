package it.fh.campus.service;

import it.fh.campus.entities.User;
import it.fh.campus.exceptions.UserNameNotUniqueException;
import it.fh.campus.exceptions.UserNameOrPasswordNotCorrectException;
import it.fh.campus.utils.UserFileHandler;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

class UserServiceTest {

    private static final String FILE_PATH = "src/test/resources/userFileTest.json";

    public static final String CONST_FIRST_NAME = "firstName";
    public static final String CONST_LAST_NAME = "lastName";
    public static final String CONST_USERNAME = "userName";
    public static final String CONST_PASSWORD = "password";

    private static final String TEST_USER_STIPI_FIRST_NAME = "Michael";
    private static final String TEST_USER_STIPI_LAST_NAME = "Stipsits";
    private static final String TEST_USER_STIPI_USERNAME = "Stipi";
    private static final String TEST_USER_STIPI_PASSWORD_PLAIN = "12345";
    private static final String TEST_USER_STIPI_PASSWORD_ENCRYPTED = "zRaNireiGG1bPAYCevdp1w==";

    public static final String UNIQUE_USERNAME = "Unique";
    private static final String TEST_USER_ANDY_USERNAME = "Andy";
    private static final String TEST_USER_ANDY_PASSWORD_PLAIN = "001";

    private final UserService userService = new UserServiceImpl();

    @BeforeEach
    void init() {
        createTestFile();
        UserFileHandler.setFilePath(FILE_PATH);
    }

    @Test
    void testCreateAccount() {
        User user = null;
        try {
            user = userService.createAccount(TEST_USER_STIPI_FIRST_NAME, TEST_USER_STIPI_LAST_NAME, TEST_USER_STIPI_USERNAME, TEST_USER_STIPI_PASSWORD_PLAIN);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        Assertions.assertNotNull(user);
        Assertions.assertEquals(TEST_USER_STIPI_FIRST_NAME, user.getFirstName());
        Assertions.assertEquals(TEST_USER_STIPI_LAST_NAME, user.getLastName());
        Assertions.assertEquals(TEST_USER_STIPI_USERNAME, user.getUserName());
        Assertions.assertEquals(TEST_USER_STIPI_PASSWORD_ENCRYPTED, user.getPassword());
    }

    @Test
    void testCheckUserNameUniqueTrue() {
        Assertions.assertDoesNotThrow(() -> userService.checkUserNameUnique(UNIQUE_USERNAME));
    }

    @Test
    void testCheckUserNameUniqueFalse() {
        Assertions.assertThrows(UserNameNotUniqueException.class, () -> userService.checkUserNameUnique(TEST_USER_ANDY_USERNAME));
    }

    @Test
    void deleteAccount() {
        try {
            User user = userService.createAccount(TEST_USER_STIPI_FIRST_NAME, TEST_USER_STIPI_LAST_NAME, TEST_USER_STIPI_USERNAME, TEST_USER_STIPI_PASSWORD_PLAIN);
            Assertions.assertThrows(UserNameNotUniqueException.class, () -> userService.checkUserNameUnique(TEST_USER_STIPI_USERNAME));
            userService.deleteAccount(user);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        Assertions.assertDoesNotThrow(() -> userService.checkUserNameUnique(TEST_USER_STIPI_USERNAME));
    }

    @Test
    void testLoginSuccessful() {
        Assertions.assertDoesNotThrow(() -> userService.login(TEST_USER_ANDY_USERNAME, TEST_USER_ANDY_PASSWORD_PLAIN));
    }

    @Test
    void testLoginNotSuccessful() {
        Assertions.assertThrows(UserNameOrPasswordNotCorrectException.class, () -> userService.login(TEST_USER_ANDY_USERNAME, "002"));
    }

    @Test
    void testChangePassword() throws IOException, ParseException, UserNameOrPasswordNotCorrectException {
       User user;
        try {
            user = userService.login(TEST_USER_ANDY_USERNAME, TEST_USER_ANDY_PASSWORD_PLAIN);
        } catch (UserNameOrPasswordNotCorrectException | IOException | ParseException e) {
            throw e;
        }
        userService.changePassword(user, "002");
        Assertions.assertThrows(UserNameOrPasswordNotCorrectException.class, () -> userService.login(TEST_USER_ANDY_USERNAME, TEST_USER_ANDY_PASSWORD_PLAIN));
    }

    private void createTestFile() {
        JSONArray users = createUsers();
        try (FileOutputStream fos = new FileOutputStream(FILE_PATH);) {
            fos.write(users.toJSONString().getBytes(StandardCharsets.UTF_8));
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JSONArray createUsers() {
        JSONArray users = new JSONArray();
        JSONObject testUser1 = new JSONObject();
        testUser1.put(CONST_FIRST_NAME, "Andreas");
        testUser1.put(CONST_LAST_NAME, "Test1");
        testUser1.put(CONST_USERNAME, "Andy");
        testUser1.put(CONST_PASSWORD, "eGUUdZuaHUpcnMHwnL2w7g==");
        users.add(testUser1);

        JSONObject testUser2 = new JSONObject();
        testUser2.put(CONST_FIRST_NAME, "Berta");
        testUser2.put(CONST_LAST_NAME, "Test2");
        testUser2.put(CONST_USERNAME, "Berta");
        testUser2.put(CONST_PASSWORD, "USprCjCrGYQVZ8k2l2UTm5c7H+WdbD1mV7F9t5ocUFo=");
        users.add(testUser2);

        JSONObject testUser3 = new JSONObject();
        testUser3.put(CONST_FIRST_NAME, "Claudia");
        testUser3.put(CONST_LAST_NAME, "Test2");
        testUser3.put(CONST_USERNAME, "Claudia");
        testUser3.put(CONST_PASSWORD, "YTAPstLQNHLQDMA8dxTkZw==");
        users.add(testUser3);
        return users;
    }
}
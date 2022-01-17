package it.fh.campus.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

class UserFileHandlerTest {

    private static final String FILE_PATH = "src/test/resources/userFileTest.json";

    public static final String CONST_FIRST_NAME = "firstName";
    public static final String CONST_LAST_NAME = "lastName";
    public static final String CONST_USERNAME = "userName";
    public static final String CONST_PASSWORD = "password";

    private static final String TEST_USER_STIPI_FIRST_NAME = "Michael";
    private static final String TEST_USER_STIPI_LAST_NAME = "Stipsits";
    private static final String TEST_USER_STIPI_USERNAME = "Stipi";
    private static final String TEST_USER_STIPI_PASSWORD_ENCRYPTED = "zRaNireiGG1bPAYCevdp1w==";

    public static final String UNIQUE_USERNAME = "Unique";

    private static final String TEST_USER_ANDY_FIRST_NAME = "Andreas";
    private static final String TEST_USER_ANDY_LAST_NAME = "Test1";
    private static final String TEST_USER_ANDY_USERNAME = "Andy";
    private static final String TEST_USER_ANDY_PASSWORD_ENCRYPTED = "eGUUdZuaHUpcnMHwnL2w7g==";

    @BeforeEach
    void init() {
        createTestFile();
        UserFileHandler.setFilePath(FILE_PATH);
    }

    @Test
    void testFindUserByUserNameUserFound() throws IOException, ParseException {
        Optional<JSONObject> userJson = UserFileHandler.findUserByUsername(TEST_USER_ANDY_USERNAME);
        Assertions.assertTrue(userJson.isPresent());
    }

    @Test
    void testFindUserByUserNameNoUserFound() throws IOException, ParseException {
        Optional<JSONObject> userJson = UserFileHandler.findUserByUsername(UNIQUE_USERNAME);
        Assertions.assertTrue(userJson.isEmpty());
    }

    @Test
    void testAddUser() throws IOException, ParseException {
        JSONObject user = new JSONObject();
        user.put(CONST_FIRST_NAME, TEST_USER_STIPI_FIRST_NAME);
        user.put(CONST_LAST_NAME, TEST_USER_STIPI_LAST_NAME);
        user.put(CONST_USERNAME, TEST_USER_STIPI_USERNAME);
        user.put(CONST_PASSWORD, TEST_USER_STIPI_PASSWORD_ENCRYPTED);

        Assertions.assertTrue(UserFileHandler.findUserByUsername(TEST_USER_STIPI_USERNAME).isEmpty());
        UserFileHandler.addUser(user);
        Assertions.assertTrue(UserFileHandler.findUserByUsername(TEST_USER_STIPI_USERNAME).isPresent());
    }

    @Test
    void testRemoveUser() throws IOException, ParseException {
        JSONObject user = new JSONObject();
        user.put(CONST_FIRST_NAME, TEST_USER_ANDY_FIRST_NAME);
        user.put(CONST_LAST_NAME, TEST_USER_ANDY_LAST_NAME);
        user.put(CONST_USERNAME, TEST_USER_ANDY_USERNAME);
        user.put(CONST_PASSWORD, TEST_USER_ANDY_PASSWORD_ENCRYPTED);

        Assertions.assertTrue(UserFileHandler.findUserByUsername(TEST_USER_ANDY_USERNAME).isPresent());
        UserFileHandler.removeUser(user);
        Assertions.assertTrue(UserFileHandler.findUserByUsername(TEST_USER_ANDY_USERNAME).isEmpty());
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
package it.fh.campus.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class UserFileHandler {

    private static final String USERNAME = "userName";
    private static String FILE_PATH = "";

    private UserFileHandler() {
        throw new IllegalStateException("Utility class");
    }

    private static JSONArray getUserList() throws IOException, ParseException {
        return (JSONArray) new JSONParser().parse(new FileReader(FILE_PATH));
    }

    public static Optional<JSONObject> findUserByUsername(String userName) throws IOException, ParseException {
        JSONArray userList = getUserList();
        for (Object jsonObject : userList) {
            JSONObject user = (JSONObject) jsonObject;
            if (userName.equals(user.get(USERNAME))) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public static void addUser(JSONObject user) throws IOException, ParseException {
        JSONArray userList = getUserList();
        userList.add(user);
        writeToJSONFile(userList);
    }

    public static void removeUser(JSONObject user) throws IOException, ParseException {
        JSONArray userList = getUserList();
        userList.remove(user);
        writeToJSONFile(userList);
    }

    private static void writeToJSONFile(JSONArray fileContent) throws IOException {
        try (FileWriter file = new FileWriter(FILE_PATH)) {
            file.write(fileContent.toJSONString());
            file.flush();
        }
    }

    public static void setFilePath(String filePath){
        FILE_PATH = filePath;
    }
}

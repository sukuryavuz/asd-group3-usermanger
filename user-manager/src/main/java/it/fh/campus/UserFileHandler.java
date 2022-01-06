package it.fh.campus;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class UserFileHandler {

    private static final String FILE_PATH = "user-manager/src/main/resources/userFile.json";
    //private static final String FILE_PATH = "C:\\Users\\Zorana FH\\Desktop\\Master_SDE\\Projekte\\ASD\\user-manager-git\\user-manager\\src\\main\\resources\\userFile.json";

    private static JSONArray jsonUserArray;

    public static void initParser() {
        try {
            Object userFileObject = new JSONParser().parse(new FileReader(FILE_PATH));
            jsonUserArray = (JSONArray) userFileObject;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject findUserByUsername(String username) {
        for (Object userObject : jsonUserArray) {
            JSONObject user = (JSONObject) userObject;
            if (username.equals(user.get("username"))) {
                return user;
            }
        }
        return null;
    }

    public static void addUser(JSONObject user) throws IOException {
        jsonUserArray.add(user);
        FileWriter userFile = new FileWriter(FILE_PATH);
        userFile.write(jsonUserArray.toJSONString());
        userFile.flush();
        userFile.close();
    }

    public static void removeUser(JSONObject user) throws IOException {
        jsonUserArray.remove(user);
        FileWriter userFile = new FileWriter(FILE_PATH);
        userFile.write(jsonUserArray.toJSONString());
        userFile.flush();
        userFile.close();
    }
}

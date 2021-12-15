package it.fh.campus;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class UserFileHandler {

    private static String pathToFile;

    private static JSONArray listOfUser;

    private UserFileHandler(){
        throw new IllegalStateException("UserFileHandler class");
    }

    public static void initParser(String path) {
        try {
            pathToFile = path;
            Object object = new JSONParser().parse(new FileReader(path));
            listOfUser = (JSONArray) object;
        } catch (IOException | ParseException exception) {
            exception.printStackTrace();
        }
    }

    public static JSONObject findUserByUsername(String username) {
        for (Object object : listOfUser) {
            JSONObject user = (JSONObject) object;
            if (username.equals(user.get("username"))) {
                return user;
            }
        }
        return (JSONObject) List.of();
    }

    public static void addUser(JSONObject user) {
        listOfUser.add(user);
        writeToFile();
    }

    public static void removeUser(JSONObject user) {
        listOfUser.remove(user);
        writeToFile();
    }

    public static int size(){
        if (listOfUser.isEmpty()){
            return 0;
        }
        return listOfUser.size();
    }

    public static void writeToFile(){
        try (FileWriter file = new FileWriter(pathToFile)){
            file.write(listOfUser.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

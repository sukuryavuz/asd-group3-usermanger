package it.fh.campus.utilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

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

    public static Optional<JSONObject> findUserByUsername(String username) {
        for (Object object : listOfUser) {
            JSONObject user = (JSONObject) object;
            if (username.equals(user.get("username"))) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public static void addUser(JSONObject user) {
        listOfUser.add(user);
        writeToFile();
    }

    public static void removeUser(JSONObject user) {
        listOfUser.remove(user);
        writeToFile();
    }

    private static void writeToFile(){
        try (FileWriter file = new FileWriter(pathToFile)){
            file.write(listOfUser.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int size(){
        return listOfUser.size();
    }

    public static JSONArray getListOfUser(){
        return listOfUser;
    }
}

package it.fh.campus;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class UserFileHandler {

    private static final String FILE_PATH = "user-manager/src/main/resources/userFile.json";

    private static JSONArray jo;

    public static void initParser() {
        try {
            Object obj = new JSONParser().parse(new FileReader(FILE_PATH));
            jo = (JSONArray) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    public static boolean IsUsernameValid(String username){
        Iterator i = jo.iterator();
        String key = Main.getKey();

        while(i.hasNext()) {
            JSONObject user = (JSONObject) i.next();
            String usrname = (String) user.get("username");
            if(usrname.equals(username)){
                System.out.println("Dieser Username existiert bereits!");
                return false;
            }
        } return true;
    }

    public static JSONObject findUserByUsernameAndPassword(String username, String password) {
        Iterator i = jo.iterator();
        String key = Main.getKey();

        while (i.hasNext()) {
            JSONObject user = (JSONObject) i.next();
            String usrname = (String) user.get("username");
            String pw = (String) user.get("password");
            String decryptedPw = AES.decrypt(pw, key);

            if (usrname.equals(username) && decryptedPw.equals(password)) {
                return user;
            }
        }
        return null;
    }

    public static JSONObject findUserByUsername(String username) {
        for (Object o : jo) {
            JSONObject user = (JSONObject) o;
            if (username.equals(user.get("username"))) {
                return user;
            }
        }
        return null;
    }

    public static void addUser(JSONObject user) throws IOException {
        jo.add(user);
        FileWriter file = new FileWriter(FILE_PATH);
        file.write(jo.toJSONString());
        file.flush();
        file.close();
    }
}

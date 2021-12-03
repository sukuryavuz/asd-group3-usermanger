package it.fh.campus;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;


public class UserFileHandler {

    static JSONArray jo;

    public static void initParser() {
        try {
            Object obj = new JSONParser().parse(new FileReader("user-manager/src/main/resources/userFile.json"));
            jo = (JSONArray) obj;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static boolean isCorrectUsernameAndPw(String username, String password) {
        Iterator i = jo.iterator();
        String key = Main.getKey();

        while (i.hasNext()) {
            JSONObject user = (JSONObject) i.next();
            String usrname = (String) user.get("username");
            String pw = (String) user.get("password");
            String decryptedPw = AES.decrypt(pw, key);

            if (usrname.equals(username) && decryptedPw.equals(password)) {
                return true;
            }
        }
        return false;
    }
}

package it.fh.campus;

import it.fh.campus.service.UserServiceImpl;

public class Init {

    static String key;

    public static void initializeUserManager() {

        key = "superSecret123";
        Rijndael.setKey(key);
        UserFileHandler.initParser();
    }

    public static String getKey() {
        return key;
    }
}

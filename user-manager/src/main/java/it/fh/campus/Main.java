package it.fh.campus;

import it.fh.campus.service.UserServiceImpl;

public class Main {
    static String key;

    public static void main(String[] args) {

        key = "superSecret123";

        AES.setKey(key);
        UserFileHandler.initParser();
        new ClGui(new UserServiceImpl());
    }

    public static String getKey() {
        return key;
    }
}

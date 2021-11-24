package it.fh.campus;

public class Main {
    static String key;

    public static void main(String[] args) {

        key = "superSecret123";

        AES.setKey(key);
        UserFileHandler.initParser();
        ClGui.startUserManager();
    }

    public static String getKey() {
        return key;
    }
}

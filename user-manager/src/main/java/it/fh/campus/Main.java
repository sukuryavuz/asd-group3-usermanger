package it.fh.campus;

import it.fh.campus.service.UserServiceImpl;

public class Main {

    private static final String FILE_PATH = "user-manager/src/main/resources/userFile.json";

    public static void main(String[] args) {

        Rijndael.setKey();
        UserFileHandler.initParser(FILE_PATH);
        new CommandLine(new UserServiceImpl());
    }

}

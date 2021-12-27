package it.fh.campus;

import it.fh.campus.service.UserServiceImpl;
import it.fh.campus.utilities.UserFileHandler;

public class Main {

    private static final String FILE_PATH = "user-manager/src/main/resources/userFile.json";

    public static void main(String[] args) {

        UserFileHandler.initParser(FILE_PATH);
        new CommandLine(new UserServiceImpl());
    }

}

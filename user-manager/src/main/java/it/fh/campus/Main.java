package it.fh.campus;

import it.fh.campus.service.UserServiceImpl;
import it.fh.campus.utils.UserFileHandler;

public class Main {

    public static void main(String[] args) {
        UserFileHandler.setFilePath("user-manager/src/main/resources/userFile.json");
        new CommandLine(new UserServiceImpl());
    }
}

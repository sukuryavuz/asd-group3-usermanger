package it.fh.campus;

import it.fh.campus.service.UserServiceImpl;

import static it.fh.campus.Init.initializeUserManager;

public class Main {
    public static void main(String[] args) {

      initializeUserManager();
      new CommandLine(new UserServiceImpl());
    }

}

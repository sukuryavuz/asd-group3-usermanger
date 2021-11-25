package it.fh.campus;

import it.fh.campus.entities.User;
import it.fh.campus.service.UserService;

import java.util.Scanner;

public class ClGui {

    private final UserService userService;

    public ClGui(UserService userService) {
        this.userService = userService;
        printStartPage();
    }

    private void printStartPage() {
        while (true) {
            System.out.println("Welcome to UserManager!");
            System.out.println("1 - Create Account");
            System.out.println("2 - Login");
            System.out.println("3 - Exit");
            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();
            switch (input) {
                case 1:
                    handleCreateAccount();
                    break;
                case 2:
                    handleLogin();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Please enter a valid number");
                    break;
            }
        }
    }

    private void printLoggedInPage(User user) {
        while (true) {
            System.out.println("Welcome " + user.getFirstname() + " " + user.getLastname() + "!");
            System.out.println("1 - Log Out");
            System.out.println("2 - Change Password");
            System.out.println("3 - Delete Account");
            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();
            switch (input) {
                case 1:
                    handleLogOut();
                    return;
                case 2:
                    handleChangePassword(user);
                    break;
                case 3:
                    handleDeleteAccount(user);
                    break;
                default:
                    System.out.println("Please enter a valid number");
                    break;
            }
        }
    }

    private void handleCreateAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your firstname: ");
        String firstname = scanner.nextLine();
        System.out.println("Please enter your lastname: ");
        String lastname = scanner.nextLine();
        System.out.println("Please enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Please enter your password: ");
        String password = scanner.nextLine();
        userService.createAccount(firstname, lastname, username, password);
    }

    private void handleLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter username: ");
        String username = scanner.nextLine();
        System.out.println("Please enter password: ");
        String password = scanner.nextLine();
        User user = userService.login(username, password);
        if (user != null) {
            printLoggedInPage(user);
        } else {
            System.out.println("Username or Password not correct");
        }
    }

    private void handleLogOut() {
        //TODO:
    }

    private void handleChangePassword(User user) {
        //TODO:
    }

    private void handleDeleteAccount(User user) {
        //TODO:
    }
}

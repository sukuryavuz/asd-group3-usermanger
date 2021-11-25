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

    public void printStartPage() {
        while (true) {
            System.out.println("Willkommen im UserManager!");
            System.out.println("1 - Account erstellen");
            System.out.println("2 - Login");
            System.out.println("3 - Beenden");
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
                    System.out.println("Bitte treffen Sie eine gültige Auswahl!");
                    break;
            }
        }
    }

    public void printLoggedInPage(User user) {
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
                    return;
                default:
                    System.out.println("Bitte treffen Sie eine gültige Auswahl!");
                    break;
            }
        }
    }

    public void handleCreateAccount() {
        Scanner scanner = new Scanner(System.in);

        boolean condition = true;
        while(condition) {
            System.out.println("Geben Sie Bitte einen Usernamen ein: ");
            String username = scanner.nextLine();

            if(UserFileHandler.IsUsernameValid(username)){
                System.out.println("Geben Sie Bitte Ihren Vornamen ein: ");
                String firstname = scanner.nextLine();

                System.out.println("Geben Sie Bitte Ihren Nachnamen ein: ");
                String lastname = scanner.nextLine();

                System.out.println("Geben Sie Bitte Ihr Passwort ein: ");
                String password = scanner.nextLine();
                condition = false;
            }
        }


    }

    private void handleLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte geben Sie Ihren Usernamen ein: ");
        String username = scanner.nextLine();
        System.out.println("Bitte geben Sie Ihr Passwort ein: ");
        String password = scanner.nextLine();
        User user = userService.login(username, password);
        if (user != null) {
            printLoggedInPage(user);
        } else {
            System.out.println("Username oder Passwort nicht korrekt!");
        }
    }

    private void handleLogOut() {
        //TODO:
    }

    private void handleChangePassword(User user) {
        //TODO:
    }

    public void handleDeleteAccount(User user) {
        //TODO:
    }
}

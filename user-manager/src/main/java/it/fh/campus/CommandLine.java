package it.fh.campus;

import it.fh.campus.entities.User;
import it.fh.campus.service.UserService;

import java.io.IOException;
import java.util.Scanner;

public class CommandLine {

    private int countLoginAttempts = 0;

    private final UserService userService;

    public CommandLine(UserService userService) {
        this.userService = userService;
        printStartPage();
    }

    private void printStartPage() {
        while (true) {
            System.out.println("""
                    Willkommen im UserManager!
                    1 - Account erstellen
                    2 - Login
                    3 - Beenden
                    """);
            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();
            switch (input) {
                case "1":
                    handleCreateAccount();
                    break;
                case "2":
                    handleLogin();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Bitte treffen Sie eine gültige Auswahl!");
                    break;
            }
        }
    }

    private void printLoggedInPage(User user) {
        while (true) {
            System.out.println("Willkommen " + user.getFirstname() + " " + user.getLastname() + "!");
            System.out.println("""
                    1 - Log out
                    2 - Passwort ändern
                    3 - Account löschen
                    """);
            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();
            switch (input) {
                case "1":
                    handleLogOut(user);
                    return;
                case "2":
                    handleChangePassword(user);
                    break;
                case "3":
                    handleDeleteAccount(user);
                    break;
                default:
                    System.out.println("Bitte treffen Sie eine gültige Auswahl!");
                    break;
            }
        }
    }

    private void handleCreateAccount() {
        Scanner scanner = new Scanner(System.in);
        String username;
        while (true) {
            System.out.println("Geben Sie Bitte einen Usernamen ein: ");
            username = scanner.nextLine();
            if (userService.isUsernameUnique(username)) {
                break;
            } else {
                System.out.println("Username bereits vergeben!");
            }
        }
        System.out.println("Geben Sie Bitte Ihren Vornamen ein: ");
        String firstname = scanner.nextLine();
        System.out.println("Geben Sie Bitte Ihren Nachnamen ein: ");
        String lastname = scanner.nextLine();
        System.out.println("Geben Sie Bitte Ihr Passwort ein: ");
        String password = scanner.nextLine();
        try {
            userService.createAccount(firstname, lastname, username, password);
            User user = userService.login(username, password);
            printLoggedInPage(user);
        } catch (IOException e) {
            System.out.println("Etwas ist schief gelaufen!");
        }
    }

    private void handleLogin() {
        while (countLoginAttempts < 3){
            printAttempt();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Bitte geben Sie Ihren Usernamen ein: ");
            String username = scanner.nextLine();
            System.out.println("Bitte geben Sie Ihr Passwort ein: ");
            String password = scanner.nextLine();
            User user = userService.login(username, password);
            if (user != null) {
                countLoginAttempts = 0;
                printLoggedInPage(user);
            } else {
                System.out.println("Username oder Passwort nicht korrekt!");
            }
        }
    }

    private void handleLogOut(User user) {
        userService.logout(user);
        printStartPage();
    }

    private void handleChangePassword(User user) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Bitte geben Sie ihr neues Passwort ein: ");
            String newPassword = scanner.next();
            System.out.println("Bitte geben Sie erneut ihr neues Passwort ein: ");
            String newPassword2 = scanner.next();
            if (newPassword.equals(newPassword2)) {
                userService.changePassword(user, newPassword);
                System.out.println("Ihr neues Passwort wurde erfolgreich aktualisiert! ");
            } else {
                System.out.println("Die Passwörter stimmen nicht überein ");
                printLoggedInPage(user);
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void handleDeleteAccount(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Möchten Sie Ihren Account wirklich löschen? (y oder n): ");
        while (true) {
            String inputDeleteAcc = scanner.next();
            switch (inputDeleteAcc) {
                case "y":
                    try {
                        userService.deleteAccount(user);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Ihr Account mit dem Usernamen " + user.getUsername() + " wurde erfolgreich gelöscht");
                    printStartPage();
                    break;
                case "n":
                    System.out.println("Ihr Account wird nicht gelöscht");
                    printLoggedInPage(user);
                    break;
                default:
                    System.out.println("Bitte tippen Sie 'y' oder 'n': ");
                    break;
            }
        }
    }

    private void printAttempt() {
        switch (countLoginAttempts) {
           // case 0 -> System.out.println("1.Versuch zum Einloggen");
            case 1 -> System.out.println("2.Versuch zum Einloggen");
            case 2 -> System.out.println("3.Versuch zum Einloggen");
        }
        countLoginAttempts++;

    }

}
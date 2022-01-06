package it.fh.campus;

import it.fh.campus.entities.User;
import it.fh.campus.service.UserService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Scanner;

public class CommandLine {

    private static final String WENT_WRONG_MESSAGE = "Etwas ist schief gelaufen!";
    private static final String CREATE_ACCOUNT = "1";
    private static final String LOGIN = "2";
    private static final String EXIT = "3";
    private static final String LOGOUT = "1";
    private static final String CHANGE_PASSWORD = "2";
    private static final String DELETE_ACCOUNT = "3";
    private static final String DELETE_ACCOUNT_YES = "j";
    private static final String DELETE_ACCOUNT_NO = "n";

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
            Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
            String input = scanner.next();
            switch (input) {
                case CREATE_ACCOUNT:
                    handleCreateAccount();
                    break;
                case LOGIN:
                    handleLogin();
                    break;
                case EXIT:
                    return;
                default:
                    System.out.println("Bitte treffen Sie eine gültige Auswahl!");
                    break;
            }
        }
    }

    private void printLoggedInPage(User user) {
        while (true) {
            System.out.println("Willkommen " + user.getFirstName() + " " + user.getLastName() + "!");
            System.out.println("""
                    1 - Log out
                    2 - Passwort ändern
                    3 - Account löschen
                    """);
            Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
            String input = scanner.next();
            switch (input) {
                case LOGOUT:
                    return;
                case CHANGE_PASSWORD:
                    handleChangePassword(user);
                    break;
                case DELETE_ACCOUNT:
                    if (handleDeleteAccount(user)) {
                        return;
                    }
                    break;
                default:
                    System.out.println("Bitte treffen Sie eine gültige Auswahl!");
                    break;
            }
        }
    }

    private void handleCreateAccount() {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        String username;
        while (true) {
            System.out.println("Geben Sie Bitte einen Usernamen ein: ");
            username = scanner.nextLine();
            if (userService.isUsernameUnique(username)) {
                break;
            } else {
                System.out.println("Username existiert bereits!");
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
            Optional<User> user = userService.login(username, password);
            printLoggedInPage(user.orElseThrow());
        } catch (IOException e) {
            System.out.println(WENT_WRONG_MESSAGE);
        }
    }

    private void handleLogin() {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        for (int countLoginAttempts = 0; countLoginAttempts < 3; countLoginAttempts++) {
            printLoginAttempt(countLoginAttempts);
            System.out.println("Bitte geben Sie Ihren Usernamen ein: ");
            String username = scanner.nextLine();
            System.out.println("Bitte geben Sie Ihr Passwort ein: ");
            String password = scanner.nextLine();
            Optional<User> user = userService.login(username, password);
            if (user.isPresent()) {
                printLoggedInPage(user.orElseThrow());
                break;
            } else {
                System.out.println("Username oder Passwort nicht korrekt!");
            }
        }
    }

    private void handleChangePassword(User user) {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        System.out.println("Bitte geben Sie ihr neues Passwort ein: ");
        String newPassword = scanner.next();
        System.out.println("Bitte geben Sie erneut ihr neues Passwort ein: ");
        String newPassword2 = scanner.next();
        if (newPassword.equals(newPassword2)) {
            try {
                userService.changePassword(user, newPassword);
            } catch (IOException e) {
                System.out.println(WENT_WRONG_MESSAGE);
            }
            System.out.println("Ihr neues Passwort wurde erfolgreich aktualisiert! ");
        } else {
            System.out.println("Kennwörter nicht gleich");
        }
    }

    private boolean handleDeleteAccount(User user) {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        System.out.println("Wollen Sie den Account wirklich löschen (j oder n): ");
        while (true) {
            String inputDeleteAcc = scanner.next();
            switch (inputDeleteAcc) {
                case DELETE_ACCOUNT_YES -> {
                    try {
                        userService.deleteAccount(user);
                    } catch (IOException e) {
                        System.out.println(WENT_WRONG_MESSAGE);
                    }
                    System.out.println("Ihr Account mit dem Usernamen " + user.getUsername() + " wurde erfolgreich gelöscht");
                    return true;
                }
                case DELETE_ACCOUNT_NO -> {
                    System.out.println("Ihr Account wird nicht gelöscht");
                    return false;
                }
                default -> System.out.println("Bitte tippen Sie 'j' oder 'n': ");
            }
        }
    }

    private void printLoginAttempt(int countLoginAttempts) {
        if (countLoginAttempts == 1) {
            System.out.println("2.Versuch zum Einloggen");
        } else {
            System.out.println("3.Versuch zum Einloggen");
        }
    }

}
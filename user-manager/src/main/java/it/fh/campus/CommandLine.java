package it.fh.campus;

import it.fh.campus.entities.User;
import it.fh.campus.exceptions.UserNameNotUniqueException;
import it.fh.campus.exceptions.UserNameOrPasswordNotCorrectException;
import it.fh.campus.service.UserService;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

public class CommandLine {

    public static final String CREATE_ACCOUNT_CASE = "1";
    public static final String LOGIN_CASE = "2";
    public static final String QUIT_CASE = "3";
    public static final String LOGOUT_CASE = "1";
    public static final String CHANGE_PASSWORD_CASE = "2";
    public static final String DELETE_ACCOUNT_CASE = "3";
    public static final String MSG_STH_WENT_WRONG = "Etwas ist schief gelaufen!";
    public static final String MSG_CHOOSE_VALID_SELECTION = "Bitte treffen Sie eine gültige Auswahl!";
    public static final String DELETE_ACCOUNT_JA = "j";
    public static final String DELETE_ACCOUNT_NEIN = "n";

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
                case CREATE_ACCOUNT_CASE:
                    handleCreateAccount();
                    break;
                case LOGIN_CASE:
                    handleLogin();
                    break;
                case QUIT_CASE:
                    return;
                default:
                    System.out.println(MSG_CHOOSE_VALID_SELECTION);
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
            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();
            switch (input) {
                case LOGOUT_CASE:
                    return;
                case CHANGE_PASSWORD_CASE:
                    handleChangePassword(user);
                    break;
                case DELETE_ACCOUNT_CASE:
                    handleDeleteAccount(user);
                    try {
                        userService.checkUserNameUnique(user.getUserName());
                    } catch (IOException | ParseException e) {
                        e.printStackTrace();
                    } catch (UserNameNotUniqueException e){
                        break;
                    }
                    return;
                default:
                    System.out.println(MSG_CHOOSE_VALID_SELECTION);
                    break;
            }
        }
    }

    private void handleCreateAccount() {
        Scanner scanner = new Scanner(System.in);
        String userName = getUniqueUserName();
        System.out.println("Geben Sie Bitte Ihren Vornamen ein: ");
        String firstName = scanner.nextLine();
        System.out.println("Geben Sie Bitte Ihren Nachnamen ein: ");
        String lastName = scanner.nextLine();
        System.out.println("Geben Sie Bitte Ihr Passwort ein: ");
        String password = scanner.nextLine();
        createAccount(userName, firstName, lastName, password);
    }

    private void createAccount(String userName, String firstName, String lastName, String password) {
        try {
            User user = userService.createAccount(firstName, lastName, userName, password);
            printLoggedInPage(user);
        } catch (IOException | ParseException e) {
            System.out.println(MSG_STH_WENT_WRONG);
        }
    }

    private String getUniqueUserName() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Geben Sie Bitte einen Usernamen ein: ");
            String username = scanner.nextLine();
            try {
                userService.checkUserNameUnique(username);
                return username;
            } catch (IOException | ParseException e) {
                System.out.println(MSG_STH_WENT_WRONG);
            } catch (UserNameNotUniqueException e) {
                System.out.println("Username existiert bereits!");
            }
        }
    }

    private void handleLogin() {
        Scanner scanner = new Scanner(System.in);
        for (int countLoginAttempts = 1; countLoginAttempts < 4; countLoginAttempts++) {
            System.out.println(countLoginAttempts + ". Versuch zum Einloggen");
            System.out.println("Bitte geben Sie Ihren Usernamen ein: ");
            String username = scanner.nextLine();
            System.out.println("Bitte geben Sie Ihr Passwort ein: ");
            String password = scanner.nextLine();
            try {
                printLoggedInPage(userService.login(username, password));
                return;
            } catch (UserNameOrPasswordNotCorrectException e) {
                System.out.println("Username oder Passwort nicht korrekt!");
            } catch (IOException | ParseException e) {
                System.out.println(MSG_STH_WENT_WRONG);
            }
        }

    }

    private void handleChangePassword(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte geben Sie ihr neues Passwort ein: ");
        String newPassword1 = scanner.next();
        System.out.println("Bitte geben Sie erneut ihr neues Passwort ein: ");
        String newPassword2 = scanner.next();
        if (newPassword1.equals(newPassword2)) {
            try {
                userService.changePassword(user, newPassword1);
            } catch (IOException | ParseException e) {
                System.out.println(MSG_STH_WENT_WRONG);
            }
            System.out.println("Ihr neues Passwort wurde erfolgreich aktualisiert! ");
        } else {
            System.out.println("Kennwörter nicht gleich");
        }
    }

    private void handleDeleteAccount(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wollen Sie den Account wirklich löschen (j oder n): ");
        while (true) {
            String inputDeleteAcc = scanner.next();
            switch (inputDeleteAcc) {
                case DELETE_ACCOUNT_JA:
                    try {
                        userService.deleteAccount(user);
                    } catch (IOException | ParseException e) {
                        System.out.println(MSG_STH_WENT_WRONG);
                    }
                    System.out.println("Ihr Account mit dem Usernamen " + user.getUserName() + " wurde erfolgreich gelöscht");
                    return;
                case DELETE_ACCOUNT_NEIN:
                    System.out.println("Ihr Account wird nicht gelöscht");
                    return;
                default:
                    System.out.println("Bitte tippen Sie 'j' oder 'n': ");
            }
        }
    }
}
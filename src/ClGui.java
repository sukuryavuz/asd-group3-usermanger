import java.util.Scanner;

public class ClGui {

    public static void startUserManager() {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Please enter username: ");
        String username = scanner.nextLine();
        System.out.println("Please enter password: ");
        String password = scanner.nextLine();


        if (UserFileHandler.isCorrectUsernameAndPw(username, password)) {
            System.out.println("Match found");
        } else System.out.println("No match!!");
    }
}


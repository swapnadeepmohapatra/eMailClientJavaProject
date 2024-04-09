import core.Account;
import service.AccountService;
import service.MailService;
import utils.SqlConnection;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    final static private AccountService accountService = new AccountService();
    final static private MailService mailService = new MailService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("Email Menu:");
            System.out.println("1. Login with existing account");
            System.out.println("2. Create new account");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    Account signedInAccount = accountService.loginAccount();
                    boolean isLoggedIn = signedInAccount != null;
                    while (isLoggedIn){
                        System.out.println("Email Menu:");
                        System.out.println("1. Send mail");
                        System.out.println("2. Show mails");
                        System.out.println("3. Logout");

                        System.out.print("Enter your choice: ");
                        int ch = scanner.nextInt();
                        scanner.nextLine();

                        switch (ch){
                            case 1:
                                mailService.sendMail(signedInAccount);
                                break;
                            case 2:
                                mailService.readMail(signedInAccount);
                                break;
                            case 3:
                                System.out.println("Logged out!");
                                isLoggedIn = false;
                                break;
                            default:
                                System.out.println("Invalid choice. Please enter a valid option.");
                        }
                    }
                    break;
                case 2:
                    accountService.createAccount();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
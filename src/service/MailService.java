package service;

import core.Account;
import core.Mail;
import database.Database;
import database.SqlDatabase;

import java.util.Scanner;

public class MailService implements interfaces.MailService {
    private static final AccountService accountService = new AccountService();
    private static final SqlDatabase database = new SqlDatabase();

    @Override
    public boolean sendMail(Account account) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter receiver");
        String receiver = sc.next();
        System.out.println("Enter subject");
        String subject = sc.next();
        System.out.println("Enter body");
        String body = sc.next();

        Mail mail = new Mail(account, accountService.searchAccount(receiver), subject, body);

        database.addMail(mail);

        return false;
    }

    @Override
    public boolean readMail(Account account) {
        for (Mail mail : database.getMailsFromUser(account.getUsername())) {
            System.out.println("Sender: " + mail.getSender().getName() + " <" + mail.getSender().getUsername() + ">");
            System.out.println("Receiver: " + mail.getRecipient().getName() + " <" + mail.getRecipient().getUsername() + ">");
            System.out.println("Subject: " + mail.getSubject());
            System.out.println("Body: " + mail.getBody());
            System.out.println("\n");
        }
        return false;
    }

    @Override
    public boolean showAllMailsByUser(String username) {
        for (Mail mail : database.getMailsFromUser(username)) {
            System.out.println("Sender: " + mail.getSender().getName() + " <" + mail.getSender().getUsername() + ">");
            System.out.println("Receiver: " + mail.getRecipient().getName() + " <" + mail.getRecipient().getUsername() + ">");
            System.out.println("Subject: " + mail.getSubject());
            System.out.println("Body: " + mail.getBody());
            System.out.println("\n");
        }
        return false;
    }
}

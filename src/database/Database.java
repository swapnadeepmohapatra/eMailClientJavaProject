package database;

import core.Account;
import core.Mail;

import java.util.ArrayList;
import java.util.Objects;

public class Database implements interfaces.Database {
    ArrayList<Account> accounts = new ArrayList<>();
    ArrayList<Mail> mails = new ArrayList<>();

    public void addMail(Mail mail) {
        mails.add(mail);
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public ArrayList<Mail> getMailsFromUser(String username) {
        ArrayList<Mail> foundMails = new ArrayList<>();
        for (Mail mail: mails){
            if (mail.getRecipient().getUsername().equals(username)){
                foundMails.add(mail);
            }
        }

        return foundMails;
    }

    public Account getAccountFromUsernameAndPassword(String username, String password) {
        for (Account account : accounts) {
            if (Objects.equals(account.getUsername(), username)) {
                if (account.isPasswordCorrect(password)) {
                    return account;
                } else {
                    System.out.println("Wrong Password");
                    return null;
                }
            }
        }
        System.out.println("Mail not found");
        return null;
    }

    public Account getAccountFromUsername(String username) {
        for (Account account : accounts) {
            if (Objects.equals(account.getUsername(), username)) {
                return account;
            }
        }
        return null;
    }
}

package interfaces;

import core.Account;
import core.Mail;

import java.util.ArrayList;

public interface Database {
    public void addMail(Mail mail);

    public void addAccount(Account account);

    public ArrayList<Mail> getMailsFromUser(String username);

    public Account getAccountFromUsernameAndPassword(String username, String password);

    public Account getAccountFromUsername(String username);
}

package interfaces;

import core.Account;
import core.Mail;

public interface MailService {
    boolean sendMail(Account account);
    boolean readMail(Account account);
    boolean showAllMailsByUser(String username);
}

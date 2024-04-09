package core;

public class Mail {
    private Account sender;
    private Account recipient;
    private String subject;
    private String body;

    public Mail(Account sender, Account recipient, String subject, String body) {
        this.sender = sender;
        this.recipient = recipient;
        this.subject = subject;
        this.body = body;
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public Account getRecipient() {
        return recipient;
    }

    public void setRecipient(Account recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

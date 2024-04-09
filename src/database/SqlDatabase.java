package database;

import core.Account;
import core.Mail;
import utils.SqlConnection;
import utils.Validator;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class SqlDatabase implements interfaces.Database {
    Validator validator = new Validator();

    Connection connection;

    public SqlDatabase() {
        SqlConnection sqlConnection = new SqlConnection();
        connection = sqlConnection.getConnection();
    }

    public void addMail(Mail mail) {
        String query = "INSERT INTO mails (sender, receiver, subject, body) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            System.out.println(mail.getRecipient().getUsername());
            System.out.println(mail.getRecipient().getPassword());
            System.out.println(mail.getRecipient().getName());

            System.out.println(mail.getBody());
            System.out.println(mail.getSubject());
            System.out.println(mail.getRecipient());

            preparedStatement.setString(1, mail.getSender().getUsername());
            preparedStatement.setString(2, mail.getRecipient().getUsername());
            preparedStatement.setString(3, mail.getSubject());
            preparedStatement.setString(4, mail.getBody());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAccount(Account account) {
        String query = "INSERT INTO accounts (name, username, password) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, account.getName());
            preparedStatement.setString(2, account.getUsername());
            preparedStatement.setString(3, account.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Mail> getMailsFromUser(String username) {
        ArrayList<Mail> foundMails = new ArrayList<>();
        String query = "SELECT * FROM mails WHERE receiver = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String sender = resultSet.getString("sender");
                String recipient = resultSet.getString("receiver");
                String subject = resultSet.getString("subject");
                String message = resultSet.getString("body");
                foundMails.add(new Mail(this.getAccountFromUsername(sender), this.getAccountFromUsername(recipient), subject, message));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundMails;

    }

    public Account getAccountFromUsernameAndPassword(String username, String password) {
        String query = "SELECT * FROM accounts WHERE username = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, validator.hidePassword(password));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Account(resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("name"));
            } else {
                System.out.println("Account not found or wrong password");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public Account getAccountFromUsername(String username) {
        String query = "SELECT * FROM accounts WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Account(resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("name"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}

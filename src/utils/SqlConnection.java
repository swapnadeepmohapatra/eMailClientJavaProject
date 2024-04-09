package utils;

import java.sql.*;

public class SqlConnection {
    public Connection connection;
    Statement statement;

    public SqlConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mail","swapnadeep","password");

            statement = connection.createStatement();

            createDatabase(statement, "mail");
            createAccountsTable(statement);
            createMailsTable(statement);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createDatabase(Statement statement, String databaseName) throws SQLException {
        String sql = "CREATE DATABASE IF NOT EXISTS " + databaseName;
        statement.executeUpdate(sql);
        System.out.println("Database '" + databaseName + "' created successfully");
    }

    private static void createAccountsTable(Statement statement) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS accounts (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "username VARCHAR(255) NOT NULL UNIQUE," +
                "password VARCHAR(255) NOT NULL" +
                ")";
        statement.executeUpdate(sql);
        System.out.println("Table 'accounts' created successfully");
    }

    private static void createMailsTable(Statement statement) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS mails (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "sender VARCHAR(255) NOT NULL," +
                "receiver VARCHAR(255) NOT NULL," +
                "subject VARCHAR(255) NOT NULL," +
                "body TEXT NOT NULL," +
                "FOREIGN KEY (sender) REFERENCES accounts(username)," +
                "FOREIGN KEY (receiver) REFERENCES accounts(username)" +
                ")";
        statement.executeUpdate(sql);
        System.out.println("Table 'mails' created successfully");
    }
}

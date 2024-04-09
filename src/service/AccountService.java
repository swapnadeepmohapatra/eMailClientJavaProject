package service;

import core.Account;
import database.Database;
import database.SqlDatabase;

import java.util.Scanner;

public class AccountService implements interfaces.AccountService {
    private static final SqlDatabase database = new SqlDatabase();
    @Override
    public void createAccount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name:");
        String name = sc.next();
        System.out.println("Enter your mail id:");
        String username = sc.next();
        System.out.println("Enter your password:");
        String password = sc.next();

        Account createdAccount = new Account(username, password, name);

        database.addAccount(createdAccount);
    }

    @Override
    public Account loginAccount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your mail id:");
        String username = sc.next();
        System.out.println("Enter your password:");
        String password = sc.next();

        Account a = database.getAccountFromUsernameAndPassword(username, password);

        return a;
    }

    @Override
    public Account searchAccount(String mail) {
        return database.getAccountFromUsername(mail);
    }
}

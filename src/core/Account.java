package core;

import utils.Validator;

import java.util.Objects;

public class Account {
    private String username;
    private final String password;
    private String name;

    private static final Validator validator = new Validator();

    public Account(String username, String password, String name) {
        this.username = username;
        this.password = validator.hidePassword(password);
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPasswordCorrect(String password){
        return Objects.equals(this.password, validator.hidePassword(password));
    }

    public String getPassword() {
        return password;
    }
}

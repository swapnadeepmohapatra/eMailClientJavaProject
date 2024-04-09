package interfaces;

public interface Validator {
    boolean validatePassword(String newPassword, String existingPassword);
    String hidePassword(String password);
}

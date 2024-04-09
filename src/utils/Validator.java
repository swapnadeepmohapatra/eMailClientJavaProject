package utils;

public class Validator implements interfaces.Validator {

    private String hashFunction(String password){
        String salt = "SwapnadeepSalt";
        String saltedPassword = password + salt;

        int hash = 0;
        for (int i = 0; i < saltedPassword.length(); i++) {
            hash = 31 * hash + saltedPassword.charAt(i);
        }

        return Integer.toHexString(hash);
    }

    @Override
    public boolean validatePassword(String newPassword, String existingPassword) {
        return hashFunction(newPassword).equals(existingPassword);
    }

    @Override
    public String hidePassword(String password) {
        return hashFunction(password);
    }
}

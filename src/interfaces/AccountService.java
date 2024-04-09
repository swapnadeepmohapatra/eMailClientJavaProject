package interfaces;

import core.Account;

public interface AccountService {

    void createAccount();
    Account loginAccount();
    Account searchAccount(String mail);
}

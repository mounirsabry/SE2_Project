package account_system;

import java.util.List;

public class AccountsExistenceInterface {

    private AccountsControl accountsControl;

    public AccountsExistenceInterface() {
        accountsControl = new AccountsControl();
    }

    public boolean signUp(String email, String userName, String password, String userType) {
        return accountsControl.signUp(email, userName, password, userType);
    }

    public boolean deleteAccount(String token, String email, String userType) {
        return accountsControl.deleteAccount(token, email, userType);
    }

    public List<List<String>> getAllUsers(String token) {
        return accountsControl.getAllUsers(token);
    }
}

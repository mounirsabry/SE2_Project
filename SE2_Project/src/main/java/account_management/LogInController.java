package account_management;

import account_system.AccountsStorage;
import account_system.AccountsLoggingInterface;

public class LogInController {

    private AccountsLoggingInterface accountsLoggingInterface;

    public LogInController() {
        accountsLoggingInterface = new AccountsLoggingInterface();
    }

    public String normalUserLogIn(String email, String password) {
        if (fieldsAreEmpty(email, password)) {
            return null;
        }
        return accountsLoggingInterface.logIn(email, password, AccountsStorage.NORMAL_USER);
    }

    public String shopOwnerLogIn(String email, String password) {
        if (fieldsAreEmpty(email, password)) {
            return null;
        }
        return accountsLoggingInterface.logIn(email, password, AccountsStorage.SHOP_OWNER);
    }

    public String adminLogIn(String email, String password) {
        if (fieldsAreEmpty(email, password)) {
            return null;
        }
        return accountsLoggingInterface.logIn(email, password, AccountsStorage.ADMIN);
    }

    private boolean fieldsAreEmpty(String email, String password) {
        if (email == null || email.equals("")) {
            return true;
        }
        if (password == null || password.equals("")) {
            return true;
        }
        return false;
    }
}

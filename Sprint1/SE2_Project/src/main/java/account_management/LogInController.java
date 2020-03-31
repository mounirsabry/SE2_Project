package account_management;

import account_system.AccountsControl;
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
        return accountsLoggingInterface.logIn(email, password, AccountsControl.NORMAL_USER);
    }

    public String shopOwnerLogIn(String email, String password) {
        if (fieldsAreEmpty(email, password)) {
            return null;
        }
        return accountsLoggingInterface.logIn(email, password, AccountsControl.SHOP_OWNER);
    }

    public String adminLogIn(String email, String password) {
        if (fieldsAreEmpty(email, password)) {
            return null;
        }
        return accountsLoggingInterface.logIn(email, password, AccountsControl.ADMIN);
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

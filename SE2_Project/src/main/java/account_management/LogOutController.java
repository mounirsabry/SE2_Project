package account_management;

import account_system.AccountsStorage;
import account_system.AccountsExistenceInterface;
import account_system.AccountsLoggingInterface;

public class LogOutController {

    private AccountsLoggingInterface accountsLoggingInterface;

    public LogOutController() {
        accountsLoggingInterface = new AccountsLoggingInterface();
    }

    public boolean normalUserLogOut(String token) {
        if (fieldsAreEmpty(token)) {
            return false;
        }
        return accountsLoggingInterface.logOut(token);
    }

    public boolean shopOwnerLogOut(String token) {
        if (fieldsAreEmpty(token)) {
            return false;
        }
        return accountsLoggingInterface.logOut(token);
    }

    public boolean adminLogOut(String token) {
        if (fieldsAreEmpty(token)) {
            return false;
        }
        return accountsLoggingInterface.logOut(token);
    }

    private boolean fieldsAreEmpty(String token) {
        if (token == null || token.equals("")) {
            return true;
        }
        return false;
    }
}

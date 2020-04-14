package account_management;

import account_system.AccountsControl;
import account_system.AccountsExistenceInterface;

public class SignUpController {

    private AccountsExistenceInterface accountsExistenceInterface;

    public SignUpController() {
        accountsExistenceInterface = new AccountsExistenceInterface();
    }

    public boolean normalUserSignUp(String email, String userName, String password) {
        if (fieldsAreEmpty(email, userName, password)) {
            return false;
        }
        return accountsExistenceInterface.signUp(email, userName, password, AccountsControl.NORMAL_USER);
    }

    public boolean shopOwnerSignUp(String email, String userName, String password) {
        if (fieldsAreEmpty(email, userName, password)) {
            return false;
        }
        return accountsExistenceInterface.signUp(email, userName, password, AccountsControl.SHOP_OWNER);
    }

    private boolean fieldsAreEmpty(String email, String userName, String password) {
        if (email == null || email.equals("")) {
            return true;
        }
        if (userName == null || userName.equals("")) {
            return true;
        }
        if (password == null || password.equals("")) {
            return true;
        }
        return false;
    }
}

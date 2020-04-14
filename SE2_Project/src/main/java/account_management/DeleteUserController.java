package account_management;

import account_system.AccountsControl;
import account_system.AccountsExistenceInterface;

public class DeleteUserController {

    private AccountsExistenceInterface accountsExistenceInterface;

    public DeleteUserController() {
        accountsExistenceInterface = new AccountsExistenceInterface();
    }

    public boolean deleteNormalUserAccount(String token, String email) {
        if (fieldsAreEmpty(token, email)) {
            return false;
        }
        return accountsExistenceInterface.deleteAccount(token, email, AccountsControl.NORMAL_USER);
    }

    public boolean deleteShopOwnerAccount(String token, String email) {
        if (fieldsAreEmpty(token, email)) {
            return false;
        }
        return accountsExistenceInterface.deleteAccount(token, email, AccountsControl.SHOP_OWNER);
    }

    public boolean deleteAdminAccount(String token, String email) {
        if (fieldsAreEmpty(token, email)) {
            return false;
        }
        return accountsExistenceInterface.deleteAccount(token, email, AccountsControl.ADMIN);
    }

    public boolean fieldsAreEmpty(String token, String email) {
        if (token == null || token.equals("")) {
            return true;
        }
        if (email == null || email.equals("")) {
            return true;
        }
        return false;
    }
}

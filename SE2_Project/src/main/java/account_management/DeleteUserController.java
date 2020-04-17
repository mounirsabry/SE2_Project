package account_management;

import account_system.AccountsStorage;
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
        return accountsExistenceInterface.deleteAccount(token, email, AccountsStorage.NORMAL_USER);
    }

    public boolean deleteShopOwnerAccount(String token, String email) {
        if (fieldsAreEmpty(token, email)) {
            return false;
        }
        return accountsExistenceInterface.deleteAccount(token, email, AccountsStorage.SHOP_OWNER);
    }

    public boolean deleteAdminAccount(String token, String email) {
        if (fieldsAreEmpty(token, email)) {
            return false;
        }
        return accountsExistenceInterface.deleteAccount(token, email, AccountsStorage.ADMIN);
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

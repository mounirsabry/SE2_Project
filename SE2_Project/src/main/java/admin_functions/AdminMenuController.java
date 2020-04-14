package admin_functions;

import account_system.AccountsExistenceInterface;
import java.util.List;

public class AdminMenuController {

    private AccountsExistenceInterface accountsExistenceInterface;
    
    public AdminMenuController() {
        accountsExistenceInterface = new AccountsExistenceInterface();
    }
    
    public List<List<String>> getAllUsers(String token) {
        if(fieldsAreEmpty(token)) {
            return null;
        }
        return accountsExistenceInterface.getAllUsers(token);
    }
    
    private boolean fieldsAreEmpty(String token) {
        if(token == null || token.equals("")) {
            return true;
        }
        return false;
    }
}

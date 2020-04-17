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
    
    public boolean addAnotherAdmin(String token, String email, String userName, String password) {
        if(fieldsAreEmpty(token, email, userName, password)) {
            return false;
        }
        return accountsExistenceInterface.addAnotherAdmin(token, email, userName, password);
    }
    
    private boolean fieldsAreEmpty(String token) {
        if(token == null || token.equals("")) {
            return true;
        } else {
            return false;
        }
    }
    
    private boolean fieldsAreEmpty(String token, String email, String userName, String password) {
        if(token == null || token.equals("")) {
            return true;
        } else if(email == null || email.equals("")) {
            return true;
        } else if(userName == null || userName.equals("")) {
            return true;
        } else if(password == null || password.equals("")) {
            return true;
        } else {
            return false;
        }
    }
}

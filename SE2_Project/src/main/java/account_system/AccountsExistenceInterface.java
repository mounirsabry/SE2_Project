package account_system;

import java.util.List;

public class AccountsExistenceInterface {

    private final AccountsStorage accountsStorage;

    public AccountsExistenceInterface() {
        accountsStorage = new MySQLStorage();
    }

    public boolean signUp(String email, String userName, String password, String userType) {
        return accountsStorage.signUp(email, userName, password, userType);
    }
    
    public boolean addAnotherAdmin(String token, String email, String userName, String password) {
        return accountsStorage.addAnotherAdmin(token, email, userName, password);
    }

    public boolean deleteAccount(String token, String email, String userType) {
        return accountsStorage.deleteAccount(token, email, userType);
    }

    public List<List<String>> getAllUsers(String token) {
        return accountsStorage.getAllUsers(token);
    }
}

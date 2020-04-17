package account_system;

public class AccountsLoggingInterface {

    private final AccountsStorage accountsStorage;

    public AccountsLoggingInterface() {
        accountsStorage = new MySQLStorage();
    }

    public String logIn(String token, String password, String userType) {
        return accountsStorage.logIn(token, password, userType);
    }

    public boolean logOut(String token) {
        return accountsStorage.logOut(token);
    }
}

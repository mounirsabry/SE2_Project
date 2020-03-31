package account_system;

public class AccountsLoggingInterface {

    private AccountsControl accountsControl;

    public AccountsLoggingInterface() {
        accountsControl = new AccountsControl();
    }

    public String logIn(String token, String password, String userType) {
        return accountsControl.logIn(token, password, userType);
    }

    public boolean logOut(String token) {
        return accountsControl.logOut(token);
    }
}

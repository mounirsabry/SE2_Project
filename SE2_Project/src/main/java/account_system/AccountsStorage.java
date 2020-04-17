package account_system;

import java.util.List;

public abstract class AccountsStorage {

    final public static String NORMAL_USER = "normal_user";
    final public static String SHOP_OWNER = "shop_owner";
    final public static String ADMIN = "admin";

    public AccountsStorage() {

    }

    public abstract boolean signUp(String email, String userName, String password, String userType);
    
    public abstract boolean addAnotherAdmin(String token, String email, String userName, String password);
    
    public abstract boolean deleteAccount(String token, String email, String userType);
    
    public abstract String logIn(String email, String password, String userType);
    
    public abstract boolean logOut(String token);
    
    public abstract List<List<String>> getAllUsers(String token);
}

package account_management;

public class LogOutInterface {
    
    private LogOutController logOutController;
    
    public LogOutInterface() {
        logOutController = new LogOutController();
    }
    
    public boolean normalUserLogOut(String token) {
        return logOutController.normalUserLogOut(token);
    }
    
    public boolean shopOwnerLogOut(String token) {
        return logOutController.shopOwnerLogOut(token);
    }
    
    public boolean adminLogOut(String token) {
        return logOutController.adminLogOut(token);
    }
}

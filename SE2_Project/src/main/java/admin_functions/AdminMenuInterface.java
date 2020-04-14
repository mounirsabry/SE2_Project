package admin_functions;

import java.util.List;


public class AdminMenuInterface {
    private AdminMenuController adminMenuController;
    
    public AdminMenuInterface() {
        adminMenuController = new AdminMenuController();
    }
    
    public List<List<String>> getAllUsers(String token) {
        return adminMenuController.getAllUsers(token);
    }
}

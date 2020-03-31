package account_management;

public class LogInInterface {

    private LogInController logInController;

    public LogInInterface() {
        logInController = new LogInController();
    }

    public String normalUserLogIn(String email, String password) {
        return logInController.normalUserLogIn(email, password);
    }

    public String shopOwnerLogIn(String email, String password) {
        return logInController.shopOwnerLogIn(email, password);
    }

    public String adminLogIn(String email, String password) {
        return logInController.adminLogIn(email, password);
    }
}

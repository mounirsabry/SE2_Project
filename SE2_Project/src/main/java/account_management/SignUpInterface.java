package account_management;

public class SignUpInterface {

    private SignUpController signUpController;

    public SignUpInterface() {
        signUpController = new SignUpController();
    }

    public boolean normalUserSignUp(String email, String userName, String password) {
        return signUpController.normalUserSignUp(email, userName, password);
    }

    public boolean shopOwnerSignUp(String email, String userName, String password) {
        return signUpController.shopOwnerSignUp(email, userName, password);
    }
}

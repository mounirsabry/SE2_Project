package account_management;

public class DeleteUserInterface {

    private DeleteUserController deleteUserController;

    public DeleteUserInterface() {
        deleteUserController = new DeleteUserController();
    }

    public boolean deleteNormalUserAccount(String token, String email) {
        return deleteUserController.deleteNormalUserAccount(token, email);
    }

    public boolean deleteShopOwnerAccount(String token, String email) {
        return deleteUserController.deleteShopOwnerAccount(token, email);
    }

    public boolean deleteAdminAccount(String token, String email) {
        return deleteUserController.deleteAdminAccount(token, email);
    }
}

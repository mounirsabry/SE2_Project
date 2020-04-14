package user_package;

import java.io.Serializable;

public class ShopOwner extends User implements Serializable{

    public ShopOwner(int ID, String email, String userName, String password) {
        super(ID, email, userName, password);
    }

    public ShopOwner() {
        this(-1, "", "", "");
    }
}

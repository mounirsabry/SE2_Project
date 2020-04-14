package user_package;

import java.io.Serializable;

public class Admin extends User implements Serializable {

    public Admin(int ID, String email, String userName, String password) {
        super(ID, email, userName, password);
    }

    public Admin() {
        this(-1, "", "", "");
    }

}

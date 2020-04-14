package user_package;

import java.io.Serializable;

public class NormalUser extends User implements Serializable{

    public NormalUser(int ID, String email, String userName, String password) {
        super(ID, email, userName, password);
    }

    public NormalUser() {
        this(-1, "", "", "");
    }
}

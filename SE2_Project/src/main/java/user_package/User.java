package user_package;

import java.io.Serializable;

abstract public class User implements Serializable{

    private int ID;
    private String email;
    private String userName;
    private String password;

    public User(int ID, String email, String userName, String password) {
        this.ID = ID;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return ID + " " + email + " " + userName + " " + password;
    }

}

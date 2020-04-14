package account_system;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import user_package.*;

public class AccountsControl {

    final public static String NORMAL_USER = "normal_user";
    final public static String SHOP_OWNER = "shop_owner";
    final public static String ADMIN = "admin";

    final private String fileName = "data.bin";
    List<User> normalUsersList = null;
    List<User> shopOwnersList = null;
    List<User> adminsList = null;
    List<String> tokens = null;

    public AccountsControl() {

    }

    private void loadLists() {
        File file = new File(fileName);
        if (!file.exists()) {
            createData();
        } else if (file.length() == 0) {
            file.delete();
            createData();
        } else {
            readData();
        }
    }

    private void createData() {
        normalUsersList = new ArrayList<User>();
        shopOwnersList = new ArrayList<User>();
        adminsList = new ArrayList<User>();
        adminsList.add(new Admin(0, "admin", "admin", "admin"));
        tokens = new ArrayList<String>();

        saveLists();
    }

    private void readData() {
        try {
            File file = new File(fileName);
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            normalUsersList = (List<User>) objectInputStream.readObject();
            shopOwnersList = (List<User>) objectInputStream.readObject();
            adminsList = (List<User>) objectInputStream.readObject();
            tokens = (List<String>) objectInputStream.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AccountsControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(AccountsControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void saveLists() {
        try {
            File file = new File(fileName);
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(normalUsersList);
            objectOutputStream.writeObject(shopOwnersList);
            objectOutputStream.writeObject(adminsList);
            objectOutputStream.writeObject(tokens);

            objectOutputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(AccountsControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean signUp(String email, String userName, String password, String userType) {
        System.out.println(email + " " + userName + " " + password + " " + userType);
        loadLists();

        if (userType.equals(NORMAL_USER)) {
            if (emailAlreadyExists(normalUsersList, email)) {
                return false;
            }
            normalUsersList.add(new NormalUser(normalUsersList.size(), email, userName, password));
        } else if (userType.equals(SHOP_OWNER)) {
            if (emailAlreadyExists(shopOwnersList, email)) {
                return false;
            }
            shopOwnersList.add(new ShopOwner(shopOwnersList.size(), email, userName, password));
        } else {
            if (emailAlreadyExists(adminsList, email)) {
                return false;
            }
            adminsList.add(new Admin(shopOwnersList.size(), email, userName, password));
        }

        saveLists();
        return true;
    }

    public boolean deleteAccount(String token, String email, String userType) {
        System.out.println(token + " " + email + " " + userType);
        loadLists();

        if (!tokenExistsInList(tokens, token)) {
            return false;
        }
        if (userType.equals(NORMAL_USER)) {
            if (!deleteUserFromList(normalUsersList, email)) {
                return false;
            }
        } else if (userType.equals(SHOP_OWNER)) {
            if (!deleteUserFromList(shopOwnersList, email)) {
                return false;
            }
        } else {
            if (!deleteUserFromList(adminsList, email)) {
                return false;
            }
        }

        saveLists();
        return true;
    }

    public String logIn(String email, String password, String userType) {
        System.out.println(email + " " + password + " " + userType);
        loadLists();

        if (userType.equals(NORMAL_USER)) {
            if (!userExistsInList(normalUsersList, email, password)) {
                return null;
            }
        } else if (userType.equals(SHOP_OWNER)) {
            if (!userExistsInList(shopOwnersList, email, password)) {
                return null;
            }
        } else {
            if (!userExistsInList(adminsList, email, password)) {
                return null;
            }
        }
        String token = generateToken(email);
        tokens.add(token);
        saveLists();
        return token;
    }

    public boolean logOut(String token) {
        System.out.println(token);
        loadLists();

        if (!tokenExistsInList(tokens, token)) {
            return false;
        }
        deleteTokenFromList(tokens, token);

        saveLists();
        return true;
    }

    public List<List<String>> getAllUsers(String token) {
        loadLists();

        List<List<String>> allUsersList = new ArrayList<>();
        addInformationToUsersList(allUsersList, normalUsersList);
        addInformationToUsersList(allUsersList, shopOwnersList);
        addInformationToUsersList(allUsersList, adminsList);

        return allUsersList;
    }

    private boolean emailAlreadyExists(List<User> usersList, String email) {
        for (User user : usersList) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    private boolean deleteUserFromList(List<User> usersList, String email) {
        for (int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).getEmail().equals(email)) {
                usersList.remove(i);
                return true;
            }
        }
        return false;
    }

    private boolean userExistsInList(List<User> usersList, String email, String password) {
        for (User user : usersList) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    private String generateToken(String email) {
        return email;
    }

    private boolean tokenExistsInList(List<String> tokens, String token) {
        for (String recordedToken : tokens) {
            if (recordedToken.equals(token)) {
                return true;
            }
        }
        return false;
    }

    private void deleteTokenFromList(List<String> tokens, String token) {
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).equals(token)) {
                tokens.remove(i);
                return;
            }
        }
    }

    private void addInformationToUsersList(List<List<String>> allUsersList, List<User> users) {
        List<String> usersInformation = new ArrayList<>();
        for (User user : users) {
            usersInformation.add(user.getID() + " " + user.getEmail());
        }
        allUsersList.add(usersInformation);
    }
}

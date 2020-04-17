package account_system;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.ibatis.jdbc.ScriptRunner;

public class MySQLStorage extends AccountsStorage {

    private final String DATABASE_NAME = "se2_project";
    private final String USER = "root";
    private final String HOST = "localhost";
    private final String PORT = "3306";
    private final String PASSWORD = "root";

    private final String MYSQL_FILE = "mysql_database.sql";
    private final String USERS_TABLE = "my_user";
    private final String NORMAL_USERS_TABLE = "normal_user";
    private final String SHOP_OWNER_TABLE = "shop_owner";
    private final String ADMIN_TABLE = "my_admin";
    private final String TOKENS_TABLE = "tokens";

    public MySQLStorage() {
        checkDataBase();
    }

    private Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://" + HOST + "/" + DATABASE_NAME + "?"
                    + "user=" + USER + "&password=" + PASSWORD);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(MySQLStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    private boolean isDBExist() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            DriverManager.getConnection("jdbc:mysql://" + HOST + "/" + DATABASE_NAME + "?"
                    + "user=" + USER + "&password=" + PASSWORD);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(MySQLStorage.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (SQLException ex) {
            String exceptionMessage = ex.getMessage();
            if (exceptionMessage.equals("Unknown database '" + DATABASE_NAME + "'")) {
                return false;
            } else {
                Logger.getLogger(MySQLStorage.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return true;
    }

    private void createDataBase() {

        Connection connection = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://" + HOST + "/" + "mysql" + "?"
                    + "user=" + USER + "&password=" + PASSWORD);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(MySQLStorage.class.getName()).log(Level.SEVERE, null, ex);
        }

        Reader reader = null;
        try {
            ScriptRunner scriptRunner = new ScriptRunner(connection);
            reader = new BufferedReader(new FileReader(MYSQL_FILE));
            scriptRunner.runScript(reader);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MySQLStorage.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(MySQLStorage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void checkDataBase() {
        if (!isDBExist()) {
            createDataBase();
        }
    }

    private String generateToken(String userType) {
        //Token : userType:Random Number.
        //Random Number: random 4-digit fixed, 1000 - 9999.
        return "";
    }

    @Override
    public boolean signUp(String email, String userName, String password, String userType) {
        return true;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAnotherAdmin(String token, String email, String userName, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteAccount(String token, String email, String userType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String logIn(String email, String password, String userType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean logOut(String token) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<List<String>> getAllUsers(String token) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

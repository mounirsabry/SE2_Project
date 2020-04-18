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
import java.sql.*;

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

    private boolean checkToken(String token) {
        String query = "select * from tokens where token = " + '"' + token + '"' + ";";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://" + HOST + "/" + DATABASE_NAME + "?" + "user=" + USER + "&password=" + PASSWORD);
            Statement statement = connection.createStatement();

            // execute the query, and get a java resultset
            ResultSet result = statement.executeQuery(query);

            if (result.next()) {
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(MySQLStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private String generateToken(String userType) {
        //Token : userType:Random Number.
        //Random Number: random 4-digit fixed, 1000 - 9999.

        // Generate the random number;
        String token;
        while (true) {
            token = "";
            int randomNumber = (int) ((Math.random() * ((9999 - 1000) + 1)) + 1000);
            token = userType + ":" + randomNumber;

            if (!checkToken(token)) {
                return token;
            }
        }
    }

    @Override
    public boolean signUp(String email, String userName, String password, String userType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

        String tableType;
        if (userType.equals("admin"))
            tableType = "my_admin";
        else
            tableType = userType;
        String checkAccountInTypeTableQuery = "select * from " + tableType + " where email = " + '"' + email + '"' + ";";

        try {
            int id;
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://" + HOST + "/" + DATABASE_NAME + "?" + "user=" + USER + "&password=" + PASSWORD);
            Statement statement = connection.createStatement();

            // execute the query, and get a java resultset
            ResultSet result = statement.executeQuery(checkAccountInTypeTableQuery);

            if (!result.next()) {
                connection.close();
                return "";
            } else {
                id = result.getInt("id");
                String checkPasswordQuery = "select * from " + USERS_TABLE + " where id = " + id + " and email = " + '"' + email + '"' + " and password = " + '"' + password + '"' + ";";
                result = statement.executeQuery(checkPasswordQuery);
                if (!result.next()) {
                    // wrong password
                    connection.close();
                    return "";
                } else {
                    // right password
                    String token = generateToken(userType);
                    String addTokenQuery = "insert into " + TOKENS_TABLE + " (id,token) " + "values (" + id + "," + '"' + token + '"' + ")" + ";";
                    statement.executeUpdate(addTokenQuery);
                    connection.close();
                    return token;
                }
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(MySQLStorage.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "";
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

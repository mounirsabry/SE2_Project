package account_system;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.ibatis.jdbc.ScriptRunner;
import java.sql.*;
import java.util.ArrayList;

public class MySQLStorage extends AccountsStorage {

    private final String DATABASE_NAME = "se2_project";
    private final String USER = "root";
    private final String HOST = "localhost";
    private final String PORT = "3306";
    private final String PASSWORD = "root";

    private final String MYSQL_FILE = "mysql_database.sql";
    private final String USERS_TABLE = "my_user";
    private final String NORMAL_USERS_TABLE = "normal_user";
    private final String SHOP_OWNERS_TABLE = "shop_owner";
    private final String ADMINS_TABLE = "my_admin";
    private final String TOKENS_TABLE = "tokens";

    public MySQLStorage() {
        checkDataBase();
    }

    private Connection getConnection(String databaseName) {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + databaseName, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(MySQLStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    private boolean isDBExist() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE_NAME, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            return false;
        }
        return true;
    }

    private void createDataBase() {
        Connection connection = getConnection("mysql");
        ScriptRunner scriptRunner = new ScriptRunner(connection);
        InputStream inputStream = this.getClass().getResourceAsStream("/mysql_database.sql");
        Reader reader = new InputStreamReader(inputStream);
        scriptRunner.runScript(reader);
        try {
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(MySQLStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void checkDataBase() {
        if (!isDBExist()) {
            createDataBase();
        }
    }

    private boolean checkToken(String token) {
        String query = "select * from tokens where token = " + '"' + token + '"' + ";";
        ResultSet result;
        try {
            Connection connection = getConnection(DATABASE_NAME);
            Statement statement = connection.createStatement();
            result = statement.executeQuery(query);

            if (result.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
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
            int randomNumber = (int) ((Math.random() * ((9999 - 1000) + 1)) + 1000);
            token = userType + ":" + randomNumber;

            if (!checkToken(token)) {
                return token;
            }
        }
    }

    @Override
    public boolean signUp(String email, String userName, String password, String userType) {
        String tableType;
        if (userType.equals(NORMAL_USER)) {
            tableType = NORMAL_USERS_TABLE;
        } else if (userType.equals(SHOP_OWNER)) {
            tableType = SHOP_OWNERS_TABLE;
        } else {
            tableType = ADMINS_TABLE;
        }

        try {
            Connection connection = getConnection(DATABASE_NAME);
            Statement statement;
            String getMails;
            getMails = "select * from " + tableType + " where email = " + '"' + email + '"' + ";";

            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(getMails);
            if (result.next()) {
                connection.close();
                return false;
            } else {
                String trueStatment, user;
                user = "insert into " + USERS_TABLE + " (email,username,password) " + "values (" + '"' + email + '"'
                        + "," + '"' + userName + '"' + "," + '"' + password + '"' + ");";
                statement.executeUpdate(user);

                getMails = "select * from " + USERS_TABLE + " ORDER BY id DESC LIMIT 1;";
                result = statement.executeQuery(getMails);
                int id = 0;
                while (result.next()) {
                    id = result.getInt("id");
                    break;
                }
                trueStatment = "insert into " + tableType + " (id,email) " + "values (" + id + "," + '"' + email + '"' + ")" + ";";
                statement.executeUpdate(trueStatment);
                connection.close();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLStorage.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean addAnotherAdmin(String token, String email, String userName, String password) {
        if (checkToken(token)) {
            try {
                String[] userType = token.split(":");
                if (userType[0].equals(ADMIN)) {
                    signUp(email, userName, password, ADMIN);
                    return true;
                }
            } catch (Exception e) {

            }
        }
        return false;
    }

    @Override
    public boolean deleteAccount(String token, String email, String userType) {

        String tableType;
        if (userType.equals(NORMAL_USER)) {
            tableType = NORMAL_USERS_TABLE;
        } else if (userType.equals(SHOP_OWNER)) {
            tableType = SHOP_OWNERS_TABLE;
        } else {
            tableType = ADMINS_TABLE;
        }

        if (checkToken(token)) {
            try {
                Connection connection = getConnection(DATABASE_NAME);
                Statement statement;
                String getIDFromToken = "select * from " + TOKENS_TABLE + " where token = " + '"' + token + '"' + ";";

                statement = connection.createStatement();
                ResultSet result = statement.executeQuery(getIDFromToken);

                int id = 0;
                while (result.next()) {
                    id = result.getInt("id");
                    break;
                }

                logOut(token);

                String deleteQuery = "DELETE FROM " + tableType + " WHERE id = " + id + ";";
                statement.executeUpdate(deleteQuery);

                deleteQuery = "DELETE FROM " + USERS_TABLE + " WHERE id = " + id + ";";
                statement.executeUpdate(deleteQuery);
                return true;

            } catch (SQLException e) {

            }
        }
        return false;
    }

    @Override
    public String logIn(String email, String password, String userType) {

        String tableType;
        if (userType.equals(NORMAL_USER)) {
            tableType = NORMAL_USERS_TABLE;
        } else if (userType.equals(SHOP_OWNER)) {
            tableType = SHOP_OWNERS_TABLE;
        } else {
            tableType = ADMINS_TABLE;
        }

        String checkAccountInTypeTableQuery = "select * from " + tableType + " where email = " + '"' + email + '"' + ";";

        try {
            int id;
            Connection connection = getConnection(DATABASE_NAME);
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
        } catch (SQLException ex) {
            Logger.getLogger(MySQLStorage.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "";
    }

    @Override
    public boolean logOut(String token) {
        if (checkToken(token)) {
            try {
                String deleteToken;
                Connection connection = getConnection(DATABASE_NAME);
                deleteToken = "DELETE FROM " + TOKENS_TABLE + " WHERE token = " + '"' + token + '"' + ";";
                Statement statement;
                statement = connection.createStatement();
                statement.executeUpdate(deleteToken);
                connection.close();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(MySQLStorage.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }

        } else {
            return false;
        }
    }

    @Override
    public List<List<String>> getAllUsers(String token) {

        List<List<String>> allUsers = new ArrayList<>();
        List<String> normalUsers = new ArrayList<>();
        List<String> shopOwners = new ArrayList<>();
        List<String> admins = new ArrayList<>();

        if (checkToken(token)) {
            String[] userType = token.split(":");
            if (userType[0].equals(ADMIN)) {
                try {
                    Connection connection = getConnection(DATABASE_NAME);
                    ResultSet result;
                    Statement statement = connection.createStatement();

                    String tmp = "";

                    String query = "select * from " + NORMAL_USERS_TABLE + ";";
                    result = statement.executeQuery(query);
                    while (result.next()) {
                        tmp = result.getString("email");
                        normalUsers.add(tmp);
                    }
                    allUsers.add(normalUsers);

                    query = "select * from " + SHOP_OWNERS_TABLE + ";";
                    result = statement.executeQuery(query);
                    while (result.next()) {
                        tmp = result.getString("email");
                        shopOwners.add(tmp);
                    }
                    allUsers.add(shopOwners);

                    query = "select * from " + ADMINS_TABLE + ";";
                    result = statement.executeQuery(query);
                    while (result.next()) {
                        tmp = result.getString("email");
                        admins.add(tmp);
                    }
                    allUsers.add(admins);

                } catch (SQLException e) {

                }
            }
        }
        return allUsers;
    }

}

package dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
    /**
     * Constructor for the User Dao
     */
    public User() {
    }

    public static void main(String[] args) {
        User myUserDao = new dao.User();
        model.User sampleUser2 = new model.User("samuel", "toast", "samgmailcom", "sam", "osborne", "m", "do");
        model.User sampleUser = new model.User("sam", "toast", "samgmailcom", "sam", "osborne", "m", "do");
        myUserDao.clearTable();
        myUserDao.createTable();
        myUserDao.addUser(sampleUser);
        myUserDao.addUser(sampleUser2);
        myUserDao.removeUser(sampleUser);
        myUserDao.getUser("samuel");
        myUserDao.getUser("samuel");

    }

    /**
     * Creates an empty user table
     */
    public void createTable() {
        try (Connection conn = this.connect()) {
            String sql = "CREATE TABLE IF NOT EXISTS \"User\" ( `Username` TEXT NOT NULL UNIQUE, `Password` TEXT NOT NULL, `Email` TEXT NOT NULL, `FirstName` TEXT NOT NULL, `LastName` TEXT NOT NULL, `Gender` TEXT NOT NULL CHECK(Gender == \"m\" OR Gender == \"f\"), `PersonID` TEXT NOT NULL, PRIMARY KEY(`Username`), FOREIGN KEY(`PersonID`) REFERENCES `Person`(`PersonID`) )";

            Statement stmt = conn.createStatement();
            stmt.execute(sql);

            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Connection connect() {
        String filePath = new File("").getAbsolutePath() + "\\mydata.db";
        String url = "jdbc:sqlite:" + filePath;
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * Adds a specified user to the table
     *
     * @param user
     */
    public void addUser(model.User user) {

        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO User (Username,Password,Email,FirstName,LastName,Gender,PersonID) VALUES (");
        sb.append("'" + user.getUsername() + "'");
        sb.append(", ");
        sb.append("'" + user.getPassword() + "'");
        sb.append(", ");
        sb.append("'" + user.getEmail() + "'");
        sb.append(", ");
        sb.append("'" + user.getFirstName() + "'");
        sb.append(", ");
        sb.append("'" + user.getLastName() + "'");
        sb.append(",");
        sb.append("'" + user.getGender() + "'");
        sb.append(",");
        sb.append("'" + user.getPersonID() + "'");
        sb.append(")");
        String sql = sb.toString();
        try (Connection conn = this.connect();
             Statement pstmt = conn.createStatement()) {
            pstmt.execute(sql);
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    /**
     * Removes a specified user
     *
     * @param user
     */
    public void removeUser(model.User user) {
        String sqlDel = "DELETE FROM User WHERE Username = \"" + user.getUsername() + "\"";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sqlDel);
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Get a user by username
     *
     * @param username
     * @return the user object that corresponds with the username
     */
    public model.User getUser(String username) {
        String sqlGet = "SELECT * FROM User WHERE Username = \"" + username + "\"";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            ResultSet res = stmt.executeQuery(sqlGet);
            String myusername = res.getString("username");
            String password = res.getString("password");
            String email = res.getString("email");
            String firstName = res.getString("firstName");
            String lastName = res.getString("lastName");
            String gender = res.getString("gender");
            String personID = res.getString("personID");
            model.User outUser = new model.User(myusername, password, email, firstName, lastName, gender, personID);
            conn.close();
            return outUser;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Clears the table by dropping it then recreating it
     */
    public void clearTable() {
        String drop = "DROP TABLE User";
        try (Connection conn = this.connect();
             Statement pstmt = conn.createStatement()) {
            pstmt.executeUpdate(drop);
            pstmt.close();
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        this.createTable();
    }
}

package dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AuthorizationToken {
    /**
     * Constructor for the AuthorizationToken Dao
     */
    public AuthorizationToken() {
    }

    /**
     * Creates an empty AuthorizationToken table
     */
    public void createTable() {
        try (Connection conn = this.connect()) {
            if (conn != null) {
                System.out.println("A new database has been created.");
            }

            String sql = "CREATE TABLE `AuthorizationToken` ( `AuthorizationToken` TEXT NOT NULL UNIQUE, `User` TEXT NOT NULL,FOREIGN KEY(`User`) REFERENCES `User`(`Username`) PRIMARY KEY(`AuthorizationToken`) )";

            Statement stmt = conn.createStatement();
            // create a new table
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
     * Adds a specified AuthorizationToken to the table
     *
     * @param token
     */
    public void addToken(model.AuthorizationToken token) {

    }

    /**
     * Removes a specified AuthorizationToken
     *
     * @param token
     */
    public void removeToken(model.AuthorizationToken token) {

    }

    /**
     * Get an AuthorizationToken object by the token
     *
     * @param token
     * @return the AuthorizationToken object that corresponds with the token
     */
    public model.AuthorizationToken getToken(String token) {
        String sqlGet = "SELECT * FROM User WHERE AuthorizationToken = \"" + token + "\"";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            ResultSet res = stmt.executeQuery(sqlGet);
            String authorizationToken = res.getString("authorizationToken");
            String user = res.getString("user");
            model.AuthorizationToken outToken = new model.AuthorizationToken(authorizationToken, user);
            conn.close();
            return outToken;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Clears the table by dropping it then recreating it
     */
    public void clearTable() {
        String drop = "DROP TABLE AuthorizationToken";
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

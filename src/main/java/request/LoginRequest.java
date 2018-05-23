package request;

/**
 * Created by samosbor on 5/18/18.
 */

public class LoginRequest {
    /**
     * The username entered to try to login
     */
    String username;
    /**
     * The password entered to try to login
     */
    String password;

    /**
     * The constructor for the login object
     * @param username
     * @param password
     */
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

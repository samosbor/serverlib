package request;

/**
 * Created by samosbor on 5/18/18.
 */

public class LoginRequest {
    /**
     * The userName entered to try to login
     */
    String userName;
    /**
     * The password entered to try to login
     */
    String password;

    /**
     * The constructor for the login object
     *
     * @param userName
     * @param password
     */
    public LoginRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

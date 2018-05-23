package result;

/**
 * Created by samosbor on 5/18/18.
 */

public class RegisterResult {
    /**
     * The auth token to respond with
     */
    String authToken;
    /**
     * The username passed in with the request
     */
    String username;
    /**
     * The person ID of the user's generated person object
     */
    String personID;
    /**
     * The message to be output in case of error
     */
    String message;

    /**
     * The constructor for the register response object
     *
     * @param authToken
     * @param username
     * @param personID
     * @param message
     */
    public RegisterResult(String authToken, String username, String personID, String message) {
        this.authToken = authToken;
        this.username = username;
        this.personID = personID;
        this.message = message;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package request;

/**
 * Created by samosbor on 5/18/18.
 */

public class RegisterRequest {
    /**
     * The username that is to be registered. Must not already be in the database.
     */
    String username;
    /**
     * The password that the user chose
     */
    String password;
    /**
     * Email of the user
     */
    String email;
    /**
     * First name of the user
     */
    String firstName;
    /**
     * Last name of the user
     */
    String lastName;
    /**
     * Gender of the user (m|f)
     */
    String gender;

    /**
     * Constructor for the register request object
     * @param username
     * @param password
     * @param email
     * @param firstName
     * @param lastName
     * @param gender
     */
    public RegisterRequest(String username, String password, String email, String firstName, String lastName, String gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

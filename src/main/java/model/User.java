package model;

import java.util.Objects;

/**
 * Created by samosbor on 5/16/18.
 */

public class User {
    /**
     * A user's userName
     */
    private String userName;
    /**
     * A user's password
     */
    private String password;
    /**
     * A user's email address
     */
    private String email;
    /**
     * A user's first name
     */
    private String firstName;
    /**
     * A user's last name
     */
    private String lastName;
    /**
     * A user's gender. Can only be 'm' or 'f'
     */
    private String gender;
    /**
     * A user's unique ID
     */
    private String personID;

    /**
     * A User's constructor. Must have all values not null.
     *
     * @param userName
     * @param password
     * @param email
     * @param firstName
     * @param lastName
     * @param gender
     * @param personID
     */
    public User(String userName, String password, String email, String firstName, String lastName, String gender, String personID) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.personID = personID;
    }

    /**
     * Gets the user's userName
     *
     * @return the user's userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the user's userName
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the users password
     *
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's userName
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the users email
     *
     * @return the user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's userName
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the users first name
     *
     * @return the user's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the user's userName
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the users last name
     *
     * @return the user's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the user's userName
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the users gender
     *
     * @return the user's gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the user's userName
     *
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets the users ID
     *
     * @return the user's ID
     */
    public String getPersonID() {
        return personID;
    }

    /**
     * Sets the user's userName
     *
     * @param personID
     */
    public void setPersonID(String personID) {
        this.personID = personID;
    }

    /**
     * Checks to see if a user is equal to another
     *
     * @param o the object to compare
     * @return true if the users are equal, false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(gender, user.gender) &&
                Objects.equals(personID, user.personID);
    }

    /**
     * Gets the hashcode value of the user
     *
     * @return the hashcode value of the user
     */
    @Override
    public int hashCode() {
        int result = getUserName().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();
        result = 31 * result + getGender().hashCode();
        result = 31 * result + getPersonID().hashCode();
        return result;
    }

    /**
     * Gets the string representation of the user object
     *
     * @return the string that represents the user
     */
    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", personID='" + personID + '\'' +
                '}';
    }
}

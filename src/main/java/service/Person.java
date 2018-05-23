package service;

import result.PersonResult;

/**
 * Created by samosbor on 5/18/18.
 */

public class Person {
    /**
     * The Auth token given in the URL
     */
    String token;

    /**
     * The constructor for the person service object
     */
    public Person(String token) {
        this.token = token;
    }

    /**
     * Function to gather all of the family members of the current user. The current user is
     * determined from the provided auth token.
     *
     * @return a person result object
     */
    public PersonResult person(model.AuthorizationToken myToken) {
        return null;
    }
}

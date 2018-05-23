package service;

import result.PersonIDResult;

/**
 * Created by samosbor on 5/18/18.
 */

public class PersonID {
    /**
     * The person ID passed in from the URL
     */
    String personID;
    /**
     * The Auth token given in the URL
     */
    String token;
    /**
     * The constructor for the personID service object
     */
    public PersonID(String personID, String token) {
        this.personID = personID;
        this.token = token;
    }
    /**
     * Function to get a person by ID
     * @return a PersonIDResult result object
     */
    public PersonIDResult getPerson(model.AuthorizationToken myToken){
        return null;
    }
}

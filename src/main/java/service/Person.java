package service;

import java.util.ArrayList;

import dao.User;
import result.PersonIDResult;
import result.PersonResult;

/**
 * Created by samosbor on 5/18/18.
 */

public class Person {

    /**
     * The constructor for the person service object
     */
    public Person() {
    }

    /**
     * Function to gather all of the family members of the current user. The current user is
     * determined from the provided auth token.
     *
     * @return a person result object
     */
    public PersonResult person(String token) {
        dao.AuthorizationToken aDao = new dao.AuthorizationToken();
        dao.Person pDao = new dao.Person();
        String message;
        PersonResult result;
        if(aDao.getToken(token) != null) {
            ArrayList<model.Person> list = pDao.getAllFamily(aDao.getToken(token));
            model.Person[] array = list.toArray(new model.Person[0]);
            result = new PersonResult(array);
        }else{
            message = "Invalid auth token.";
            result = new PersonResult(message);
        }
        return result;
    }
}

package service;

import dao.AuthorizationToken;
import dao.Person;
import dao.User;
import result.PersonIDResult;

/**
 * Created by samosbor on 5/18/18.
 */

public class PersonID {
    /**
     * The constructor for the personID service object
     */
    public PersonID() {
    }

    /**
     * Function to get a person by ID
     *
     * @return a PersonIDResult result object
     */
    public PersonIDResult getPerson(String token, String personID) {
        dao.AuthorizationToken aDao = new dao.AuthorizationToken();
        dao.Person pDao = new Person();
        dao.User uDao = new User();
        String message;
        PersonIDResult result;
        if(aDao.getToken(token) != null) {
            String authUser = aDao.getToken(token).getUser();
            if(pDao.getPerson(personID)!= null){
                model.Person outPerson = pDao.getPerson(personID);
                if(outPerson.getDescendant().equals(authUser)){
                    result = new PersonIDResult(outPerson);
                }else{
                    message = "Person doesn't belong to user";
                    result = new PersonIDResult(message);
                }
            }else{
                message = "Invalid personID";
                result = new PersonIDResult(message);
            }
        }else{
            message = "Invalid auth token.";
            result = new PersonIDResult(message);
        }
        return result;
    }
}

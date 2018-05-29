package service;

import dao.AuthorizationToken;
import dao.Event;
import dao.Person;
import dao.User;
import result.ClearResult;

/**
 * Created by samosbor on 5/18/18.
 */

public class Clear {
    /**
     * The constructor for the clear service object
     */
    public Clear() {
    }

    /**
     * The function to clear ALL data from the database, including user accounts, auth tokens, and
     * generated person and event data. Returns a clear result object.
     *
     * @return
     */
    public ClearResult clear() {
        dao.AuthorizationToken aDao = new AuthorizationToken();
        dao.Person pDao = new Person();
        dao.User uDao = new User();
        dao.Event eDao = new Event();
        String message;
        try{
            aDao.clearTable();
            pDao.clearTable();
            uDao.clearTable();
            eDao.clearTable();

            message = "Clear Succeeded.";
        }catch(Exception e){
            message = e.toString();
        }
        ClearResult result = new ClearResult(message);
        return result;
    }
}

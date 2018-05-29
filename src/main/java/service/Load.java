package service;

import dao.AuthorizationToken;
import dao.Event;
import dao.Person;
import dao.User;
import request.LoadRequest;
import result.LoadResult;

/**
 * Created by samosbor on 5/18/18.
 */

public class Load {
    /**
     * The constructor for the load service object
     */
    public Load() {
    }

    /**
     * Clears all data from the database (just like the /clear API), and then loads the
     * posted user, person, and event data into the database.
     *
     * @param request
     * @return
     */
    public LoadResult load(LoadRequest request) {
        dao.AuthorizationToken aDao = new AuthorizationToken();
        dao.Person pDao = new Person();
        dao.User uDao = new User();
        dao.Event eDao = new Event();
        String message;
        LoadResult result;
        int numUsers = 0;
        int numPersons = 0;
        int numEvents = 0;

        for(model.User element : request.getUsers()){
            uDao.addUser(element);
            numUsers++;
        }
        for(model.Person element : request.getPersons()){
            pDao.addPerson(element);
            numPersons++;
        }
        for(model.Event element : request.getEvents()){
            eDao.addEvent(element);
            numEvents++;
        }
        message = "Successfully added "+Integer.toString(numUsers)+" users, "+Integer.toString(numPersons)+" persons, and "+Integer.toString(numEvents)+" events to the database.";
        result = new LoadResult(message,numUsers, numPersons, numEvents);
        return result;
    }
}

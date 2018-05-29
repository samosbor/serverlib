package service;

import java.util.ArrayList;

import model.Person;
import result.EventResult;
import result.PersonResult;

/**
 * Created by samosbor on 5/18/18.
 */

public class Event {

    /**
     * The constructor for the event service object
     */
    public Event() {
    }

    /**
     * Returns ALL events for ALL family members of the current user. The current
     * user is determined from the provided auth token.
     *
     * @return an event result object
     */
    public EventResult event(String token) {
        dao.AuthorizationToken aDao = new dao.AuthorizationToken();
        dao.Event eDao = new dao.Event();
        String message;
        EventResult result;
        if(aDao.getToken(token) != null) {
            ArrayList<model.Event> list = eDao.getAllEvents(aDao.getToken(token));
            model.Event[] array = list.toArray(new model.Event[0]);
            result = new EventResult(array);
        }else{
            message = "Invalid auth token.";
            result = new EventResult(message);
        }
        return result;
    }
}

package service;

import dao.Event;
import dao.Person;
import dao.User;
import result.EventIDResult;
import result.PersonIDResult;

/**
 * Created by samosbor on 5/18/18.
 */

public class EventID {
    public EventID() {
    }

    /**
     * Function to get an event by ID
     *
     * @return an EventIDResult result object
     */
    public EventIDResult getEvent(String token, String id) {
        dao.AuthorizationToken aDao = new dao.AuthorizationToken();
        dao.Event eDao = new Event();
        String message;
        EventIDResult result;
        if(aDao.getToken(token) != null) {
            String authUser = aDao.getToken(token).getUser();
            if(eDao.getEvent(id)!= null){
                model.Event outEvent = eDao.getEvent(id);
                if(outEvent.getDescendant().equals(authUser)){
                    result = new EventIDResult(outEvent);
                }else{
                    message = "Event doesn't belong to user";
                    result = new EventIDResult(message);
                }
            }else{
                message = "Invalid eventID";
                result = new EventIDResult(message);
            }
        }else{
            message = "Invalid auth token.";
            result = new EventIDResult(message);
        }
        return result;
    }
}

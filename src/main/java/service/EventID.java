package service;

import result.EventIDResult;

/**
 * Created by samosbor on 5/18/18.
 */

public class EventID {
    /**
     * The event ID passed in from the URL
     */
    String eventID;
    /**
     * The Auth token given in the URL
     */
    String token;

    /**
     * The constructor for the eventID service object
     */
    public EventID(String eventID, String token) {
        this.eventID = eventID;
        this.token = token;
    }

    /**
     * Function to get an event by ID
     * @return an EventIDResult result object
     */
    public EventIDResult getEvent(model.AuthorizationToken myToken){
        return null;
    }
}

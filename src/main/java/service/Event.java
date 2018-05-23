package service;

import result.EventResult;

/**
 * Created by samosbor on 5/18/18.
 */

public class Event {
    /**
     * The Auth token given in the URL
     */
    String token;
    /**
     * The constructor for the event service object
     */
    public Event(String token) {
        this.token = token;
    }

    /**
     *  Returns ALL events for ALL family members of the current user. The current
     user is determined from the provided auth token.
     * @return an event result object
     */
    public EventResult event(model.AuthorizationToken myToken){
        return null;
    }
}

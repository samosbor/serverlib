package result;

import model.Event;

/**
 * Created by samosbor on 5/18/18.
 */

public class EventIDResult {
    /**
     * The event to output in the response
     */
    Event event;
    /**
     * The message to output if failed
     */
    String message;

    /**
     * The constructor for the eventID result object
     *
     * @param event
     */
    public EventIDResult(Event event, String message) {
        this.event = event;
        this.message = message;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

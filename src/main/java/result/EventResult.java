package result;

/**
 * Created by samosbor on 5/18/18.
 */

public class EventResult {
    /**
     * An array of event objects to return
     */
    model.Event[] data;

    String message;

    /**
     * The constructor for the event result object
     *
     * @param data
     */
    public EventResult(model.Event[] data) {
        this.data = data;
    }

    public EventResult(String message) {
        this.message = message;
    }

    public model.Event[] getData() {
        return data;
    }

    public void setData(model.Event[] data) {
        this.data = data;
    }
}

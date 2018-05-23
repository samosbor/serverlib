package result;

/**
 * Created by samosbor on 5/18/18.
 */

public class FillResult {
    /**
     * The message to output if pass or fail
     */
    String message;
    /**
     * The number of persons added by the fill service
     */
    String numPersons;
    /**
     * The number of events added by the fill service
     */
    String numEvents;

    /**
     * Constructor for the fill result object
     * @param message
     * @param numPersons
     * @param numEvents
     */
    public FillResult(String message, String numPersons, String numEvents) {
        this.message = message;
        this.numPersons = numPersons;
        this.numEvents = numEvents;
    }
}

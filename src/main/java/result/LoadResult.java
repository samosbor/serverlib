package result;

/**
 * Created by samosbor on 5/18/18.
 */

public class LoadResult {
    /**
     * The message to output if pass or fail
     */
    String message;
    /**
     * Number of users added to database
     */
    int numUsers;
    /**
     * Number of persons added to database
     */
    int numPersons;
    /**
     * Number of events added to database
     */
    int numEvents;

    /**
     * Constructor for the load result object
     *
     * @param message
     * @param numUsers
     * @param numPersons
     * @param numEvents
     */
    public LoadResult(String message, int numUsers, int numPersons, int numEvents) {
        this.message = message;
        this.numUsers = numUsers;
        this.numPersons = numPersons;
        this.numEvents = numEvents;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getNumUsers() {
        return numUsers;
    }

    public void setNumUsers(int numUsers) {
        this.numUsers = numUsers;
    }

    public int getNumPersons() {
        return numPersons;
    }

    public void setNumPersons(int numPersons) {
        this.numPersons = numPersons;
    }

    public int getNumEvents() {
        return numEvents;
    }

    public void setNumEvents(int numEvents) {
        this.numEvents = numEvents;
    }
}

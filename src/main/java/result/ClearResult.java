package result;

/**
 * Created by samosbor on 5/18/18.
 */

public class ClearResult {
    /**
     * The message to output if pass or fail
     */
    String message;

    /**
     * The constructor for the clear result object
     *
     * @param message
     */
    public ClearResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

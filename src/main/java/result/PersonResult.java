package result;

/**
 * Created by samosbor on 5/18/18.
 */

public class PersonResult {
    /**
     * An array of person objects to return
     */
    model.Person[] data;

    String message;

    /**
     * The constructor for the person result object
     *
     * @param data
     */
    public PersonResult(model.Person[] data) {
        this.data = data;
    }

    public PersonResult(String message) {
        this.message = message;
    }

    public model.Person[] getData() {
        return data;
    }

    public void setData(model.Person[] data) {
        this.data = data;
    }
}

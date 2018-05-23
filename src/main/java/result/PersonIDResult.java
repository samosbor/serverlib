package result;

import model.Person;

/**
 * Created by samosbor on 5/18/18.
 */

public class PersonIDResult {
    /**
     * The person to output in the response
     */
    Person person;
    /**
     * The message to output if failed
     */
    String message;

    /**
     * The constructor for the personID result object
     * @param person
     */
    public PersonIDResult(Person person, String message) {
        this.person = person;
        this.message = message;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

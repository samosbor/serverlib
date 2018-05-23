package request;

import model.Event;
import model.Person;
import model.User;

/**
 * Created by samosbor on 5/18/18.
 */

public class LoadRequest {
    /**
     * An array of user objects
     */
    User[] users;
    /**
     * An array of person objects
     */
    Person[] persons;
    /**
     * An array of event objects
     */
    Event[] events;

    /**
     * The constructor for the load request object
     * @param users
     * @param persons
     * @param events
     */
    public LoadRequest(User[] users, Person[] persons, Event[] events) {
        this.users = users;
        this.persons = persons;
        this.events = events;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public Person[] getPersons() {
        return persons;
    }

    public void setPersons(Person[] persons) {
        this.persons = persons;
    }

    public Event[] getEvents() {
        return events;
    }

    public void setEvents(Event[] events) {
        this.events = events;
    }
}

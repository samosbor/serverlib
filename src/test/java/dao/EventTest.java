package dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class EventTest {
    Person myPersonDao;
    model.Person samplePerson;
    model.Person samplePerson2;
    model.Person samplePerson3;
    model.Person son;
    model.Person mom;
    model.Person dad;
    model.Person grandpa;
    model.Person grandma;

    model.Event samEvent;
    model.Event dadEvent;
    model.Event momEvent;
    model.Event grandpaEvent;
    model.Event grandmaEvent;
    model.Event test1;
    model.Event test2;
    Event myEventDao;

    @Before
    public void setUp() throws Exception {
        myPersonDao = new dao.Person();
        myPersonDao.clearTable();
        myPersonDao.createTable();
        samplePerson = new model.Person("petiwg", "r", "rod", "osborne", "m","manuel", "nancy", "nan");
        samplePerson2 = new model.Person("a", "a", "a", "a", "m","a", "a", "a");
        samplePerson3 = new model.Person("b", "b", "b", "b", "m","b", "b", "b");

        son = new model.Person("son", "sam", "sam", "osborne", "m","dad", "mom", "kaitlyn");
        mom = new model.Person("mom", "sam", "nan", "osborne", "f","duane", "juanita", "dad");
        dad = new model.Person("dad", "sam", "rod", "osborne", "m","grandpa", "grandma", "mom");
        grandpa = new model.Person("grandpa", "sam", "manuel", "osborne", "m","1", "2", "grandma");
        grandma = new model.Person("grandma", "sam", "nancy", "osborne", "f","3", "4", "grandpa");
        myPersonDao.addPerson(son);
        myPersonDao.addPerson(mom);
        myPersonDao.addPerson(dad);
        myPersonDao.addPerson(grandpa);
        myPersonDao.addPerson(grandma);

        myEventDao = new Event();
        samEvent = new model.Event("son", "sam", "son", 45.0, 45.0, "US", "Provo", "Death", 2018);
        dadEvent = new model.Event("mom", "sam", "mom", 45.0, 45.0, "US", "Provo", "Death", 1);
        momEvent = new model.Event("dad", "sam", "dad", 45.0, 45.0, "US", "Provo", "Death", 2);
        grandpaEvent = new model.Event("grandpa", "sam", "grandpa", 45.0, 45.0, "US", "Provo", "Death", 3);
        grandmaEvent = new model.Event("grandma", "sam", "grandma", 45.0, 45.0, "US", "Provo", "Death", 4);
        test1 = new model.Event("1", "sam", "son", 45.0, 45.0, "US", "Provo", "Death", 6);
        test2 = new model.Event("2", "notsam", "notson", 45.0, 45.0, "US", "Provo", "Death", 5);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createTable() {
        myEventDao.createTable();
    }

    @Test
    public void addEvent() {
        myEventDao.addEvent(test1);
        assertEquals(myEventDao.getEvent("1"), test1);
        assertNotEquals(myEventDao.getEvent("1"), test2);
        myEventDao.addEvent(test1);
        assertEquals(myEventDao.getEvent("1"), test1);
        assertNotEquals(myEventDao.getEvent("1"), test2);
        myEventDao.addEvent(test2);
        assertEquals(myEventDao.getEvent("2"), test2);
        assertNotEquals(myEventDao.getEvent("2"), test1);
    }

    @Test
    public void removeEvent() {
        myEventDao.addEvent(test1);
        assertEquals(myEventDao.getEvent("1"), test1);
        myEventDao.removeEvent(test1);
        assertNotEquals(myEventDao.getEvent("1"), test1);

    }

    @Test
    public void getEvent() {
        myEventDao.addEvent(test1);
        myEventDao.addEvent(test2);
        assertEquals(myEventDao.getEvent("1"), test1);
        assertEquals(myEventDao.getEvent("2"), test2);
    }

    @Test
    public void clearTable() {
        myEventDao.addEvent(test1);
        myEventDao.addEvent(test2);
        assertEquals(myEventDao.getEvent("1"), test1);
        assertEquals(myEventDao.getEvent("2"), test2);
        myEventDao.clearTable();
        assertNotEquals(myEventDao.getEvent("1"), test1);
        assertNotEquals(myEventDao.getEvent("2"), test2);

    }

    @Test
    public void getAllEvents() {
        dao.User userDao = new User();
        dao.AuthorizationToken aDao= new dao.AuthorizationToken();
        userDao.createTable();
        aDao.createTable();
        model.User sam = new model.User("sam", "idk", "idk", "sam", "osborne", "m", "son");
        userDao.addUser(sam);
        model.AuthorizationToken samToken = new model.AuthorizationToken("randomstring", "sam");

        myEventDao.addEvent(samEvent);
        myEventDao.addEvent(dadEvent);
        myEventDao.addEvent(momEvent);
        myEventDao.addEvent(grandmaEvent);
        myEventDao.addEvent(grandpaEvent);

        ArrayList<model.Event> allEventList = myEventDao.getAllEventsByUsername("sam");
        System.out.println(allEventList.toString());
    }
}
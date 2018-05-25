package dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import model.*;
import model.AuthorizationToken;

import static org.junit.Assert.*;

public class PersonTest {
    Person myPersonDao;
    model.Person samplePerson;
    model.Person samplePerson2;
    model.Person samplePerson3;
    model.Person son;
    model.Person mom;
    model.Person dad;
    model.Person grandpa;
    model.Person grandma;

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
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createTable() {
        myPersonDao.createTable();
    }

    @Test
    public void addPerson() {
        myPersonDao.addPerson(samplePerson);
        assertEquals(samplePerson, myPersonDao.getPerson("petiwg"));
        assertNotEquals(samplePerson, myPersonDao.getPerson("swag"));
        assertEquals(samplePerson, myPersonDao.getPerson("petiwg"));
        System.out.println(myPersonDao.getPerson("petiwg").toString());
    }

    @Test
    public void removePerson() {
        myPersonDao.addPerson(samplePerson);
        assertEquals(samplePerson, myPersonDao.getPerson("petiwg"));
        myPersonDao.removePerson(samplePerson);
        assertNotEquals(samplePerson, myPersonDao.getPerson("petiwg"));
    }

    @Test
    public void getPerson() {
        myPersonDao.addPerson(samplePerson);
        myPersonDao.addPerson(samplePerson2);
        myPersonDao.addPerson(samplePerson3);
        assertEquals(samplePerson, myPersonDao.getPerson("petiwg"));
        assertEquals(samplePerson2, myPersonDao.getPerson("a"));
        assertEquals(samplePerson3, myPersonDao.getPerson("b"));
        assertNotEquals(samplePerson3, myPersonDao.getPerson("a"));

    }

    @Test
    public void clearTable() {
        myPersonDao.createTable();
        myPersonDao.addPerson(samplePerson);
        myPersonDao.addPerson(samplePerson2);
        myPersonDao.addPerson(samplePerson3);
        myPersonDao.clearTable();
        assertNotEquals(samplePerson, myPersonDao.getPerson("petiwg"));
        assertNotEquals(samplePerson2, myPersonDao.getPerson("a"));
        assertNotEquals(samplePerson3, myPersonDao.getPerson("b"));
    }

    @Test
    public void getAllFamily() throws Exception {
        dao.User userDao = new User();
        dao.AuthorizationToken aDao= new dao.AuthorizationToken();
        userDao.createTable();
        aDao.createTable();
        model.User sam = new model.User("sam", "idk", "idk", "sam", "osborne", "m", "son");
        userDao.addUser(sam);

        myPersonDao.addPerson(son);
        myPersonDao.addPerson(mom);
        myPersonDao.addPerson(dad);
        myPersonDao.addPerson(grandma);
        myPersonDao.addPerson(grandpa);
        model.AuthorizationToken samToken = new AuthorizationToken("randomstring", "sam");
        ArrayList<model.Person> allfamlist = myPersonDao.getAllFamily(samToken);
        System.out.println(allfamlist.toString());
    }
}
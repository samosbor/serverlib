package dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AuthorizationTokenTest {

    dao.User userDao = new User();
    dao.AuthorizationToken aDao= new dao.AuthorizationToken();
    dao.Person myPersonDao = new dao.Person();
    model.Person son = new model.Person("son", "sam", "sam", "osborne", "m","rod", "nan", "kaitlyn");
    model.Person mom = new model.Person("mom", "sam", "nan", "osborne", "f","duane", "juanita", "dad");
    model.Person dad = new model.Person("dad", "sam", "rod", "osborne", "m","grandpa", "grandma", "mom");
    model.Person grandpa = new model.Person("grandpa", "sam", "manuel", "osborne", "m","1", "2", "grandma");
    model.Person grandma = new model.Person("grandma", "sam", "nancy", "osborne", "f","3", "4", "grandpa");
    model.AuthorizationToken samToken = new model.AuthorizationToken("randomstring", "sam");
    ArrayList<model.Person> allfamlist = myPersonDao.getAllFamily(samToken);

    @Before
    public void setUp() throws Exception {
        myPersonDao.addPerson(son);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createTable() { aDao.createTable();
    }

    @Test
    public void addToken() {
        aDao.addToken(samToken);
        aDao.getToken("randomstring");
        assertEquals(samToken, aDao.getToken("randomstring"));
    }

    @Test
    public void removeToken() {
        aDao.addToken(samToken);
        aDao.getToken("randomstring");
        assertEquals(samToken, aDao.getToken("randomstring"));
        aDao.removeToken(samToken);
        aDao.clearTable();
        assertNotEquals(samToken, aDao.getToken("randomstring"));
    }

    @Test
    public void getToken() {
        aDao.addToken(samToken);
        aDao.getToken("randomstring");
        assertEquals(samToken, aDao.getToken("randomstring"));
    }

    @Test
    public void clearTable() {
        aDao.addToken(samToken);
        aDao.getToken("randomstring");
        assertEquals(samToken, aDao.getToken("randomstring"));
        aDao.clearTable();
        assertNotEquals(samToken, aDao.getToken("randomstring"));
    }
}
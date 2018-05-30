package dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    User myuser = new User();
    model.User sampleUser2 = new model.User("samuel", "toast", "samgmailcom", "sam", "osborne", "m", "do");
    model.User sampleUser = new model.User("sam", "toast", "samgmailcom", "sam", "osborne", "m", "do");

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createTable() { myuser.createTable();
    }

    @Test
    public void addUser() {

        myuser.addUser(sampleUser);
        myuser.addUser(sampleUser2);
        myuser.getUser("samuel");
        myuser.getUser("samuel");
        myuser.getUser("sam");
        myuser.getUser("sam");
    }

    @Test
    public void removeUser() {
        myuser.clearTable();
        myuser.createTable();
        myuser.addUser(sampleUser);
        myuser.addUser(sampleUser2);
        myuser.removeUser(sampleUser);
        myuser.removeUser(sampleUser2);
        assertNull(myuser.getUser("samuel"));
        assertNull(myuser.getUser("samuel"));
    }

    @Test
    public void getUser() {
        myuser.addUser(sampleUser);
        myuser.addUser(sampleUser2);
        myuser.getUser("samuel");
        myuser.getUser("samuel");
        myuser.getUser("sam");
        myuser.getUser("sam");
    }

    @Test
    public void clearTable() {myuser.clearTable();
    }
}
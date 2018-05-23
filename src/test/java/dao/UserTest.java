package dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class UserTest {
    User myuser = new User();
    model.User sampleUser2 = new model.User("samuel", "toast", "samgmailcom","sam", "osborne", "m", "do" );
    model.User sampleUser = new model.User("sam", "toast", "samgmailcom","sam", "osborne", "m", "do" );

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createTable() {
        myuser.createTable();
        assertTrue(new File("mydata.db").isFile());
    }

    @Test
    public void addUser() {
    }

    @Test
    public void removeUser() {
    }

    @Test
    public void getUser() {
    }

    @Test
    public void clearTable() {
    }
}
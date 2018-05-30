package model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
    model.User sampleUser2 = new model.User("samuel", "toast", "samgmailcom", "sam", "osborne", "m", "do");
    model.User sampleUser = new model.User("sam", "toast", "samgmailcom", "sam", "osborne", "m", "do");

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getUsername() {
        sampleUser.getUserName();
    }

    @Test
    public void setUsername() {
        sampleUser.setUserName("notsam");
    }

    @Test
    public void getPassword() {
        sampleUser.getPassword();
    }

    @Test
    public void setPassword() {
        sampleUser.setPassword("notsampass");
    }

    @Test
    public void getEmail() {
        sampleUser.getEmail();
    }

    @Test
    public void setEmail() {
        sampleUser.setEmail("notemal");
    }

    @Test
    public void getFirstName() {
        sampleUser.getFirstName();
    }

    @Test
    public void setFirstName() {
        sampleUser.setFirstName("notfirst");
    }

    @Test
    public void getLastName() {
        sampleUser.getLastName();
    }

    @Test
    public void setLastName() {
        sampleUser.setLastName("notlast");
    }

    @Test
    public void getGender() {
        sampleUser.getGender();
    }

    @Test
    public void setGender() {
        sampleUser.setGender("f");
    }

    @Test
    public void getPersonID() {
        sampleUser.getPersonID();
    }

    @Test
    public void setPersonID() {
        sampleUser.setPersonID("dsgasg");
    }

    @Test
    public void equals() {
        Assert.assertNotEquals(sampleUser, sampleUser2);
    }
}
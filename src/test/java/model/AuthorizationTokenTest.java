package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class AuthorizationTokenTest {
    model.AuthorizationToken test = new AuthorizationToken("test1", "user");
    model.AuthorizationToken test2 = new AuthorizationToken("test2", "user2");


    @Test
    public void getToken() {
        assertEquals(test.getToken(),"test1");
    }

    @Test
    public void setToken() {
        test.setToken("nottest1");
        assertEquals(test.getToken(),"nottest1");
    }

    @Test
    public void equals() {
        assertFalse(test.equals(test2));
    }
}
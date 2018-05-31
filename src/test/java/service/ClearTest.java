package service;

import org.junit.Test;

import dao.User;
import request.RegisterRequest;

import static org.junit.Assert.*;

public class ClearTest {

    Register test = new Register();
    Clear clearTest = new Clear();
    dao.User uDao = new User();
    RegisterRequest request = new RegisterRequest("test1", "test2", "test3", "test4", "test5", "m");
    RegisterRequest request2 = new RegisterRequest("test2", "test2", "test3", "test4", "test5", "m");


    @Test
    public void clear() {
        test.register(request);
        assertNotNull(uDao.getUser("test1"));
        test.register(request);
        test.register(request2);
        assertNotNull(uDao.getUser("test2"));

        clearTest.clear();
        assertNull(uDao.getUser("test1"));
        assertNull(uDao.getUser("test2"));

    }
}
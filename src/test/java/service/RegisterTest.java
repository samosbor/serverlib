package service;

import org.junit.Test;

import dao.User;
import request.RegisterRequest;

import static org.junit.Assert.*;

public class RegisterTest {

    Register test = new Register();
    dao.User uDao = new User();
    RegisterRequest request = new RegisterRequest("test1", "test2", "test3", "test4", "test5", "m");
    RegisterRequest request2 = new RegisterRequest("test2", "test2", "test3", "test4", "test5", "m");

    @Test
    public void register() {
        test.register(request);
        assertNotNull(uDao.getUser("test1"));
        test.register(request);
        test.register(request2);
        assertNotNull(uDao.getUser("test2"));
    }
}
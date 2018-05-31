package service;

import org.junit.Test;

import dao.User;
import request.LoginRequest;
import request.RegisterRequest;

import static org.junit.Assert.*;

public class LoginTest {
    Register test = new Register();
    Login logtest = new Login();
    dao.User uDao = new User();
    RegisterRequest request = new RegisterRequest("test1", "test2", "test3", "test4", "test5", "m");
    RegisterRequest request2 = new RegisterRequest("test2", "test2", "test3", "test4", "test5", "m");

    LoginRequest logRequest = new LoginRequest("test1", "test2");
    LoginRequest logRequest2 = new LoginRequest("test2", "test2");

    @Test
    public void login() {
        test.register(request);
        test.register(request2);

        logtest.login(logRequest);
        assertEquals(logtest.login(logRequest).getUsername(), "test1");
        assertEquals(logtest.login(logRequest2).getUsername(), "test2");

    }
}
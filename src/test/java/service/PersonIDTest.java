package service;

import org.junit.Test;

import dao.User;
import request.LoginRequest;
import request.RegisterRequest;
import result.LoginResult;
import result.PersonResult;
import result.RegisterResult;

import static org.junit.Assert.*;

public class PersonIDTest {

    Register regtest = new Register();
    dao.User uDao = new User();
    RegisterRequest request = new RegisterRequest("test1", "test2", "test3", "test4", "test5", "m");
    RegisterRequest request2 = new RegisterRequest("test2", "test2", "test3", "test4", "test5", "m");
    Person persontest = new Person();

    @Test
    public void person() {
        RegisterResult registerResult = regtest.register(request);
        LoginRequest loginRequest = new LoginRequest("test1", "test2");
        Login login = new Login();
        LoginResult loginResult = login.login(loginRequest);
        PersonResult result = persontest.person(loginResult.getAuthToken());
        assertTrue(result.getData().length == 31);
    }
}
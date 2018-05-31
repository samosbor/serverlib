package service;

import org.junit.Test;

import request.LoginRequest;
import request.RegisterRequest;
import result.EventResult;
import result.LoginResult;

import static org.junit.Assert.*;

public class EventIDTest {
    Register regtest = new Register();
    RegisterRequest request = new RegisterRequest("test1", "test2", "test3", "test4", "test5", "m");
    RegisterRequest request2 = new RegisterRequest("test2", "test2", "test3", "test4", "test5", "m");
    dao.Event eDao = new dao.Event();
    service.Event etest = new service.Event();

    EventID eidtest = new EventID();

    @Test
    public void getEvent() {
        regtest.register(request);

        LoginRequest loginRequest = new LoginRequest("test1", "test2");
        Login login = new Login();
        LoginResult loginResult = login.login(loginRequest);
        String correctAuth = loginResult.getAuthToken();

        EventResult result = etest.event(correctAuth);
        String sample = result.getData()[1].getEventID();

        assertEquals(eidtest.getEvent(correctAuth, sample).getEvent().getEventID(), sample);
    }
}
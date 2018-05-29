package service;

import java.util.UUID;

import model.AuthorizationToken;
import request.LoginRequest;
import result.LoginResult;
import result.RegisterResult;

/**
 * Created by samosbor on 5/18/18.
 */

public class Login {
    /**
     * The constructor for the login service object. Takes no parameters.
     */
    public Login() {
    }

    /**
     * The function to take in a login request, validate it, perform the login actions, and return a result object
     *
     * @param request a login request object
     * @return the login object which will contain pass or fail response
     */
    public LoginResult login(LoginRequest request) {
        dao.User userDao = new dao.User();
        userDao.createTable();
        dao.Person personDao = new dao.Person();
        personDao.createTable();
        dao.AuthorizationToken ATDao = new dao.AuthorizationToken();
        ATDao.createTable();
        LoginResult result;

        if(userDao.getUser(request.getUsername()) == null) {
            String message = "That username is not valid";
            result = new LoginResult(message);
        }
        else{
            if(userDao.getUser(request.getUsername()).getPassword().equals(request.getPassword())){
                //successful login
                String authToken = UUID.randomUUID().toString();
                model.AuthorizationToken token = new AuthorizationToken(authToken, request.getUsername());
                ATDao.addToken(token);
                String personID = userDao.getUser(request.getUsername()).getPersonID();

                result = new LoginResult(authToken,request.getUsername(),personID);
            }
            else{
                String message = "That password is incorrect";
                result = new LoginResult(message);
            }

        }
        return result;
    }
}

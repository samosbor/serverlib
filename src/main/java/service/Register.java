package service;

import java.util.UUID;

import dao.Person;
import request.RegisterRequest;
import result.RegisterResult;

public class Register {
    /**
     * The constructor for the register service object. Takes no parameters.
     */
    public Register() {
    }

    /**
     * The function to take in a register request, validate it, perform the register actions, and return a result object
     *
     * @param request a register request object
     * @return the result object which will contain pass or fail response
     */
    public RegisterResult register(RegisterRequest request) {
        dao.User userDao = new dao.User();
        userDao.createTable();
        dao.Person personDao = new dao.Person();
        personDao.createTable();
        dao.AuthorizationToken ATDao = new dao.AuthorizationToken();
        ATDao.createTable();
        RegisterResult result;

        if(userDao.getUser(request.getUsername()) == null){
            String newPersonID = UUID.randomUUID().toString();
            model.User newUser = new model.User(request.getUsername(), request.getPassword(), request.getEmail(), request.getFirstName(), request.getLastName(), request.getGender(), newPersonID);
            userDao.addUser(newUser);
            model.Person newPerson = new model.Person(newPersonID, request.getUsername(), request.getFirstName(), request.getLastName(), request.getGender() );
            personDao.addPerson(newPerson);
            String authToken = UUID.randomUUID().toString();
            model.AuthorizationToken newToken = new model.AuthorizationToken(authToken, request.getUsername());
            ATDao.addToken(newToken);

            result = new RegisterResult(authToken, request.getUsername(), newPersonID);
        }
        else{
            String message = "That username is taken";
            result = new RegisterResult(message);
        }



        return result;
    }

}

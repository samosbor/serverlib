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
        if(validate(request) != null){
            return new RegisterResult(validate(request));
        }

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
            return result;
        }


        Fill fillService = new Fill();
        fillService.fill(request.getUsername(), 4);
        return result;
    }

    private String validate(RegisterRequest request){
        if(request.getUsername() == null || request.getUsername() == ""){
            return "No userName given";
        }
        if(request.getPassword() == null|| request.getPassword() == ""){
            return "No password given";
        }
        if(request.getEmail() == null|| request.getEmail() == ""){
            return "No email given";
        }
        if(request.getFirstName() == null|| request.getFirstName() == ""){
            return "No firstName given";
        }
        if(request.getLastName() == null|| request.getLastName() == ""){
            return "No lastName given";
        }
        if(request.getGender() == null|| request.getGender() == ""){
            return "No gender given";
        }
        if(!request.getGender().equals("m") && !request.getGender().equals("f")){
            return "That gender is incorrect";
        }

        return null;
    }

}

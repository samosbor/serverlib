package service;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import dao.Event;
import dao.Person;
import dao.User;
import randoms.FemaleNames;
import randoms.LastNames;
import randoms.Location;
import randoms.Locations;
import randoms.MaleNames;
import result.FillResult;

/**
 * Created by samosbor on 5/18/18.
 */

public class Fill {
    MaleNames mnames;
    FemaleNames fnames;
    Locations locations;
    LastNames lnames;
    model.Person original;
    String username;
    ArrayList<model.Person> fullList;
    Integer numPersons;
    Integer numEvents;

    public Fill() {
    }

    public FillResult fill(String user, Integer gens){
        username = user;
        numEvents = 0;
        numPersons = 0;
        FillResult result;
        dao.Person pDao = new Person();
        dao.User uDao = new User();
        dao.Event eDao = new Event();
        Gson gson = new Gson();
        String filePath = new File("").getAbsolutePath();
        try {
            FileReader male = new FileReader(filePath + "\\serverlib\\json\\mnames.json");
            mnames = gson.fromJson(male, MaleNames.class);
            FileReader female = new FileReader(filePath + "\\serverlib\\json\\fnames.json");
            fnames = gson.fromJson(female, FemaleNames.class);
            FileReader loc = new FileReader(filePath + "\\serverlib\\json\\locations.json");
            locations = gson.fromJson(loc, Locations.class);
            FileReader last = new FileReader(filePath + "\\serverlib\\json\\snames.json");
            lnames = gson.fromJson(last, LastNames.class);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if(uDao.getUser(user) == null || user.equals("")){
            result = new FillResult("That is an invalid userName");
            System.out.println("fail1");
            return result;
        }
        if(gens < 0){
            result = new FillResult("That is an invalid amount of generations");
            System.out.println("fail2");
            return result;
        }
        original = pDao.getPerson(uDao.getUser(user).getPersonID());
        original.setMother(null);
        original.setFather(null);
        original.setSpouse(null);
        deleteUserData(original);
        fullList = new ArrayList<>();
        HashMap<Integer, ArrayList<model.Person>>genMap = new HashMap<>();
        ArrayList<model.Person> gen0 = new ArrayList<>();
        gen0.add(original);
        genMap.put(0,gen0);

        for(int i = 1; i <= gens; i++){
            if(genMap.get(i) != null) {
                ArrayList<model.Person> genList = genMap.get(i);
            }else{
                ArrayList<model.Person> newGen = new ArrayList<>();
                genMap.put(i,newGen);
            }

            for(model.Person element : genMap.get(i-1)){
                ArrayList<model.Person> parents = addParents(element);
                genMap.get(i).addAll(parents);
            }
        }
        System.out.println(genMap.toString());
        pDao.removePerson(original);
        for(ArrayList<model.Person> element: genMap.values()){
            for(model.Person addPerson : element){
                if(addPerson.getPersonID().equals(original.getPersonID())){
                    Location randomloc = locations.getRandom();
                    model.Event birth = new model.Event(UUID.randomUUID().toString(), username, original.getPersonID(), randomloc.getLatitude(), randomloc.getLongitude(), randomloc.getCountry(), randomloc.getCity(), "Birth", 1996);
                    eDao.addEvent(birth);
                }
                pDao.addPerson(addPerson);
            }
        }



        String message = "Successfully added "+Integer.toString(numPersons)+" persons and "+Integer.toString(numEvents)+" events to the database.";
        result = new FillResult(message);
        return result;
    }

    private ArrayList<model.Person> addParents(model.Person person){
        dao.Person pDao = new Person();
        dao.Event eDao = new Event();
        String dadID = UUID.randomUUID().toString();
        String momID = UUID.randomUUID().toString();
        String dadName = mnames.getRandom();
        String momName = fnames.getRandom();
        String momLast = lnames.getRandom();
        model.Person dad = new model.Person(dadID, username, dadName, person.getLastName(), "m");
        dad.setSpouse(momID);
        model.Person mom = new model.Person(momID, username, momName, momLast, "f");
        mom.setSpouse(dadID);
        person.setFather(dadID);
        person.setMother(momID);
        ArrayList<model.Person> outList= new ArrayList<>();
        outList.add(dad);
        outList.add(mom);



        Integer personBirth = 1996;
        ArrayList<model.Event> personEvents = eDao.getEvents(person);
        for(model.Event element : personEvents){
            if (element.getEventType().equals("Birth")){
                personBirth = element.getYear();
            }
        }
        Integer marriageYear = ThreadLocalRandom.current().nextInt(personBirth-25, personBirth);
        Location marriageLoc = locations.getRandom();
        model.Event dadMarriage = new model.Event(UUID.randomUUID().toString(), username, dadID, marriageLoc.getLatitude(), marriageLoc.getLongitude(), marriageLoc.getCountry(), marriageLoc.getCity(), "Marriage", marriageYear);
        model.Event momMarriage = new model.Event(UUID.randomUUID().toString(), username, momID, marriageLoc.getLatitude(), marriageLoc.getLongitude(), marriageLoc.getCountry(), marriageLoc.getCity(), "Marriage", marriageYear);
        eDao.addEvent(dadMarriage);
        eDao.addEvent(momMarriage);

        Integer momBirthYear = ThreadLocalRandom.current().nextInt(marriageYear-30, marriageYear-15);
        Integer dadBirthYear = ThreadLocalRandom.current().nextInt(marriageYear-30, marriageYear-20);
        Location dadBirthLoc = locations.getRandom();
        Location momBirthLoc = locations.getRandom();
        model.Event dadBirth = new model.Event(UUID.randomUUID().toString(), username, dadID, dadBirthLoc.getLatitude(), dadBirthLoc.getLongitude(), dadBirthLoc.getCountry(), dadBirthLoc.getCity(), "Birth", dadBirthYear);
        model.Event momBirth = new model.Event(UUID.randomUUID().toString(), username, momID, momBirthLoc.getLatitude(), momBirthLoc.getLongitude(), momBirthLoc.getCountry(), momBirthLoc.getCity(), "Birth", momBirthYear);
        eDao.addEvent(dadBirth);
        eDao.addEvent(momBirth);

        Integer momBapYear = ThreadLocalRandom.current().nextInt(momBirthYear, momBirthYear+16);
        Integer dadBapYear = ThreadLocalRandom.current().nextInt(dadBirthYear, dadBirthYear+8);
        Location dadBapLoc = locations.getRandom();
        Location momBapLoc = locations.getRandom();
        model.Event dadBap = new model.Event(UUID.randomUUID().toString(), username, dadID, dadBapLoc.getLatitude(), dadBapLoc.getLongitude(), dadBapLoc.getCountry(), dadBapLoc.getCity(), "Baptism", dadBapYear);
        model.Event momBap = new model.Event(UUID.randomUUID().toString(), username, momID, momBapLoc.getLatitude(), momBapLoc.getLongitude(), momBapLoc.getCountry(), momBapLoc.getCity(), "Baptism", momBapYear);
        eDao.addEvent(dadBap);
        eDao.addEvent(momBap);

        Integer momDeathYear = ThreadLocalRandom.current().nextInt(marriageYear+1, marriageYear+50);
        Integer dadDeathYear = ThreadLocalRandom.current().nextInt(marriageYear+1, marriageYear+50);
        Location dadDeathLoc = locations.getRandom();
        Location momDeathLoc = locations.getRandom();
        model.Event dadDeath = new model.Event(UUID.randomUUID().toString(), username, dadID, dadDeathLoc.getLatitude(), dadDeathLoc.getLongitude(), dadDeathLoc.getCountry(), dadDeathLoc.getCity(), "Death", dadDeathYear);
        model.Event momDeath = new model.Event(UUID.randomUUID().toString(), username, momID, momDeathLoc.getLatitude(), momDeathLoc.getLongitude(), momDeathLoc.getCountry(), momDeathLoc.getCity(), "Death", momDeathYear);
        eDao.addEvent(dadDeath);
        eDao.addEvent(momDeath);

        numEvents = numEvents + 8;
        numPersons = numPersons + 2;
        return outList;
    }

    private void deleteUserData(model.Person user){
        dao.Person pDao = new Person();
        dao.Event eDao = new Event();
        ArrayList<model.Person> family = pDao.getAllFamily(user);
        System.out.println("Deleting Data");
        System.out.println(family.toString());
        for(model.Person element : family){
            pDao.removePerson(element);
        }
        ArrayList<model.Event> eventlist = eDao.getAllEventsByUsername(user.getDescendant());
        for(model.Event element : eventlist){
            eDao.removeEvent(element);
        }
    }

}

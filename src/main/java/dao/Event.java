package dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.*;

public class Event {

    /**
     * Constructor for the Event Dao
     */
    public Event() {
    }

    /**
     * Creates an empty event table
     */
    public void createTable() {
        try (Connection conn = this.connect()) {
            String sql = "CREATE TABLE IF NOT EXISTS \"Event\" ( `EventID` TEXT NOT NULL UNIQUE, `Descendant` TEXT NOT NULL, `Person` TEXT NOT NULL, `Latitude` TEXT, `Longitude` TEXT, `Country` TEXT, `City` TEXT, `EventType` TEXT NOT NULL, `Year` INTEGER, FOREIGN KEY(`Descendant`) REFERENCES `User`(`Username`), FOREIGN KEY(`Person`) REFERENCES `Person`(`PersonID`), PRIMARY KEY(`EventID`) )";
            Statement stmt = conn.createStatement();
            stmt.execute(sql);

            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Connection connect() {
        String filePath = new File("").getAbsolutePath() + "\\mydata.db";
        String url = "jdbc:sqlite:" + filePath;
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * Adds a specified event to the table
     *
     * @param event
     */
    public void addEvent(model.Event event) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO Event (EventID, Descendant, Person, Latitude, Longitude, Country, City, EventType, Year) VALUES (");
        sb.append("'" + event.getEventID() + "'");
        sb.append(", ");
        sb.append("'" + event.getDescendant() + "'");
        sb.append(", ");
        sb.append("'" + event.getPerson() + "'");
        sb.append(", ");
        sb.append("'" + event.getLatitude() + "'");
        sb.append(",");
        sb.append("'" + event.getLongitude() + "'");
        sb.append(",");
        sb.append("'" + event.getCountry() + "'");
        sb.append(",");
        sb.append("'" + event.getCity() + "'");
        sb.append(",");
        sb.append("'" + event.getEventType() + "'");
        sb.append(",");
        sb.append("'" + event.getYear() + "'");
        sb.append(")");
        String sql = sb.toString();
        try (Connection conn = this.connect();
             Statement pstmt = conn.createStatement()) {
            pstmt.execute(sql);
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Removes a specified event
     *
     * @param event
     */
    public void removeEvent(model.Event event) {
        String sqlDel = "DELETE FROM Event WHERE EventID = \"" + event.getEventID() + "\"";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sqlDel);
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Get a event by eventID
     *
     * @param eventID
     * @return the event object that corresponds with the eventID
     */
    public model.Event getEvent(String eventID) {
        String sqlGet = "SELECT * FROM Event WHERE EventID = \"" + eventID + "\"";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            ResultSet res = stmt.executeQuery(sqlGet);
            String ID = res.getString("eventID");
            String descendant = res.getString("Descendant");
            String person = res.getString("person");
            Double latitude = res.getDouble("latitude");
            Double longitude = res.getDouble("longitude");
            String country = res.getString("country");
            String city = res.getString("city");
            String eventType = res.getString("eventType");
            Integer year = res.getInt("year");
            model.Event outEvent = new model.Event(ID, descendant, person, latitude, longitude, country, city, eventType, year);
            conn.close();
            return outEvent;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Clears the table by dropping it then recreating it
     */
    public void clearTable() {
        String drop = "DROP TABLE Event";
        try (Connection conn = this.connect();
             Statement pstmt = conn.createStatement()) {
            pstmt.executeUpdate(drop);
            pstmt.close();
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        this.createTable();
    }

    public ArrayList<model.Event> getAllEvents(model.AuthorizationToken token){
        model.Person currentPerson = getPersonFromAT(token);

        ArrayList<model.Event> initialList = new ArrayList<>();
        ArrayList<model.Person> personList = new ArrayList<>();
        return allEventsDFS(currentPerson, initialList, personList);

    }
    public ArrayList<model.Event> getAllEvents(model.Person currentPerson){

        ArrayList<model.Event> initialList = new ArrayList<>();
        ArrayList<model.Person> personList = new ArrayList<>();
        return allEventsDFS(currentPerson, initialList, personList);

    }


    private model.Person getPersonFromAT(model.AuthorizationToken token){
        String currentUsername = token.getUser();
        dao.User userDao = new dao.User();
        model.User currentUser = userDao.getUser(currentUsername);
        String currentPersonId = currentUser.getPersonID();
        System.out.println(currentPersonId);
        dao.Person personDao = new dao.Person();
        return personDao.getPerson(currentPersonId);
    }

    private ArrayList<model.Event> allEventsDFS(model.Person person, ArrayList<model.Event> list, ArrayList<model.Person> personList ){
        if(!personList.contains(person)){
            personList.add(person);
        }
        for(model.Event element : getEvents(person)){
            if(!list.contains(element)){
                list.add(element);
            }
        }
        dao.Person personDao = new dao.Person();

        if(person.getFather() != null && personDao.getPerson(person.getFather()) != null){
            if(!personList.contains(personDao.getPerson(person.getFather()))) {
                allEventsDFS(personDao.getPerson(person.getFather()), list, personList);
            }
        }
        if(person.getMother() != null && personDao.getPerson(person.getMother()) != null){
            if(!personList.contains(personDao.getPerson(person.getMother()))) {
                allEventsDFS(personDao.getPerson(person.getMother()), list, personList);
            }
        }
        if(person.getSpouse() != null&& personDao.getPerson(person.getSpouse()) != null){
            if(!personList.contains(personDao.getPerson(person.getSpouse()))) {
                allEventsDFS(personDao.getPerson(person.getSpouse()), list, personList);
            }
        }
        return list;
    }

    public ArrayList<model.Event> getEvents(model.Person person){
        ArrayList<model.Event> outList = new ArrayList<>();
        String sqlGet = "SELECT EventID FROM Event WHERE Person = \"" + person.getPersonID() + "\"";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            ResultSet res = stmt.executeQuery(sqlGet);
            while(res.next()){
                String eventID = res.getString("EventID");
                model.Event event = this.getEvent(eventID);
                outList.add(event);
            }
            conn.close();
            return outList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<model.Event> getAllEventsByUsername(String username){
        ArrayList<model.Event> outList = new ArrayList<>();
        String sqlGet = "SELECT EventID FROM Event WHERE Descendant = \"" + username + "\"";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            ResultSet res = stmt.executeQuery(sqlGet);
            while(res.next()){
                String eventID = res.getString("EventID");
                model.Event event = this.getEvent(eventID);
                outList.add(event);
            }
            conn.close();
            return outList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}

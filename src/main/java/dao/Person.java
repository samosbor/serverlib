package dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Person {

    /**
     * Constructor for the Person Dao
     */
    public Person() {
    }


    public static void main(String[] args) {
        Person myPersonDao = new dao.Person();
        myPersonDao.createTable();
        model.Person samplePerson = new model.Person("petiwg", "samuel", "rod", "osborne", "m","manuel", "nancy", "nan");
        myPersonDao.addPerson(samplePerson);
    }

    /**
     * Creates an empty person table
     */
    public void createTable() {
        try (Connection conn = this.connect()) {
            if (conn != null) {
                System.out.println("A new database has been created.");
            }

            String sql = "CREATE TABLE IF NOT EXISTS \"Person\" ( `PersonID` TEXT NOT NULL UNIQUE, `Descendant` TEXT NOT NULL, `FirstName` TEXT NOT NULL, `LastName` TEXT NOT NULL, `Gender` TEXT NOT NULL CHECK(Gender == \"m\" OR Gender == \"f\"), `Father` TEXT, `Mother` TEXT, `Spouse` TEXT, FOREIGN KEY(`Descendant`) REFERENCES `User`(`Username`), PRIMARY KEY(`PersonID`) )";

            Statement stmt = conn.createStatement();
            // create a new table
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
     * Adds a specified person to the table
     *
     * @param person
     */
    public void addPerson(model.Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO Person (PersonID,Descendant,FirstName,LastName,Gender,Father, Mother, Spouse) VALUES (");
        sb.append("'" + person.getPersonID() + "'");
        sb.append(", ");
        sb.append("'" + person.getDescendant() + "'");
        sb.append(", ");
        sb.append("'" + person.getFirstName() + "'");
        sb.append(", ");
        sb.append("'" + person.getLastName() + "'");
        sb.append(",");
        sb.append("'" + person.getGender() + "'");
        sb.append(",");
        sb.append("'" + person.getFather() + "'");
        sb.append(",");
        sb.append("'" + person.getMother() + "'");
        sb.append(",");
        sb.append("'" + person.getSpouse() + "'");
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
     * Removes a specified person
     *
     * @param person
     */
    public void removePerson(model.Person person) {
        String sqlDel = "DELETE FROM User WHERE Username = \"" + person.getPersonID() + "\"";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sqlDel);
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Get a person by personID
     *
     * @param personID
     * @return the person object that corresponds with the personID
     */
    public model.Person getPerson(String personID) {
        String sqlGet = "SELECT * FROM User WHERE Username = \"" + personID + "\"";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            ResultSet res = stmt.executeQuery(sqlGet);
            String ID = res.getString("personID");
            String descendant = res.getString("Descendant");
            String firstName = res.getString("firstName");
            String lastName = res.getString("lastName");
            String gender = res.getString("gender");
            String father = res.getString("father");
            String mother = res.getString("mother");
            String spouse = res.getString("spouse");
            model.Person outPerson = new model.Person(ID, descendant, firstName, lastName, gender, father, mother, spouse);
            conn.close();
            return outPerson;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Clears the table by dropping it then recreating it
     */
    public void clearTable() {
        String drop = "DROP TABLE Person";
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
}

package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class PersonTest {
    model.Person test = new Person("test1", "test2", "test3", "test4", "m", "test5", "test6", "test7");

    @Test
    public void getPersonID() {
        test.getPersonID();
        assertEquals(test.getPersonID(), "test1");
    }

    @Test
    public void setPersonID() {
        test.setPersonID("nottest");
        assertEquals(test.getPersonID(), "nottest");
    }

    @Test
    public void equals() {
        model.Person test2 = new Person("test1", "test2", "test3", "test4", "m", "test5", "test6", "test7");
        assertTrue(test.equals(test2));
    }
}
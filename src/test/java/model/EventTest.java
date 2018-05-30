package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class EventTest {
    model.Event test = new Event("test1", "test2", "test3", 89.0, 68.0, "test5", "test6", "test7", 1);
    model.Event test2 = new Event("test2", "test2", "test3", 89.0, 68.0, "test5", "test6", "test7", 1);


    @Test
    public void getEventID() {
        test.getEventID();
        assertEquals(test.getEventID(), "test1");
    }

    @Test
    public void setEventID() {
        test.setEventID("nottest1");
        assertEquals(test.getEventID(), "nottest1");
    }

    @Test
    public void equals() {
        assertFalse(test.equals(test2));
    }
}
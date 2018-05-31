package service;

import org.junit.Test;

import model.Event;
import model.Person;
import request.LoadRequest;
import result.LoadResult;

import static org.junit.Assert.*;

public class LoadTest {

    Load loadtest = new Load();
    model.User sampleUser2 = new model.User("samuel", "toast", "samgmailcom", "sam", "osborne", "m", "do");
    model.User sampleUser = new model.User("sam", "toast", "samgmailcom", "sam", "osborne", "m", "do");
    model.User[] users = new model.User[]{sampleUser, sampleUser2};
    model.Person ptest = new Person("test1", "test2", "test3", "test4", "m", "test5", "test6", "test7");
    model.Person[] persons = new model.Person[]{ptest};
    model.Event test = new model.Event("test1", "test2", "test3", 89.0, 68.0, "test5", "test6", "test7", 1);
    model.Event test2 = new Event("test2", "test2", "test3", 89.0, 68.0, "test5", "test6", "test7", 1);
    model.Event[] events = new Event[]{test, test2};

    LoadRequest request = new LoadRequest(users, persons, events);

    @Test
    public void load() {
        LoadResult result = loadtest.load(request);
        assert(result.getNumEvents() == 2);
        assert(result.getNumPersons() == 1);
        assert(result.getNumEvents() == 2);
    }
}
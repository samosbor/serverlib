package service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.Event;
import dao.Person;

import static org.junit.Assert.*;

public class FillTest {
    service.Fill myFill;
    model.Person son;
    model.Person mom;
    model.Person dad;
    model.Person grandpa;
    model.Person grandma;

    @Before
    public void setUp() throws Exception {
        son = new model.Person("son", "son", "sam", "osborne", "m","dad", "mom", "kaitlyn");
        mom = new model.Person("mom", "son", "nan", "osborne", "f","duane", "juanita", "dad");
        dad = new model.Person("dad", "son", "rod", "osborne", "m","grandpa", "grandma", "mom");
        grandpa = new model.Person("grandpa", "son", "manuel", "osborne", "m","1", "2", "grandma");
        grandma = new model.Person("grandma", "son", "nancy", "osborne", "f","3", "4", "grandpa");
        myFill = new Fill();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void fill() {
        dao.Person pDao = new Person();
        dao.Event edao = new Event();
        pDao.clearTable();
        edao.clearTable();
        pDao.addPerson(son);
        pDao.addPerson(dad);
        pDao.addPerson(mom);
        pDao.addPerson(grandma);
        myFill.fill("sam", 4);
    }
}
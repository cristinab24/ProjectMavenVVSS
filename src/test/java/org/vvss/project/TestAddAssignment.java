package org.vvss.project;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.vvss.project.Domain.Teme;
import org.vvss.project.Repository.TemeRepo;
import org.vvss.project.Service.ServiceTeme;
import org.vvss.project.Validator.TemeValidator;
import org.vvss.project.Validator.ValidationException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestAddAssignment {

    ServiceTeme serviceTeme;

    @Before
    public void init() {
        TemeRepo rep = new TemeRepo(new TemeValidator(), "assignment_test.xml");
        serviceTeme = new ServiceTeme(rep);
    }

    @Test
    public void testAssignmentValid() {
        Integer id = 1;
        String description = "Do something";
        Integer deadlineWeek = 4;
        Integer deliverWeek = 3;
        Teme t = new Teme(id, description, deliverWeek, deadlineWeek);
        assertNull(serviceTeme.find(id));
        serviceTeme.add(t);
        assertNotNull(serviceTeme.find(id));
    }

    @Test(expected = ValidationException.class)
    public void testAssignmentIdNull() {
        Integer id = null;
        String description = "Do something";
        Integer deadlineWeek = 4;
        Integer deliverWeek = 3;
        Teme t = new Teme(id, description, deliverWeek, deadlineWeek);
        assertNull(serviceTeme.find(id));
        serviceTeme.add(t);
    }

    @Test(expected = ValidationException.class)
    public void testAssignmentIdSmallerThan1() {
        Integer id = -1;
        String description = "Do something";
        Integer deadlineWeek = 4;
        Integer deliverWeek = 3;
        Teme t = new Teme(id, description, deliverWeek, deadlineWeek);
        assertNull(serviceTeme.find(id));
        serviceTeme.add(t);
    }

    @Test(expected = ValidationException.class)
    public void testDeadlineWeekBiggerThan14() {
        Integer id = 2;
        String description = "Do something";
        Integer deadlineWeek = 15;
        Integer deliverWeek = 3;
        Teme t = new Teme(id, description, deliverWeek, deadlineWeek);
        assertNull(serviceTeme.find(id));
        serviceTeme.add(t);
    }

    @Test(expected = ValidationException.class)
    public void testDeadlineWeekSmallerThan1() {
        Integer id = 2;
        String description = "Do something";
        Integer deadlineWeek = -1;
        Integer deliverWeek = 3;
        Teme t = new Teme(id, description, deliverWeek, deadlineWeek);
        assertNull(serviceTeme.find(id));
        serviceTeme.add(t);
    }

    @Test(expected = ValidationException.class)
    public void testDeadlineWeekSmallerThanDeliverWeek() {
        Integer id = 2;
        String description = "Do something";
        Integer deadlineWeek = 4;
        Integer deliverWeek = 13;
        Teme t = new Teme(id, description, deliverWeek, deadlineWeek);
        assertNull(serviceTeme.find(id));
        serviceTeme.add(t);
    }

    @Test(expected = ValidationException.class)
    public void testDeliverWeekBiggerThan14() {
        Integer id = 2;
        String description = "Do something";
        Integer deadlineWeek = 4;
        Integer deliverWeek = 15;
        Teme t = new Teme(id, description, deliverWeek, deadlineWeek);
        assertNull(serviceTeme.find(id));
        serviceTeme.add(t);
    }

    @Test(expected = ValidationException.class)
    public void testDeliverWeekSmallerThan1() {
        Integer id = 1;
        String description = "Do something";
        Integer deadlineWeek = 4;
        Integer deliverWeek = -1;
        Teme t = new Teme(id, description, deliverWeek, deadlineWeek);
        assertNull(serviceTeme.find(id));
        serviceTeme.add(t);
    }

    @Test(expected = ValidationException.class)
    public void testDescriptionNull() {
        Integer id = 1;
        Integer deadlineWeek = 4;
        Integer deliverWeek = 1;
        Teme t = new Teme(id, null, deliverWeek, deadlineWeek);
        assertNull(serviceTeme.find(id));
        serviceTeme.add(t);
    }

    @Test(expected = ValidationException.class)
    public void testEmptyDescription() {
        Integer id = 1;
        String description = "";
        Integer deadlineWeek = 4;
        Integer deliverWeek = 1;
        Teme t = new Teme(id, description, deliverWeek, deadlineWeek);
        assertNull(serviceTeme.find(id));
        serviceTeme.add(t);
    }
    @After
    public void clearTests() {
        Iterator<Teme> iterator = serviceTeme.all().iterator();
        List<Teme> assignList = new ArrayList<>();
        iterator.forEachRemaining(assignList::add);
        assignList.forEach(assign -> serviceTeme.del(assign.getID()));
    }
}

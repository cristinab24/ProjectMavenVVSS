package org.vvss.project;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.vvss.project.Domain.Teme;
import org.vvss.project.Repository.TemeRepo;
import org.vvss.project.Service.ServiceTeme;
import org.vvss.project.Validator.TemeValidator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class TestAddAssignment {

    ServiceTeme serviceTeme;

    @Before
    public void init() {
        TemeRepo rep = new TemeRepo(new TemeValidator(), "assignment_test.xml");
        serviceTeme = new ServiceTeme(rep);
    }

    @Test
    public void testAssignmentIdNull(){

    }

    @Test
    public void testAssignmentIdEmpty(){

    }

    @Test
    public void testAssignmentIdSmallerThan1(){

    }

    @Test
    public void testDeadlineWeekBiggerThan14(){

    }

    @Test
    public void testDeadlineWeekSmallerThan1(){

    }

    @Test
    public void testDeadlineWeekSmallerThanDeliverWeek(){

    }

    @Test
    public void testDeliverWeekBiggerThan14(){

    }

    @Test
    public void testDeliverWeekSmallerThan1(){

    }

    @After
    public void clearTests() {
        Iterator<Teme> iterator = serviceTeme.all().iterator();
        List<Teme> assignList = new ArrayList<>();
        iterator.forEachRemaining(assignList::add);
        assignList.forEach(assign -> serviceTeme.del(assign.getID()));
    }
}

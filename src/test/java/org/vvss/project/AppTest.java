package org.vvss.project;

import org.junit.Before;
import org.junit.Test;
import org.vvss.project.Domain.Student;
import org.vvss.project.Repository.StudentRepo;
import org.vvss.project.Service.ServiceStudent;
import org.vvss.project.Validator.StudentValidator;
import org.vvss.project.Validator.ValidationException;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    ServiceStudent serviceStudent;

    @Before
    public void init(){
        StudentRepo rep = new StudentRepo(new StudentValidator(), "studenti.xml");
        serviceStudent = new ServiceStudent(rep);
    }

    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testAddStudent()
    {
        String id = "15";
        String nume = "Nume";
        int gr = 931;
        String em = "a@scs.ubbcluj.com";
        String prof = "teacher";
        Student stud = new Student(id, nume, gr, em, prof);
        assertNull(serviceStudent.find(id));
        serviceStudent.add(stud);
        assertNotNull(serviceStudent.find(id));
    }

    @Test
    public void testAddStudentInvalid()
    {
        String id = "";
        String nume = "Nume";
        int gr = 931;
        String em = "a@scs.ubbcluj.com";
        String prof = "teacher";
        Student stud = new Student(id, nume, gr, em, prof);
        try {
            serviceStudent.add(stud);
            assert (false);
        } catch (ValidationException ex){
            assertEquals("\nID invalid",ex.getMessage());
        }
        assertNull(serviceStudent.find(id));
    }
}

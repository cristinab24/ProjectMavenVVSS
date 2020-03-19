package org.vvss.project;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.vvss.project.Domain.Student;
import org.vvss.project.Repository.StudentRepo;
import org.vvss.project.Service.ServiceStudent;
import org.vvss.project.Validator.StudentValidator;
import org.vvss.project.Validator.ValidationException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestStudent {

    ServiceStudent serviceStudent;

    @Before
    public void init(){
        StudentRepo rep = new StudentRepo(new StudentValidator(), "student_test.xml");
        serviceStudent = new ServiceStudent(rep);
    }

    @Test
    public void testAddStudentValid() {
        String id = "15";
        String nume = "Nume";
        int gr = 931;
        String em = "a@scs.ubbcluj.com";
        String prof = "Teacher";
        Student stud = new Student(id, nume, gr, em, prof);
        assertNull(serviceStudent.find(id));
        serviceStudent.add(stud);
        assertNotNull(serviceStudent.find(id));
    }

    @Test(expected = ValidationException.class)
    public void testAddStudentIdNull() {
        String nume = "Nume";
        int gr = 931;
        String em = "a@scs.ubbcluj.com";
        String prof = "Teacher";
        Student stud = new Student(null, nume, gr, em, prof);
        serviceStudent.add(stud);
    }

    @Test(expected = ValidationException.class)
    public void testAddStudentIdEmpty() {
        String id = "";
        String nume = "Nume";
        int gr = 931;
        String em = "a@scs.ubbcluj.com";
        String prof = "Teacher";
        Student stud = new Student(id, nume, gr, em, prof);
        assertNull(serviceStudent.find(id));
        serviceStudent.add(stud);
    }

    @Test(expected = ValidationException.class)
    public void testAddStudentIdNegative() {
        String id = "-1";
        String nume = "Nume";
        int gr = 931;
        String em = "a@scs.ubbcluj.com";
        String prof = "Teacher";
        Student stud = new Student(id, nume, gr, em, prof);
        assertNull(serviceStudent.find(id));
        serviceStudent.add(stud);
    }

    @Test(expected = ValidationException.class)
    public void testAddStudentIdNaN() {
        String id = "not number";
        String nume = "Nume";
        int gr = 931;
        String em = "a@scs.ubbcluj.com";
        String prof = "Teacher";
        Student stud = new Student(id, nume, gr, em, prof);
        assertNull(serviceStudent.find(id));
        serviceStudent.add(stud);
    }

    @Test(expected = ValidationException.class)
    public void testAddStudentNameNotValid() {
        String id = "15";
        String nume = ":)";
        int gr = 931;
        String em = "a@scs.ubbcluj.com";
        String prof = "Teacher";
        Student stud = new Student(id, nume, gr, em, prof);
        assertNull(serviceStudent.find(id));
        serviceStudent.add(stud);
    }

    @Test(expected = NullPointerException.class)
    public void testAddStudentNameNull() {
        String id = "15";
        int gr = 931;
        String em = "a@scs.ubbcluj.com";
        String prof = "Teacher";
        Student stud = new Student(id, null, gr, em, prof);
        assertNull(serviceStudent.find(id));
        serviceStudent.add(stud);
    }

    @Test(expected = ValidationException.class)
    public void testAddStudentGroupNegativeNumber() {
        String id = "15";
        String nume = "Nume";
        int gr = -10;
        String em = "a@scs.ubbcluj.com";
        String prof = "Teacher";
        Student stud = new Student(id, nume, gr, em, prof);
        assertNull(serviceStudent.find(id));
        serviceStudent.add(stud);
    }

    @Test(expected = ValidationException.class)
    public void testAddStudentGroupInvalid() {
        String id = "15";
        String nume = "Nume";
        int gr = 100;
        String em = "a@scs.ubbcluj.com";
        String prof = "Teacher";
        Student stud = new Student(id, nume, gr, em, prof);
        assertNull(serviceStudent.find(id));
        serviceStudent.add(stud);
    }

    @Test(expected = ValidationException.class)
    public void addStudentEmailInvalid() {
        String id = "15";
        String nume = "Nume";
        int gr = 931;
        String em = "this email is not valid";
        String prof = "Teacher";
        Student stud = new Student(id, nume, gr, em, prof);
        assertNull(serviceStudent.find(id));
        serviceStudent.add(stud);
    }

    @Test(expected = NullPointerException.class)
    public void addStudentEmailNull() {
        String id = "15";
        String nume = "Nume";
        int gr = 931;
        String prof = "Teacher";
        Student stud = new Student(id, nume, gr, null, prof);
        assertNull(serviceStudent.find(id));
        serviceStudent.add(stud);
    }

    @Test(expected = ValidationException.class)
    public void testAddStudentEmailEmpty() {
        String id = "15";
        String nume = "Nume";
        int gr = 931;
        String em = "";
        String prof = "Teacher";
        Student stud = new Student(id, nume, gr, em, prof);
        assertNull(serviceStudent.find(id));
        serviceStudent.add(stud);
    }

    @Test(expected = ValidationException.class)
    public void addStudentProfessorEmpty() {
        String id = "15";
        String nume = "Nume";
        int gr = 931;
        String em = "a@scs.ubbcluj.com";
        String prof = "";
        Student stud = new Student(id, nume, gr, em, prof);
        assertNull(serviceStudent.find(id));
        serviceStudent.add(stud);
    }

    @Test(expected = NullPointerException.class)
    public void addStudentProfessorNull() {
        String id = "15";
        String nume = "Nume";
        int gr = 931;
        String em = "a@scs.ubbcluj.com";
        Student stud = new Student(id, nume, gr, em, null);
        assertNull(serviceStudent.find(id));
        serviceStudent.add(stud);
    }

    @After
    public void clearTests() {
        Iterator<Student> studentIterator = serviceStudent.all().iterator();
        List<Student> studentList = new ArrayList<>();
        studentIterator.forEachRemaining(studentList::add);
        studentList.forEach(student -> serviceStudent.del(student.getID()));
    }
}

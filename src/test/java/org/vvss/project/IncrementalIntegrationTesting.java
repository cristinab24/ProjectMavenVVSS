package org.vvss.project;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.vvss.project.Domain.Nota;
import org.vvss.project.Domain.Student;
import org.vvss.project.Domain.Teme;
import org.vvss.project.Repository.NoteRepo;
import org.vvss.project.Repository.StudentRepo;
import org.vvss.project.Repository.TemeRepo;
import org.vvss.project.Service.ServiceNote;
import org.vvss.project.Service.ServiceStudent;
import org.vvss.project.Service.ServiceTeme;
import org.vvss.project.Validator.NotaValidator;
import org.vvss.project.Validator.StudentValidator;
import org.vvss.project.Validator.TemeValidator;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class IncrementalIntegrationTesting {

    private NoteRepo noteRepo;
    private ServiceTeme serviceTeme;
    private ServiceStudent serviceStudent;
    private ServiceNote serviceNote;

    @Before
    public void setup() {
        serviceStudent = new ServiceStudent(new StudentRepo(new StudentValidator(), "student_test.xml"));
        serviceTeme = new ServiceTeme(new TemeRepo(new TemeValidator(), "assignment_test.xml"));
        noteRepo = new NoteRepo(new NotaValidator());
        serviceNote = new ServiceNote(noteRepo);
    }

    @Test
    public void addStudent() {
        String id = "15";
        String nume = "Nume";
        int gr = 931;
        String em = "a@scs.ubbcluj.com";
        String prof = "Teacher";
        Student stud = new Student(id, nume, gr, em, prof);
        serviceStudent.add(stud);
        assertNotNull(serviceStudent.find(id));
    }

    @Test
    public void addAssignment() {
        addStudent();

        Integer id = 1;
        String description = "Do something";
        Integer deadlineWeek = 4;
        Integer deliverWeek = 3;
        Teme t = new Teme(id, description, deliverWeek, deadlineWeek);
        serviceTeme.add(t);
        assertNotNull(serviceTeme.find(id));
    }

    @Test
    public void addGrade() {
        addStudent();
        addAssignment();

        String id = "15";
        String nume = "Nume";
        int gr = 931;
        String em = "a@scs.ubbcluj.com";
        String prof = "Teacher";
        Student stud = new Student(id, nume, gr, em, prof);
        Integer tid = 1;
        String description = "Do something";
        Integer deadlineWeek = 4;
        Integer deliverWeek = 3;
        Teme t = new Teme(tid, description, deliverWeek, deadlineWeek);
        Nota nota = new Nota(new AbstractMap.SimpleEntry<>("id", 10),stud, t, 10, 4);
        serviceNote.add(nota, "Catalog.xml");
        assertNotNull(noteRepo.findOne(nota.getID()));
    }

    @Test
    public void incrementalIntegration() {
        addStudent();
        addAssignment();
        addGrade();
    }

    @After
    public void clearTests() {
        Iterator<Student> studentIterator = serviceStudent.all().iterator();
        List<Student> studentList = new ArrayList<>();
        studentIterator.forEachRemaining(studentList::add);
        studentList.forEach(student -> serviceStudent.del(student.getID()));

        Iterator<Teme> iterator = serviceTeme.all().iterator();
        List<Teme> assignList = new ArrayList<>();
        iterator.forEachRemaining(assignList::add);
        assignList.forEach(assign -> serviceTeme.del(assign.getID()));
    }
}
package org.defuni;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import org.defuni.account.*;
import org.junit.jupiter.api.Test;
import org.defuni.course.ScheduledClass;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TestStudentObserve {
    @Test
    public void testAddContent() {
        // Arrange
        ScheduledClass scheduledClass = new ScheduledClass();
        Student student = new Student();
        student.addClass(scheduledClass);
        scheduledClass.setContent("Week 1: Do nothing");
        assertEquals("Week 1: Do nothing", scheduledClass.getContent());

        // Act

        scheduledClass.setContent("Week 1: Do nothing\nWeek 2: Read textbook.");
        String expectedContent = "Week 1: Do nothing\nWeek 2: Read textbook.";


        // Assert
        assertEquals(expectedContent, scheduledClass.getContent());
    }

    @Test
    public void testCreateAccount() {
        // to create staffAccount
        UserAccountFactory staffAccountFactory = new StaffAccountFactory();

        // to create studentAccount
        UserAccountFactory studentAccountFactory = new StudentAccountFactory();

        UserAccount user = staffAccountFactory.createUser(UserAccountType.LECTURER);
        assertEquals(Lecturer.class,user.getClass());

        user = studentAccountFactory.createUser(UserAccountType.STUDENT);
        assertEquals(Student.class, user.getClass());
    }

    @Test
    public void testLogin() {
        UserAccountFactory staffAccountFactory = new StaffAccountFactory();

        // to create studentAccount
        UserAccountFactory studentAccountFactory = new StudentAccountFactory();

        UserAccount user = staffAccountFactory.createUser(UserAccountType.LECTURER);
        user.setUserName("testUser");
        user.setPassword("testPassword");

        assertTrue(user.login(user.getUserName(), user.getPassword())!=null);



    }





}


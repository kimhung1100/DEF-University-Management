


package org.defuni;

import org.defuni.account.Lecturer;
import org.defuni.account.Manager;
import org.defuni.account.Student;
import org.defuni.account.UserAccount;
import org.defuni.course.ScheduledClass;
import org.junit.Test;

import org.junit.Assert;


public class updateContent {
    @Test
    public void update() {
        Lecturer lecturer = new Lecturer();
        ScheduledClass scClass = new ScheduledClass();
        Student student = new Student();
        scClass.registerObserver(lecturer);
        scClass.registerObserver(student);
        lecturer.updateContent(scClass, "new content");

    }
}

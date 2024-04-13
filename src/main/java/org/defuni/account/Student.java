package org.defuni.account;

import com.googlecode.objectify.annotation.Entity;
import org.defuni.account.UserAccount;
import org.defuni.course.ScheduledClass;
import org.defuni.course.StudentObserver;

import java.util.ArrayList;
import java.util.List;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Student extends UserAccount implements StudentObserver {
    @Id
    List<ScheduledClass> courseClass;
    List<String> notification;
    private static int ID;

    public Student() {
        courseClass = new ArrayList<ScheduledClass>();
        notification = new ArrayList<String>();

        this.setLastName("No");
        this.setFirstName("Name");
        this.setEmail("noMail@MUvodich");
        this.setAddress("Vo Gia Cu");
        this.setUserID(ID++);
    }

    public Student(String userName, String email, String password) {
        super(userName, email, password);
        courseClass = new ArrayList<ScheduledClass>();
        notification = new ArrayList<String>();
    }

    public void addClass(ScheduledClass scheduledClass) {
        courseClass.add(scheduledClass);
        scheduledClass.registerObserver(this);
    }

    public void displayGrade() {

    }

    public void update(ScheduledClass scheduledClass) {
        notification.add("Current class " + scheduledClass.getClassID() + " updated: \n" + scheduledClass.getContent());
        System.out
                .println("Student notification: Current class " + scheduledClass.getClassID() + " updated: \n" + scheduledClass.getContent());
    }

}

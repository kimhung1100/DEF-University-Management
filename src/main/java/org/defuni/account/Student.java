package org.defuni.account;

import org.defuni.account.UserAccount;
import org.defuni.course.ScheduledClass;
import org.defuni.course.StudentObserver;

import java.util.ArrayList;
import java.util.List;

public class Student extends UserAccount implements StudentObserver {
    List<ScheduledClass> courseClass;
    List<String> notification;

    public Student(){
        courseClass = new ArrayList<ScheduledClass>();
        notification = new ArrayList<String>();
    }
    public void addClass(ScheduledClass scheduledClass){
        courseClass.add(scheduledClass);
        scheduledClass.registerObserver(this);
    }
    public void displayGrade() {

    }
    public void update(ScheduledClass scheduledClass) {
        notification.add("Current class " + scheduledClass.getClassID() +" updated: \n" + scheduledClass.getContent());
        System.out.println("Current class " + scheduledClass.getClassID() +" updated: \n" + scheduledClass.getContent());
    }


}

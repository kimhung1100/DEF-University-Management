package org.defuni.account;

import com.googlecode.objectify.annotation.Entity;
import org.defuni.account.UserAccount;
import org.defuni.course.ScheduledClass;
import org.defuni.course.StudentObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Student extends UserAccount implements StudentObserver {
    @Id
    List<ScheduledClass> courseClass;
    List<String> notification;
    private static int ID;
    Map<ScheduledClass, Integer> grades;

    public Student() {
        courseClass = new ArrayList<ScheduledClass>();
        notification = new ArrayList<String>();
        grades = new HashMap<>();

    }
    public Student(String studentID){
        this.setUserName(studentID);
        grades = new HashMap<>();
    }

    public Student(String userName, String email, String password) {
        super(userName, email, password);
        courseClass = new ArrayList<ScheduledClass>();
        notification = new ArrayList<String>();
        grades = new HashMap<>();
    }



    public void addClass(ScheduledClass scheduledClass) {
        courseClass.add(scheduledClass);
        scheduledClass.registerObserver(this);
    }

    public void removeClass(ScheduledClass scheduledClass) {
        courseClass.remove(scheduledClass);
        scheduledClass.removeObserver(this);
    }

    public void displayGrade() {
        System.out.println("Grades for student " + getUserName() + ":");
        for (ScheduledClass scheduledClass : grades.keySet()) {
            int grade = grades.get(scheduledClass);
            System.out.println("Class: " + scheduledClass.getClassID() + ", Grade: " + grade);
        }
    }

    public void update(ScheduledClass scheduledClass) {
        int grade = scheduledClass.getGradeForStudent(this);
        grades.put(scheduledClass, grade);

        notification.add("Current class " + scheduledClass.getClassID() + " updated: \n" + scheduledClass.getContent());
        System.out
                .println("Student notification: Current class " + scheduledClass.getClassID() + " updated: \n" + scheduledClass.getContent());
    }

}

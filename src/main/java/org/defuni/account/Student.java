package org.defuni.account;

import com.google.cloud.firestore.Firestore;
import com.googlecode.objectify.annotation.Entity;
import org.defuni.account.UserAccount;
import org.defuni.account.UserAccountType;

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
    // private static int ID;
    // Map<ScheduledClass, Integer> grades; //Whose idea?
    List<Map<String, String>> grades; // Why not this? like "CO1001" : "10 9 9.5 8", "PH1003" "8 5 7 2"

    public List<Map<String, String>> getGrades() {
        return this.grades;
    }

    public int getID() {
        return getUserID();
    }

    public void setGrades(List<Map<String, String>> grades) {
        this.grades = grades;
    }

    public Student() {
        setAccType(UserAccountType.STUDENT);
        courseClass = new ArrayList<ScheduledClass>();
        notifications = new ArrayList<String>();
        grades = new ArrayList<>();
    }

    public Student(String studentID) {
        setAccType(UserAccountType.STUDENT);
        this.setUserName(studentID);
        grades = new ArrayList<>();
    }

    public Student(String userName, String email, String password) {
        super(userName, email, password);
        courseClass = new ArrayList<ScheduledClass>();
        notifications = new ArrayList<String>();
        // grades = new HashMap<>();

    }

    public void update(String message) {
        Manager manager = Manager.getInstance();
        Firestore db = manager.retriveDB();

        Map<String, Object> stu = Manager.findDocument(db, "students", getUserName());
        Student student = Manager.convStudent(stu);
        List<String> noti = student.getNotifications();
        noti.add(message);

        manager.updateDocument("students", getUserName(), "notifications", noti);

        this.notifications.add(message);
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
        // for (ScheduledClass scheduledClass : grades.keySet()) {
        // int grade = grades.get(scheduledClass);
        // System.out.println("Class: " + scheduledClass.getClassID() + ", Grade: " +
        // grade);
        // }
    }

    public void setNotifications(List<String> notifications) {
        this.notifications = notifications;
    }

    public List<String> getNotifications() {
        return this.notifications;
    }
}

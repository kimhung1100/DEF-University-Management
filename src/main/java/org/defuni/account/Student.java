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
import java.util.Random;

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
        // notifications = new ArrayList<String>();
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
        // notifications = new ArrayList<String>();
        // grades = new HashMap<>();

    }

    public void update(String message) {
        Manager manager = Manager.getInstance();
        Firestore db = manager.retriveDB();

        Map<String, Object> stu = Manager.findDocument(db, "students", getUserName());
        Student student = Manager.convStudent(stu);

        List<String> noti = student.getNotifications();
        List<String> sched = student.getSchedule();

        noti.add(message);
        String TKB = cookTKB(message);
        sched.add(TKB);

        manager.updateDocument("students", getUserName(), "notifications", noti);
        manager.updateDocument("students", getUserName(), "schedule", sched);

        this.notifications.add(message);
        this.setSchedule(sched);
        // this.schedule.add(courseID + " - " + courseName);
    }

    public String cookTKB(String message) {
        /// LET MASTER CHEF KENNY V COOK THIS UP!/////
        String[] parts = message.split(": ", 2);
        String somethingAhead = parts[0];
        String rest = parts[1];
        parts = rest.split(" - ", 2);
        String courseID = parts[0];
        String courseName = parts[1];

        String scheduleString = getRandomDay() + " " + courseID + " " + courseName + " " + getRandomTime();
        return scheduleString;
    }

    public String getRandomDay() {
        String[] days = { "T2", "T3", "T4", "T5", "T6", "T7" };
        Random random = new Random();
        int index = random.nextInt(days.length);
        return days[index];
    }

    public String getRandomTime() {
        Random random = new Random();
        int startHour;
        do {
            startHour = 7 + random.nextInt(8); // Random number from 7 to 14
        } while (startHour == 10 || startHour == 11); // Exclude 10 and 11

        int endHour = startHour + 2;
        return startHour + "h-" + endHour + "h";
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

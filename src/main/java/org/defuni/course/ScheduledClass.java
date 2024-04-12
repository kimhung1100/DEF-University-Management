package org.defuni.course;

import org.defuni.account.Lecturer;
import org.defuni.infrastructure.Room;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ScheduledClass implements Subject {
    private List<StudentObserver> studentObservers;

    private Lecturer lecturerIncharge;

    private String classID; // class's name: L01, L02...
    private Course course;

    private List<Session> sessions;
    private String content; // just for test, will be replace by classContent

    private List<CourseContent> classContent;
    private ScheduledClassType type;

    public ScheduledClass() {
        studentObservers = new ArrayList<StudentObserver>();
    }

    public ScheduledClass(String ID, Course course) {
        studentObservers = new ArrayList<StudentObserver>();

        this.classID = ID;
        this.course = course;
    }

    public ScheduledClass(Course course) {
        this.course = course;
        classContent = course.getCourseContent();
    }

    public void addStudent(StudentObserver o) {
        this.registerObserver(o);
    }

    public void removeStudent(StudentObserver o) {
        this.removeObserver(o);
    }

    public void notifyStudents() {
        this.notifyObserver();
    }

    public void contentChanged() {
        notifyStudents();
    }

    public void setContent(String courseContent) {
        this.content = courseContent;
        contentChanged();
    }

    public String getContent() {
        return this.content;
    }

    public String getClassID() {
        return this.classID;
    }

    public void setLectuter(Lecturer l) {
        this.lecturerIncharge = l;
    }

    public Lecturer getLecturer() {
        return this.lecturerIncharge;
    }

    public void registerObserver(StudentObserver student) {
        studentObservers.add(student);
    }

    public void removeObserver(StudentObserver student) {
        studentObservers.remove(student);
    }

    public void notifyObserver() {
        for (StudentObserver student : studentObservers) {
            student.update(this);
        }
    }
}

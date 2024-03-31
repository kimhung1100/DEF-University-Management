package org.defuni.course;

import org.defuni.infrastructure.Room;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ScheduledClass implements Subject {
    private List<StudentObserver> studentObservers;

    private List<LecturerObserver> lectureObservers;

    private String classID;
    private Course course;

    private List<Session> sessions;
    private String content; //just for test, will be replace by classContent

    private List<CourseContent> classContent;
    private ScheduledClassType type;
    public ScheduledClass() {
        studentObservers = new ArrayList<StudentObserver>();
        lectureObservers = new ArrayList<LecturerObserver>();
        sessions = new ArrayList<Session>();
    }

    public ScheduledClass(Course course){
        this.course = course;
        classContent = course.getCourseContent();
    }

    public void registerObserver(StudentObserver student){
        studentObservers.add(student);
    }
    public void removeObserver(StudentObserver student){
        studentObservers.remove(student);
    }

    public void notifyObserver() {
        for (StudentObserver student : studentObservers){
            student.update(this);
        }
    }

    public void contentChanged() {
        notifyObserver();
    }
    public void setContent(String courseContent){
        this.content = courseContent;
        contentChanged();
    }
    public void createSessionEachWeek(LocalTime time, LocalDate beginDate, Room room) {
        if (classContent == null) {
            return;
        }

        for (CourseContent courseContent : classContent) {
            int[] weeks = courseContent.getWeeks();
            String[] contents = courseContent.getContent();
            String[] lecturings = courseContent.getLecturing();

            for (int week : weeks) {
                // Calculate the date for the current week
                LocalDate sessionDate = beginDate.plusWeeks(week - 1); // Subtract 1 because weeks are 1-based

                // Create a new session for each week
                LocalDateTime sessionDateTime = sessionDate.atTime(time);

                // Create a new session for each week
                Session session = new Session(courseContent, sessionDateTime, room);
                sessions.add(session);
            }
        }
    }
    public String getContent(){
        return this.content;
    }
    public String getClassID() {return this.classID;}
}

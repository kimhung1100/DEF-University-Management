package org.defuni.course;

import org.defuni.account.Manager;
import org.defuni.infrastructure.Room;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import org.defuni.account.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScheduledClass implements Subject {
    private List<StudentObserver> studentObservers;

    private List<LecturerObserver> lectureObservers;

    private String classID; // class's name: L01, L02...
    private Course course;

    private String content; // just for test, will be replace by classContent

    // private List<CourseContent> classContent;
    private ScheduledClassType type;

 

    public ScheduledClass(String classID) {
        this.classID = classID;
        studentObservers = new ArrayList<StudentObserver>();
        lectureObservers = new ArrayList<LecturerObserver>();
        Manager manager = Manager.getInstance();
        Firestore firestore = manager.connect();
        CollectionReference scheduledClassCollection = firestore.collection("scheduledClass");
        DocumentReference docRef = scheduledClassCollection.document(this.getClassID());
        Map<String, Object> data = createExpectedDataMap();

        ApiFuture<WriteResult> writeResult = docRef.set(data);



    }

    public ScheduledClass(String ID) {
        studentObservers = new ArrayList<StudentObserver>();
        lectureObservers = new ArrayList<LecturerObserver>();

        this.classID = ID;
    }
    
    private Map<String, Object> createExpectedDataMap() {
        Map<String, Object> data = new HashMap<>();
        data.put("classId", this.getClassID());
        // data.put("email", this.getCourseContent());
        // data.put("password", this.getPassword());
        // ... other fields based on your Student class
        return data;
    }

   

    public ScheduledClass(Course course) {
        this.course = course;
        classContent = course.getCourseContent();
    }
    public void registerObserver(LecturerObserver lecture) {
        
        lectureObservers.add(lecture);
    }
     

    public void registerObserver(StudentObserver student) {

        studentObservers.add(student);
    }

    public void removeObserver(LecturerObserver lecture) {
        LecturerObserver.remove(lecture);
    }


    public void removeObserver(StudentObserver student) {
        studentObservers.remove(student);
    }

    public void notifyObserver() {
        for (StudentObserver student : studentObservers) {
            student.update(this);
        }
    }

    public void contentChanged() {
        notifyObserver();
    }

    public void setContent(String courseContent) {
        this.content = courseContent;
        contentChanged();
    }

    public void createSessionEachWeek(LocalTime time, LocalDate beginDate, Room room) {
        if (classContent == null) {
            return;
        }

        // for (CourseContent courseContent : classContent) {
        // int[] weeks = courseContent.getWeeks();
        // String[] contents = courseContent.getContent();
        // String[] lecturings = courseContent.getLecturing();
        //
        // for (int week : weeks) {
        // // Calculate the date for the current week
        // LocalDate sessionDate = beginDate.plusWeeks(week - 1); // Subtract 1 because
        // weeks are 1-based
        //
        // // Create a new session for each week
        // LocalDateTime sessionDateTime = sessionDate.atTime(time);
        //
        // // Create a new session for each week
        // Session session = new Session(courseContent, sessionDateTime, room);
        // sessions.add(session);
        // }
        // }
    }

    public String getContent() {
        return this.content;
    }

    public String getClassID() {
        return this.classID;
    }

    public String getCourse() {
        return this.course;
    }
}

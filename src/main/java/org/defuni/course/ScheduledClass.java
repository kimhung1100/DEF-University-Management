package org.defuni.course;

import org.defuni.account.*;
import org.defuni.infrastructure.Room;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScheduledClass extends Course {
    private List<StudentObserver> studentObservers;

    private List<LecturerObserver> lecturerObservers;

    private List<List<Double>> componentGradesList;
    private String classID; // class's name: L01, L02...
    private Course course;

    private String content; // just for test, will be replace by classContent

    // private List<CourseContent> classContent;
    private ScheduledClassType type;
    private String semester;
    private String schoolYear;
    private LocalTime time;
    private Room room;

    public void setTime(LocalTime time){
        this.time = time;
    }
    public LocalTime getTime(){
        return this.time;
    }
    public void setRoom(Room room){
        this.room = room;
    }
    public Room getRoom(){
        return this.room;
    }
    public ScheduledClass(){
        List<List<Double>> componentGradesList = new ArrayList<>();
        for(int i=0; i < studentObservers.size(); i++){
            for(int j=0; j < componentGrades.size(); j++) {
                componentGradesList.add(getComponentGrades());
            }
        }
    }
    public void GradeChanged(StudentObserver o, int index, Double grade){
        int position = studentObservers.indexOf(o);
        for(int i=0; i < studentObservers.size(); i++){
            if(studentObservers.get(i) == o){
                position = i;
                break;
            }
        }
        componentGradesList.get(position).set(index,grade);
    }

    public void setSemester(String semester){
        this.semester = semester;
    }
    public String getSemester() {
        return semester;
    }
    public void setSchoolYear(String year){
        this.schoolYear = year;
    }
    public String getSchoolYear(){
        return schoolYear;
    }


    public ScheduledClass(String classID) {
        this.classID = classID;
        studentObservers = new ArrayList<StudentObserver>();
        lecturerObservers = new ArrayList<LecturerObserver>();
//        save();
    }
    private void save(){
        Manager manager = Manager.getInstance();

        Firestore firestore = manager.connect();
        CollectionReference scheduledClassCollection = firestore.collection("scheduledClass");
        DocumentReference docRef = scheduledClassCollection.document(this.getClassID());
        Map<String, Object> data = createExpectedDataMap();

        ApiFuture<WriteResult> writeResult = docRef.set(data);
    }

    public ScheduledClass(String classID, Course course){
        this.setClassID(classID);
        this.setCourse(course);
        save();
    }

    private void setCourse(Course course) {
        this.course = course;
    }

    private void setClassID(String classID) {
        this.classID = classID;
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
        this.content = course.getCourseContent();
    }
    public void registerObserver(LecturerObserver lecture) {

        lecturerObservers.add(lecture);
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



    public String getContent() {
        return this.content;
    }

    public String getClassID() {
        return this.classID;
    }

    

//    public Lecturer getLecturer() {
//        return this.lecturerIncharge;
//    }

    public void registerObserver(StudentObserver student) {

        studentObservers.add(student);
    }

    public void removeObserver(LecturerObserver lecture) {
        lecturerObservers.remove(lecture);
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

//    public void createSessionEachWeek(LocalTime time, LocalDate beginDate, Room room) {
//        if (classContent == null) {
//            return;
//        }
//
//        // for (CourseContent courseContent : classContent) {
//        // int[] weeks = courseContent.getWeeks();
//        // String[] contents = courseContent.getContent();
//        // String[] lecturings = courseContent.getLecturing();
//        //
//        // for (int week : weeks) {
//        // // Calculate the date for the current week
//        // LocalDate sessionDate = beginDate.plusWeeks(week - 1); // Subtract 1 because
//        // weeks are 1-based
//        //
//        // // Create a new session for each week
//        // LocalDateTime sessionDateTime = sessionDate.atTime(time);
//        //
//        // // Create a new session for each week
//        // Session session = new Session(courseContent, sessionDateTime, room);
//        // sessions.add(session);
//        // }
//        // }
//    }


    public Course getCourse() {
        return this.course;
    }
}

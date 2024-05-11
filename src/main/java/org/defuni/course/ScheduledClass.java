package org.defuni.course;

import org.defuni.account.*;
import org.defuni.infrastructure.Room;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;

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

    //Database không thể nhận  Map<List<String>, List<Double>> không thể lập bảng điểm
    private Map<String, List<Double>> listScore = new HashMap<>();

    public List<String> studentList = new ArrayList<String>();

    // score list
    

    

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalTime getTime() {
        return this.time;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return this.room;
    }

    public void setClassID(String ID) {
        this.classID = ID;
    }


    public void GradeChanged(StudentObserver o, int index, Double grade) {
        int position = studentObservers.indexOf(o);
        for (int i = 0; i < studentObservers.size(); i++) {
            if (studentObservers.get(i) == o) {
                position = i;
                break;
            }
        }
        componentGradesList.get(position).set(index, grade);
    }
    public ScheduledClass() {

    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getSemester() {
        return semester;
    }

    public void setSchoolYear(String year) {
        this.schoolYear = year;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public ScheduledClass(String classID) {
        this.classID = classID;
        studentObservers = new ArrayList<StudentObserver>();
        lecturerObservers = new ArrayList<LecturerObserver>();
        // save();
    }

    private void save() {
        Manager manager = Manager.getInstance();

        Firestore firestore = manager.connect();
        CollectionReference scheduledClassCollection = firestore.collection("scheduledClass");
        DocumentReference docRef = scheduledClassCollection.document(this.getClassID());
        Map<String, Object> data = createExpectedDataMap();

        ApiFuture<WriteResult> writeResult = docRef.set(data);
    }

    public ScheduledClass(String classID, Course course) {
        this.setClassID(classID);
        this.setCourse(course);
        save();
    }

    public void setCourse(Course course) {
        this.course = course;
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
//        int n = course.getComponentGrades().size();
//
//        List<List<Double>> componentGradesList = new ArrayList<>();
//        for (int i = 0; i < studentObservers.size(); i++) {
//            for (int j = 0; j < n; j++) {
//                componentGradesList.add(getComponentGrades());
//            }
//        }
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

    public void setContent(String courseContent) {
        this.content = courseContent;
        contentChanged();
    }

    // public Lecturer getLecturer() {
    // return this.lecturerIncharge;
    // }

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
        if (studentObservers == null || studentObservers.isEmpty())
        {
            return;}
        for (StudentObserver student : studentObservers) {
            student.update(this);
        }
    }

    public void contentChanged() {
        notifyStudents();
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

    public Course getCourse() {
        return this.course;
    }

   /*   ~~~~~~~~~~~~~~~~~~~~~~~~
    public void setScore(int MSSV, double[] data) {
        listScore.add(MSSV, data);
    }

    public void sortListScore() {
        listScore.sortListByMSSV();
    }

    public SinglyLinkedList getListScore() {
        return listScore;
    }

    public double[] getScoreStudent(int MSSV) {
        return listScore.Score(MSSV);
    }

    public void printListScore() {
        listScore.printList();
    }

    public void printScoreStudent(int MSSV) {
        listScore.printStudent(MSSV);
    }

    public boolean findStudent(int MSSV) {
        return listScore.findMSSV(MSSV);
    }

    public void updateScore(int MSSV, double[] data) {
        listScore.updateScore(MSSV, data);
    }

   

    // ~~~~~~~~~~~~~~~~~~~~~~~~*/

    public void registerStudent(String studentID) {
        studentList.add(studentID);
    }
    public List<String> getStudentList() {
        return studentList;
    }
    // public void createSessionEachWeek(LocalTime time, LocalDate beginDate, Room
    // room) {
    // if (classContent == null) {
    // return;
    // }
    //
    // // for (CourseContent courseContent : classContent) {
    // // int[] weeks = courseContent.getWeeks();
    // // String[] contents = courseContent.getContent();
    // // String[] lecturings = courseContent.getLecturing();
    // //
    // // for (int week : weeks) {
    // // // Calculate the date for the current week
    // // LocalDate sessionDate = beginDate.plusWeeks(week - 1); // Subtract 1
    // because
    // // weeks are 1-based
    // //
    // // // Create a new session for each week
    // // LocalDateTime sessionDateTime = sessionDate.atTime(time);
    // //
    // // // Create a new session for each week
    // // Session session = new Session(courseContent, sessionDateTime, room);
    // // sessions.add(session);
    // // }
    // // }
    // }




    //scorelist//
    public  Map<String, List<Double>> getListScore() {
        return this.listScore;
    }

    public void addScore(String MSSV, double[] data) {

        if(this.findMSSV(MSSV)){
            //System.out.print("Use updateScore(MSSV)\n");
            return;
        } 


        List<Double> newScore = new ArrayList<>();
        for (double value : data) {
            newScore.add( Math.round(value * 100.0) / 100.0);
        }
        
        this.listScore.put(MSSV, newScore);
    }

    // tìm
    public boolean findMSSV(String MSSV) {
        for (Map.Entry<String, List<Double>> entry : listScore.entrySet()) {
            if(entry.getKey() == MSSV) return true;
        }
        return false; // Không tìm thấy MSSV
    }

    //in MSSV + điểm
    public void printList(){
        for (Map.Entry<String, List<Double>> entry : listScore.entrySet()) {

            String[] formattedFractions = entry.getValue().stream()
            .map(f -> String.format("%.2f", f))
            .toArray(String[]::new);

            System.out.println("StudentID: " + entry.getKey() + "    Score: " + String.join(", ", formattedFractions));
        }
    }


    // cập nhật MSSV có sẵn, thất bại, trả false
    public boolean updateScore(String MSSV, double[] newData){

        if( !this.listScore.containsKey(MSSV)) return false;
        

        // Xóa tất cả giá trị cũ trong List
        this.listScore.get(MSSV).clear();

        // Thêm giá trị mới vào List
        for (double value : newData) {
            this.listScore.get(MSSV).add(value);
        }
            return true;
    }
    //scorelist//
}

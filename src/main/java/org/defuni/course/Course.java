package org.defuni.course;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import org.defuni.account.Lecturer;
import org.defuni.account.Manager;
import org.defuni.account.Student;
import org.defuni.account.UserAccount;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Course {
    private String courseID;
    private String courseTitle;
    private Lecturer lecturerCreated;
    private int credits;
    private List<Double> componentGrades;
//    private List<CourseContent> courseContent;
    private String courseContent;
    private List<String> courseMaterials;

    private CourseState courseState;

    public String description;
    public Department department;

    private List<Student> studentRegisters;
    private void save(){
        Manager manager = Manager.getInstance();

        Firestore firestore = manager.connect();
        CollectionReference scheduledClassCollection = firestore.collection("course");
        DocumentReference docRef = scheduledClassCollection.document(this.getCourseID());
        Map<String, Object> data = createExpectedDataMap();

        ApiFuture<WriteResult> writeResult = docRef.set(data);
    }
    public static Course fromMap(Map<String, Object> data){
        Course course = new Course();
        course.setCourseID((String) data.get("courseID"));
        course.setCourseTitle((String) data.get("courseTitle"));
//        course.setLecturerInCharge((Lecturer) data.get("lecturerInCharge"));
        Object creditsObj = data.get("credits");
        // Check if the creditsObj is not null and is an instance of Integer or Long
        if (creditsObj != null && (creditsObj instanceof Integer || creditsObj instanceof Long)) {
            // Convert the creditsObj to Integer
            int credits = ((Number) creditsObj).intValue();
            // Set the credits to the course object
            course.setCredits(credits);
        } else {
            // Handle the case where credits is not an Integer or Long
            // You can log an error, provide a default value, or take other appropriate actions
            course.setCredits(0);
        }
        course.setComponentGrades((List<Double>) data.get("componentGrades"));
        course.setCourseMaterials((List<String>) data.get("courseMaterials"));
//        CourseState courseState = (CourseState) data.get("courseState");
//        course.setState((String) data.get("state"));
        course.setDescription((String) data.get("description"));
        List<String> studentIds = (List<String>) data.get("studentRegisters");
        ArrayList<Student> students = new ArrayList<>();
        for (String studentId : studentIds){
            students.add(new Student(studentId));
        }
        course.setStudentRegisters(students);

        return course;

    }

    private void setDescription(String description) {
        this.description = description;
    }

    private void setState(CourseState state) {
        this.courseState = state;
    }

    private void setCourseMaterials(List<String> courseMaterials) {
        this.courseMaterials = courseMaterials;
    }

    private void setStudentRegisters(List<Student> studentRegisters) {
        this.studentRegisters = studentRegisters;
    }

    private Map<String, Object> createExpectedDataMap() {
        Map<String, Object> data = new HashMap<>();
        data.put("courseID", this.getCourseID());
        data.put("courseTitle", this.getCourseTitle());
        data.put("lecturerInCharge", this.getLecturer());
        data.put("credits", this.getCredits());
        data.put("componentGrades", this.getComponentGrades());
        data.put("courseContent", this.getCourseContent());
        data.put("courseMaterials", this.getCourseMaterials());
        data.put("state", this.getState());
        data.put("description", this.getDescription());
        data.put("department", this.getDepartment());
        data.put("studentRegisters", this.getStudentRegisters());





        // data.put("password", this.getPassword());
        // ... other fields based on your Student class
        return data;
    }

    public Department getDepartment() {
        return this.department;
    }

    public String getDescription() {
        return this.description;
    }

    public CourseState getState() {
        return this.courseState;
    }

    public List<String> getCourseMaterials() {
        return this.courseMaterials;
    }

    public List<Double> getComponentGrades(){
        return this.componentGrades;
    }
    private int getCredits() {
        return this.credits;
    }

    private Lecturer getLecturer() {
        return this.lecturerInCharge;
    }

    public Course(String courseID, String courseName, int credit, String courseContent, Lecturer lecturer) {
        this.courseID = courseID;
        this.courseTitle = courseName;
        this.credits = credit;
        this.courseContent = courseContent;
        this.componentGrades = new ArrayList<>();
        this.studentRegisters = new ArrayList<>();
        this.courseState = CourseState.EDITING;
        this.lecturerInCharge = lecturer;

        save();

    }

    public void registerCourse(Student student){
        this.studentRegisters.add(student);
        save();
    }
    public void removeRegister(Student student){
        this.studentRegisters.remove(student);
    }
    public List<Student> getStudentRegisters() {
        return this.studentRegisters;
    }
    public void deleteRegisters(){
        this.studentRegisters = new ArrayList<>();
    }


    public Course() {
        courseState = CourseState.EDITING;

        courseMaterials = new ArrayList<>();
        studentRegisters = new ArrayList<>();

    }

    public double getComponentGradeAt(int index) {
        if (index < 0 || index >= componentGrades.size()) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        return componentGrades.get(index);
    }

    public int getNumberOfComponentGrades() {
        return componentGrades.size();
    }

//    public List<CourseContent> getCourseContent() {
//        return this.courseContent;
//    }
    public String getCourseContent() {
        return this.courseContent;
    }


    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseID() {
        return this.courseID;
    }
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseTitle() {
        return this.courseTitle;
    }

    public void setLecturerInCharge(Lecturer lecturer) {
        this.lecturerCreated = lecturer;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setComponentGrades(List<Double> componentGrades) {
        this.componentGrades = componentGrades;
    }
}

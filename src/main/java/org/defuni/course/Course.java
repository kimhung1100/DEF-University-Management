package org.defuni.course;

import org.defuni.account.Lecturer;

import java.util.List;
import java.util.ArrayList;

public class Course {
    private String courseID;
    private String courseTitle;
    private Lecturer lecturerCreated;
    private int credits;
    private List<Double> componentGrades;
    private List<CourseContent> courseContent;
    private List<String> courseMaterials;
    private CourseState courseState;
    private List<ScheduledClass> classes;

    public String description;
    public Department department;

    public Course() {
        courseState = CourseState.EDITING;
        classes = new ArrayList<ScheduledClass>();

        classes.add(new ScheduledClass("L01", this));
        classes.add(new ScheduledClass("L02", this));
        classes.add(new ScheduledClass("L03", this));

        classes.get(0).addLecturer(lecturerCreated); // L01 sẽ do giáo viên tạo course dạy
        lecturerCreated.addClass(classes.get(0));

        courseMaterials = new ArrayList<>();

    }

    public double getComponentGrade(int index) {
        if (index < 0 || index >= componentGrades.size()) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        return componentGrades.get(index);
    }

    public int getNumberOfComponentGrades() {
        return componentGrades.size();
    }

    public List<CourseContent> getCourseContent() {
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

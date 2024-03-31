package org.defuni.course;

import org.defuni.account.Lecturer;

import java.util.List;

public class Course {
    private String courseID;
    private String courseTitle;
    private Lecturer lecturerInCharge;
    private int credits;
    private List<Double> componentGrades;
    private List<CourseContent> courseContent;
    private List<String> courseMaterials;
    private CourseState courseState;

    public Course(){
        courseState = CourseState.EDITING;
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

    public String description;
    public Department department;

    public List<CourseContent> getCourseContent() {return this.courseContent;}


    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public void setLecturerInCharge(Lecturer lecturer) {
        this.lecturerInCharge = lecturer;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setComponentGrades(List<Double> componentGrades) {
        this.componentGrades = componentGrades;
    }
}

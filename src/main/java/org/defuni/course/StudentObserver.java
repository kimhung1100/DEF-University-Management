package org.defuni.course;
public class Student implements StudentObserver {
    private String studentID;
    private String studentName;
    private double grade;

    public Student(String studentID, String studentName) {
        this.studentID = studentID;
        this.studentName = studentName;
    }
    public void displayGrade() {
        System.out.println("Student ID: " + studentID + ", Name: " + studentName + ", Grade: " + grade);
    }
     public void update(ScheduledClass scheduledClass) {
        System.out.println("Student ID: " + studentID + ", Name: " + studentName + ", Class Updated: " + scheduledClass.getClassID());
    }
    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}

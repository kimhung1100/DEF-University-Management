package org.defuni.course;

public class Student implements StudentObserver {
    private String studentID;
    private String name;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
    }

    public void displayGrade() {
        System.out.println("Grades for Student " + name + ": ");s
        double[] grades = scheduledClass.getScoreStudent(studentID);
        for (int i = 0; i < grades.length; i++) {
            System.out.println("Component " + (i+1) + ": " + grades[i]);
        }
    }
    public void update(ScheduledClass scheduledClass) {
        displayGrade(); 
    }
}

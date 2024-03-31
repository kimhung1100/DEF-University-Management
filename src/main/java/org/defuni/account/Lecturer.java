package org.defuni.account;

import org.defuni.course.Course;
import org.defuni.course.LecturerObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lecturer extends UserAccount implements LecturerObserver {
    private String department;
    private List<String> certificates;

    public Lecturer(){
        this.department = "default";
        this.certificates = new ArrayList<String>();

    }
    public void updateClass(){

    }

    public void updateGrade(){

    }

    public boolean courseCreate() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter course ID:");
        String courseID = scanner.nextLine();

        System.out.println("Enter course title:");
        String courseTitle = scanner.nextLine();

        // Assuming you have appropriate constructors for Department and CourseContent classes
        Course course = new Course();
        course.setCourseID(courseID);
        course.setCourseTitle(courseTitle);
        course.setLecturerInCharge(this);


        // Add the created course to the list of courses
        Manager.addCourse(course);

        System.out.println("Course created successfully!");
        return true;
    }
}

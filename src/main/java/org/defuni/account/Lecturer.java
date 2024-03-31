package org.defuni.account;

import org.defuni.account.UserAccount;
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

    public void courseCreate() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter course ID:");
        String courseID = scanner.nextLine();

        System.out.println("Enter course title:");
        String courseTitle = scanner.nextLine();


        System.out.println("Enter number of credits:");
        int credits = scanner.nextInt();

        System.out.println("Enter number of component grades:");
        int numOfGrades = scanner.nextInt();
        List<Double> componentGrades = new ArrayList<>();
        System.out.println("Enter component grades:");
        for (int i = 0; i < numOfGrades; i++) {
            componentGrades.add(scanner.nextDouble());
        }

        // Assuming you have appropriate constructors for Department and CourseContent classes
        Course course = new Course();
        course.setCourseID(courseID);
        course.setCourseTitle(courseTitle);
        course.setLecturerInCharge(this);
        course.setCredits(credits);
        course.setComponentGrades(componentGrades);

        // Add the created course to the list of courses
        courses.add(course);

        System.out.println("Course created successfully!");
    }
}

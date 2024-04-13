package org.defuni.account;

import org.defuni.course.Course;
import org.defuni.course.LecturerObserver;
import org.defuni.course.ScheduledClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lecturer extends UserAccount implements LecturerObserver {
    private String department;
    private List<String> certificates;

    public static int ID = 1;

    public Lecturer() {
        this.department = "default";
        this.certificates = new ArrayList<String>();

        this.setLastName("No");
        this.setFirstName("Name");
        this.setEmail("noMail@MUvodich");
        this.setAddress("Vo Gia Cu");
        this.setUserID(ID++); // Lecturer's ID k lien quan den Student's ID, mac du chung userID ben super

    }

    public void updateClass() { // Need target

    }

    public void updateGrade() { // Need target, update class, not student

    }


    public void updateContent(ScheduledClass scheduledClass, String Content) { // Need target, update class, not student
        scheduledClass.setContent(Content);
    }
    public boolean inspectStudentScore() { // Need target
        return true;
    }

    public boolean courseCreate() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter course ID:");
        String courseID = scanner.nextLine();

        System.out.println("Enter course title:");
        String courseTitle = scanner.nextLine();

        // Assuming you have appropriate constructors for Department and CourseContent
        // classes
        Course course = new Course();
        course.setCourseID(courseID);
        course.setCourseTitle(courseTitle);
        course.setLecturerInCharge(this);

        // Add the created course to the list of courses
        Manager.addCourse(course);

        System.out.println(String.format("Course %s created successfully!", course.getCourseTitle()));

        scanner.nextLine();
        clearScreen();

        // scanner.close(); // Dont close the scanner!
        return true;
    }
}

package org.defuni.cli;

import org.defuni.account.Lecturer;
import org.defuni.course.Course;
import org.defuni.course.CourseState;
import org.defuni.course.Department;
import org.defuni.course.ScheduledClass;

import java.util.List;
import java.util.Scanner;

import static org.defuni.Main.clearScreen;

public class CourseCreatePage {
    public CourseCreatePage(Lecturer lecturer){
        clearScreen();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Course Creating:");

        System.out.println("Enter Course ID:");
        String courseID = scanner.nextLine();

        System.out.println("Enter Course Title:");
        String courseName = scanner.nextLine();


        System.out.println("Enter course content:");
        String courseContent = scanner.nextLine();

        System.out.println("Enter credits:");
        int credit = scanner.nextInt();

        Course course = new Course(courseID, courseName, credit, courseContent, lecturer);

        System.out.println("Course Create Successful!");


    }
}
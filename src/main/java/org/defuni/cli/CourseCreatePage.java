package org.defuni.cli;

import org.checkerframework.checker.units.qual.degrees;
import org.defuni.account.Lecturer;
import org.defuni.course.Course;
import org.defuni.course.ScheduledClass;
import org.defuni.repository.CourseRepositoryFirebase;

import java.util.List;
import java.util.Scanner;

import static org.defuni.Main.clearScreen;

public class CourseCreatePage {
    public CourseCreatePage(Lecturer lecturer) {
        clearScreen();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Course Creating:");

        System.out.println("Enter Course ID:");
        String courseID = scanner.nextLine();

        System.out.println("Enter Course Title:");
        String courseName = scanner.nextLine();

        System.out.println("Enter course description:");
        String courseDes = scanner.nextLine();

        System.out.println("Enter course initial material:");
        String courseMat = scanner.nextLine();

        System.out.println("Enter course content:");
        String courseContent = scanner.nextLine();

        System.out.println("Enter credits:");
        int credit = scanner.nextInt();

        Course course = new Course(courseID, courseName, credit, courseContent, lecturer, courseDes, courseMat);
        CourseRepositoryFirebase courseRepository = new CourseRepositoryFirebase();

        courseRepository.saveCourse(course);

        System.out.println("Course Create Successful!");

    }
}

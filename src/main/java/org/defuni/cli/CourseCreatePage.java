package org.defuni.cli;

import org.checkerframework.checker.units.qual.degrees;
import org.defuni.account.EducationManager;
import org.defuni.account.Lecturer;
import org.defuni.account.Manager;
import org.defuni.course.Course;
import org.defuni.course.ScheduledClass;
import org.defuni.repository.CourseRepositoryFirebase;
import org.defuni.repository.Firebase;

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
        while (courseID.trim().isEmpty() || Firebase.hasName(Manager.getDB(), "course", courseID)) {
            if (courseID.trim().isEmpty()) {
                System.out.println("Course ID cannot be empty. Please enter again:");
            } else if (Firebase.hasName(Manager.getDB(), "course", courseID)) {
                System.out.println("Course ID already exists. Please enter a unique ID:");
            }
            courseID = scanner.nextLine();
        }

        System.out.println("Enter Course Title:");
        String courseName = scanner.nextLine();
        while (courseName.trim().isEmpty()) {
            System.out.println("Course Title cannot be empty. Please enter again:");
            courseName = scanner.nextLine();
        }

        System.out.println("Enter course description:");
        String courseDes = scanner.nextLine();
        while (courseDes.trim().isEmpty()) {
            System.out.println("Course Description cannot be empty. Please enter again:");
            courseDes = scanner.nextLine();
        }

        System.out.println("Enter course initial material:");
        String courseMat = scanner.nextLine();
        while (courseMat.trim().isEmpty()) {
            System.out.println("Course Initial Material cannot be empty. Please enter again:");
            courseMat = scanner.nextLine();
        }

        System.out.println("Enter course content:");
        String courseContent = scanner.nextLine();
        while (courseContent.trim().isEmpty()) {
            System.out.println("Course Content cannot be empty. Please enter again:");
            courseContent = scanner.nextLine();
        }

        int credits = 0;
        while (true) {
            System.out.println("Enter credits:");
            try {
                credits = Integer.parseInt(scanner.nextLine());
                if (credits <= 0) {
                    System.out.println("Credits must be a positive number. Please enter again:");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number:");
            }
        }

        Course course = new Course(courseID, courseName, credits, courseContent, lecturer, courseDes, courseMat);
        CourseRepositoryFirebase courseRepository = new CourseRepositoryFirebase();

        courseRepository.saveCourse(course);

        System.out.println("Course Create Successful!");

    }

    public CourseCreatePage(EducationManager manager) {
        clearScreen();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Course Creating:");

        System.out.println("Enter Course ID:");
        String courseID = scanner.nextLine();

        // Boolean isValid = Firebase.hasName(Manager.getDB(), "course", courseID);
        Boolean hasExisted = Manager.findDocument("course", courseID) == null ? false : true;

        while (courseID.trim().isEmpty() || hasExisted) {
            if (courseID.trim().isEmpty()) {
                System.out.println("Course ID cannot be empty. Please enter again:");
            } else if (hasExisted) {
                System.out.println("Course ID already exists. Please enter a unique ID:");
            }
            courseID = scanner.nextLine();
        }

        System.out.println("Enter Course Title:");
        String courseName = scanner.nextLine();
        while (courseName.trim().isEmpty()) {
            System.out.println("Course Title cannot be empty. Please enter again:");
            courseName = scanner.nextLine();
        }

        System.out.println("Enter course description:");
        String courseDes = scanner.nextLine();
        while (courseDes.trim().isEmpty()) {
            System.out.println("Course Description cannot be empty. Please enter again:");
            courseDes = scanner.nextLine();
        }

        System.out.println("Enter course initial material:");
        String courseMat = scanner.nextLine();
        while (courseMat.trim().isEmpty()) {
            System.out.println("Course Initial Material cannot be empty. Please enter again:");
            courseMat = scanner.nextLine();
        }

        System.out.println("Enter course content:");
        String courseContent = scanner.nextLine();
        while (courseContent.trim().isEmpty()) {
            System.out.println("Course Content cannot be empty. Please enter again:");
            courseContent = scanner.nextLine();
        }

        int credits = 0;
        while (true) {
            System.out.println("Enter credits:");
            try {
                credits = Integer.parseInt(scanner.nextLine());
                if (credits <= 0) {
                    System.out.println("Credits must be a positive number. Please enter again:");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number:");
            }
        }

        Course course = new Course(courseID, courseName, credits, courseContent, courseDes, courseMat);
        CourseRepositoryFirebase courseRepository = new CourseRepositoryFirebase();

        courseRepository.saveCourse(course);

        System.out.println("Course Create Successful!");

    }
}

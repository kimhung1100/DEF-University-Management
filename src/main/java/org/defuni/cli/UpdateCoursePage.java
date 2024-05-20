package org.defuni.cli;

import org.defuni.account.Lecturer;
import org.defuni.account.Manager;
import org.defuni.course.Course;
import org.defuni.repository.CourseRepositoryFirebase;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.defuni.Main.clearScreen;
import static org.defuni.Main.sleep;

public class UpdateCoursePage {
    public UpdateCoursePage(Lecturer lecturer) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            clearScreen();

            System.out.println("Course Updating:");
            Manager.displayDocs("course");
            System.out.println("Enter Course ID:");
            String courseID = scanner.nextLine();

            if (courseID.isEmpty()) {
                System.out.println("Invalid Course ID");
                sleep(1000);
                continue;
            }

            CourseRepositoryFirebase courseRepository = new CourseRepositoryFirebase();
            Course course = courseRepository.findCourseById(courseID);
            System.out.println(course.toString());
            System.out.println("\n______________________________________________________\n");
            System.out.println("Update choice:");
            System.out.println("1. Update course ID");
            System.out.println("2. Update course title");
            System.out.println("3. Update course credit");
            System.out.println("4. Update course content");
            System.out.println("5. Update course materials");
            System.out.println("6. Exit: ");
            System.out.println("\n______________________________________________________\n");
            System.out.println("Enter choice:");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                System.out.println("Enter new Course ID:");
                String newCourseID = scanner.nextLine();
                course.setCourseID(newCourseID);
            } else if (choice.equals("2")) {
                System.out.println("Enter new Course Title:");
                String newTitle = scanner.nextLine();
                course.setCourseTitle(newTitle);
            } else if (choice.equals("3")) {
                System.out.println("Enter new Course Credit:");
                int newCredit = Integer.parseInt(scanner.nextLine());
                course.setCredits(newCredit);
            } else if (choice.equals("4")) {
                System.out.println("Enter new Course Content:");
                String newContent = scanner.nextLine();
                course.setCourseContent(newContent);
            } else if (choice.equals("5")) {
                System.out.println("Enter new Course Materials:");
                String newMaterials = scanner.nextLine();
                course.setCourseMaterials(newMaterials);
            } else if (choice.equals("6")) {
                break;
            } else {
                System.out.println("Invalid choice");
                sleep(1000);
                continue;
            }

            courseRepository.saveCourse(course);
            sleep(1000);
            System.out.println("Course Update Successful!");

            Course courseSaved = courseRepository.findCourseById(courseID);
            System.out.println(courseSaved.toString());
        }
    }
}

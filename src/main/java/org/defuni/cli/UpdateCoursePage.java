package org.defuni.cli;

import org.defuni.account.Lecturer;
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
        clearScreen();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Course Updating:");
            System.out.println("Enter Course ID:");
            String courseID = scanner.nextLine();

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
            System.out.println("6. Update course components grades:");
            System.out.println("7. Exit: ");
            System.out.println("\n______________________________________________________\n");
            System.out.println("Enter choice:");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter new Course ID:");
                    String newCourseID = scanner.nextLine();
                    course.setCourseID(newCourseID);
                    break;
                case 2:
                    System.out.println("Enter new Course Title:");
                    String newTitle = scanner.nextLine();
                    course.setCourseTitle(newTitle);
                    break;
                case 3:
                    System.out.println("Enter new Course Credit:");
                    int newCredit = scanner.nextInt();
                    course.setCredits(newCredit);
                    break;
                case 4:
                    System.out.println("Enter new Course Content:");
                    String newContent = scanner.nextLine();
                    course.setCourseContent(newContent);
                    break;
                case 5:
                    System.out.println("Enter new Course Materials:");
                    String newMaterials = scanner.nextLine();
                    course.setCourseMaterials(newMaterials);
                    break;
                case 6:
                    System.out.println("Enter number of components:");
                    int num = scanner.nextInt();
                    List<String> components = new ArrayList<>();
                    for (int i = 0; i < num; i++) {
                        System.out.println("Enter component " + i + " grade:");
                        components.add(scanner.nextLine());
                    }
                    course.setComponentGrades(components);
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Invalid choice");
            }

            // if (choice == 1) {
            // System.out.println("Enter new Course ID:");
            // String newCourseID = scanner.nextLine();
            // course.setCourseID(newCourseID);
            // }

            // else if (choice == 2) {
            // System.out.println("Enter new Course Title:");
            // String newTitle = scanner.nextLine();
            // course.setCourseTitle(newTitle);
            // }

            courseRepository.saveCourse(course);
            sleep(1000);
            System.out.println("Course Update Successful!");

            Course courseSaved = courseRepository.findCourseById(courseID);
            System.out.println(courseSaved.toString());
        }
    }
}

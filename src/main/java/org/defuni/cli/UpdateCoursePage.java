package org.defuni.cli;

import org.defuni.account.Lecturer;
import org.defuni.course.Course;
import org.defuni.repository.CourseRepositoryFirebase;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.defuni.Main.clearScreen;

public class UpdateCoursePage {
    public UpdateCoursePage(Lecturer lecturer){
        clearScreen();
        Scanner scanner = new Scanner(System.in);

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
        System.out.println("5. Update course state");
        System.out.println("6. Update course department");
        System.out.println("7. Update course materials");
        System.out.println("8. Update course components grades:");

        System.out.println("\n______________________________________________________\n");
        System.out.println("Enter choice:");
        int choice = scanner.nextInt();

        if (choice == 1){

        } else if (choice == 2){

        } else if (choice == 3){

        } else if (choice == 4){

        } else if (choice == 5){

        } else if (choice == 6){

        } else if (choice == 7){

        } else if (choice == 8){

            System.out.println("Enter number of components:");
            int num = scanner.nextInt();
            List<Double> components = new ArrayList<>();
            for (int i = 0; i < num; i++){
                System.out.println("Enter component " + i + " grade:");
                components.add(scanner.nextDouble());
            }
            course.setComponentGrades(components);

        } else {
            System.out.println("Invalid choice");
        }

        System.out.println("Course Update Successful!");
        courseRepository.saveCourse(course);

        Course  courseSaved = courseRepository.findCourseById(courseID);
        System.out.println(courseSaved.toString());





    }
}

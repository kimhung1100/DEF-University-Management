package org.defuni.cli;

import org.defuni.account.EducationManager;
import org.defuni.account.Lecturer;
import org.defuni.account.Manager;
import org.defuni.course.Course;
import org.defuni.course.ScheduledClass;
import org.defuni.repository.CourseRepositoryFirebase;
import org.defuni.repository.ScheduledClassRepositoryFirebase;

import java.util.List;
import java.util.Scanner;

public class ManagerPage {
    EducationManager Emanager;

    public ManagerPage(EducationManager Emanager) {
        this.Emanager = Emanager;
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void run() {
        clearScreen();
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        while (true) {
            System.out.println("Education Manager Page");
            System.out.println("0. EXIT");
            System.out.println("1. Create a course");
            System.out.println("2. Create Classes from Course registers");
            System.out.println("Enter choice:");
            String choice = scanner.nextLine();

            if (choice.equals("0")) {
                return;
            }

            else if (choice.equals("1")) {
                CourseCreatePage courseCreatePage = new CourseCreatePage(this.Emanager);
            }

            else if (choice.equals("2")) {
                System.out.println("Available courses:");
                Manager.displayDocs("course");

                System.out.println("Enter Course ID:");

                String courseID = scanner.nextLine();

                Boolean isExisted = Manager.findDocument("course", courseID) == null ? false : true;

                while (courseID.trim().isEmpty() || !isExisted) {
                    if (!isExisted) {
                        System.out.println("Course ID does not exist. Please enter again:");
                    } else {
                        System.out.println("Course ID cannot be empty. Please enter again:");
                    }
                    courseID = scanner.nextLine();
                    isExisted = Manager.findDocument("course", courseID) == null ? false : true;
                }

                CourseRepositoryFirebase courseRepository = new CourseRepositoryFirebase();
                Course course = courseRepository.findCourseById(courseID);
                System.out.println("Students registed for the course:");
                System.out.println(course.getStudentRegisters());

                System.out.println("Enter number of students per class:");

                int num = scanner1.nextInt();

                List<ScheduledClass> scheduledClassList = course.createScheduledClass(num);

                ScheduledClassRepositoryFirebase scheduledClassRepositoryFirebase = new ScheduledClassRepositoryFirebase();
                for (ScheduledClass scheduledClass : scheduledClassList) {
                    scheduledClassRepositoryFirebase.createScheduledClass(scheduledClass);

                    String message = "You have been added to class " + scheduledClass.getSemester()
                            + scheduledClass.getCourseID() + scheduledClass.getClassID() + " of course: "
                            + scheduledClass.getCourseID()
                            + " - " + scheduledClass.getCourseName();
                    scheduledClass.notifyStudents(message);
                }

                courseRepository.saveCourse(course);

                // Document: HKx + courseID + classID (Lxx)
                // for (ScheduledClass scheduledClass : scheduledClassList) {
                // ScheduledClass scheduledClassRet =
                // scheduledClassRepositoryFirebase.findScheduledClassByID(
                // scheduledClass.getSemester() + scheduledClass.getCourseID() +
                // scheduledClass.getClassID());
                // System.out.println(scheduledClassRet.getClassID());
                // }

                System.out.println("Classes created successfully!");
                sleep(1500);
            }

            else {
                System.out.println("Invalid choice");
            }
            clearScreen();
        }

        // scanner.close();
    }

    public void sleep(int milisec) {
        try { // Sleep 1000ms before clear screen
            Thread.sleep(milisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

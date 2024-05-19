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
        System.out.println("Education Manager Page");
        Scanner scanner = new Scanner(System.in);
        // chức năng tạo course của Manager đang bị lỗi, có thể tạo nhưng lỗi vói
        // database...
        System.out.println("1. Create a course");
        System.out.println("2. Create Classes from Course registers");
        System.out.println("Enter choice:");
        int choice = scanner.nextInt();

        if (choice == 1) {
            // chức năng tạo course của Manager đang bị lỗi, có thể tạo nhưng lỗi với
            // database...
            CourseCreatePage courseCreatePage = new CourseCreatePage(this.Emanager);
        }

        else if (choice == 2) {
            System.out.println("Available courses:");
            Manager.displayDocs("course");

            System.out.println("Enter course ID:");
            Scanner scanner1 = new Scanner(System.in);
            String courseID = scanner1.nextLine();

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
                scheduledClass.notifyStudents("You have been added to class " + scheduledClass.getClassID());
            }

            courseRepository.saveCourse(course);

            // Document: HKx + courseID + classID (Lxx)
            for (ScheduledClass scheduledClass : scheduledClassList) {
                ScheduledClass scheduledClassRet = scheduledClassRepositoryFirebase.findScheduledClassByID(
                        scheduledClass.getSemester() + scheduledClass.getCourseID() + scheduledClass.getClassID());
                System.out.println(scheduledClassRet);
            }
        }

        else if (choice == 3) {

        }
    }
}

package org.defuni.cli;

import org.defuni.course.Course;
import org.defuni.course.ScheduledClass;
import org.defuni.repository.CourseRepositoryFirebase;
import org.defuni.repository.ScheduledClassRepositoryFirebase;

import java.util.List;
import java.util.Scanner;

public class ManagerPage {
    public void ManagerPage() {

    }
    public void run(){
        System.out.println("Manager Page");
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Create Classes from Course registers");

        System.out.println("Enter choice:");
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println("Enter course ID:");
            Scanner scanner1 = new Scanner(System.in);
            String courseID = scanner1.nextLine();

            CourseRepositoryFirebase courseRepository = new CourseRepositoryFirebase();
            Course course = courseRepository.findCourseById(courseID);
            System.out.println(course.getStudentRegisters());

            System.out.println("Enter number of students per class:");
            int num = scanner1.nextInt();
            List<ScheduledClass> scheduledClassList = course.createScheduledClass(num);

            ScheduledClassRepositoryFirebase scheduledClassRepositoryFirebase = new ScheduledClassRepositoryFirebase();
            for (ScheduledClass scheduledClass : scheduledClassList) {
                scheduledClassRepositoryFirebase.createScheduledClass(scheduledClass);
            }

            courseRepository.saveCourse(course);

            for (ScheduledClass scheduledClass : scheduledClassList) {
                ScheduledClass scheduledClassRet = scheduledClassRepositoryFirebase.findScheduledClassByID(scheduledClass.getSemester() + scheduledClass.getCourseID() + scheduledClass.getClassID());
                System.out.println(scheduledClassRet);
            }
        }
    }
}

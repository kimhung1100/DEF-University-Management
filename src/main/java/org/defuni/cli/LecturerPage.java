package org.defuni.cli;

import org.defuni.cli.*;
import java.util.Scanner;

import org.defuni.account.*;
import com.google.cloud.firestore.*;
import java.util.*;

import org.defuni.course.ScheduledClass;
import org.defuni.repository.ScheduledClassRepositoryFirebase;

public class LecturerPage {
    Lecturer lecturer;

    public LecturerPage(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void run() {
        clearScreen();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Lecture page:");
            System.out.println("0. EXIT");
            System.out.println("1. Display info");
            System.out.println("2. Update info");
            System.out.println("3. Create a course");
            System.out.println("4. Update Course");
            System.out.println("5. Update student's grade (abandoned)");
            System.out.println("6. Update Class (abandoned)");
            System.out.println("7. Display Class");
            System.out.println("999. Log out");

            String choice = scanner.nextLine();

            if (choice.equals("0")) {
                return;
            }

            else if (choice.equals("1")) {
                lecturer.displayInfo();
            }

            else if (choice.equals("2")) {
                lecturer.updateProfile();
            }

            else if (choice.equals("3")) {
                CourseCreatePage courseCreatePage = new CourseCreatePage(lecturer);
            }

            else if (choice.equals("4")) {
                UpdateCoursePage updateCoursePage = new UpdateCoursePage(lecturer);
            } else if (choice.equals("5")) {
                // Abandoned
                // UpdateStudentGradePage updateStudentGradePage = new UpdateStudentGradePage();

            } else if (choice.equals("6")) {
                // tạo 1 clone mới, chưa tìm ra cách xóa cái cũ....
                // UpdateScheduledClass UpdateScheduledClass = new
                // UpdateScheduledClass(lecturer);
            } else if (choice.equals("7")) {
                DisplayScheduledClassPage();
            }

            else if (choice.equals("999")) {
                return;
            }

            else {
                System.out.println("Invalid choice");
            }
            clearScreen();
        }

        // scanner.close();
    }

    public void DisplayScheduledClassPage() {
        clearScreen();
        Scanner scanner = new Scanner(System.in);

        Manager.displayDocs("scheduledClass");
        System.out.println("Enter Class ID:");
        String schelleID = scanner.nextLine();

        ScheduledClassRepositoryFirebase scheRepository = new ScheduledClassRepositoryFirebase();
        ScheduledClass sClasss = scheRepository.findScheduledClassByID(schelleID);
        System.out.println(sClasss); // ?
        // sClasss.printList();
        while (true) {
            clearScreen();
            // System.out.println("______________________________________________________");
            System.out.println("What do you want to see?");
            System.out.println("1. Class Score ");
            System.out.println("2. Student Score ");
            System.out.println("3. Class Info");
            System.out.println("7. Exit: ");
            // System.out.println("______________________________________________________");
            System.out.println("Enter choice:");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                // sClasss.printList();
                printSocre(sClasss);
            } else if (choice.equals("2")) {
                printScoreStudent(sClasss);
            } else if (choice.equals("3")) {
                printInfoClass(sClasss);
            } else if (choice.equals("7")) {
                return;
            } else {
                System.out.println("Invalid choice");
            }

        }

    }

    void printSocre(ScheduledClass sClasss) {
        sClasss.printList();
        System.out.println("*Enter to continue:");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        // scanner.close();
        clearScreen();
        // Lsleep(1500);
    }

    void printScoreStudent(ScheduledClass sClasss) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter StudentID:");
        String StudentID = scanner.nextLine();
        sClasss.printScoreStudent(StudentID);
        System.out.println("*Enter to continue:");

        scanner.nextLine();
        // scanner.close();
        clearScreen();
    }

    void printInfoClass(ScheduledClass sClasss) {
        System.out.println("Class time: " + sClasss.getTime());
        System.out.println("Class room: " + sClasss.getRoom());
        Scanner scanner = new Scanner(System.in);
        System.out.println("*Enter to continue:");

        scanner.nextLine();
        // scanner.close();
        clearScreen();
    }

    public void sleep(int milisec) {
        try { // Sleep 1000ms before clear screen
            Thread.sleep(milisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

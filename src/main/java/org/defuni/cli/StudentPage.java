package org.defuni.cli;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import org.defuni.account.Student;
import org.defuni.account.UserAccount;
import org.defuni.cli.*;
import org.defuni.course.ScheduledClass;

import com.google.api.services.storage.model.Notification;

public class StudentPage {
    UserAccount student;

    public StudentPage(UserAccount student) {
        this.student = student;
    }

    // public StudentPage(Student student) {
    // this.student = student;
    // }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void sleep(int milisec) {
        try {
            Thread.sleep(milisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        clearScreen();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            clearScreen();

            System.out.println("Student page:");
            System.out.println("0. EXIT");
            System.out.println("1. Display info");
            System.out.println("2. Update info");
            System.out.println("3. Register course");
            System.out.println("4. Cancel course");
            System.out.println("5. Schedule");
            System.out.println("6. Transcript");
            System.out.println("7. Mail box");
            System.out.println("999. Log out");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();

                if (choice == 0) {
                    return; // Exit the student page
                } else if (choice == 1) {
                    student.displayInfo();
                } else if (choice == 2) {
                    student.updateProfile();
                } else if (choice == 3) {
                    CourseRegistingPage courseRegistingPage = new CourseRegistingPage(student);
                } else if (choice == 4) {
                    CourseRemovingPage courseRemovePage = new CourseRemovingPage(student);
                } else if (choice == 5) {
                    student.displaySchedule();
                } else if (choice == 6) {
                    student.displayTranscript();
                } else if (choice == 7) {
                    MailBoxPage mailBoxPage = new MailBoxPage(student);
                } else if (choice == 999) {
                    return; // Log out
                } else {
                    System.out.println("Invalid option. Please try again.");
                    sleep(1500);
                    continue;
                }
            } else {
                System.out.println("Invalid input");
                scanner.nextLine();
                sleep(1500);
                continue;
            }

        }

    }

}

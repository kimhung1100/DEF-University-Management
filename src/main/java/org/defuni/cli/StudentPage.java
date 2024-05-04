package org.defuni.cli;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import org.defuni.account.Student;
import org.defuni.account.UserAccount;
import org.defuni.cli.*;

public class StudentPage {
    UserAccount student;

    public StudentPage(UserAccount student) {
        this.student = student;
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void run() {
        clearScreen();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Student page:");
            System.out.println("0. EXIT");
            System.out.println("1. Display info");
            System.out.println("2. Update info");
            System.out.println("3. Register course");
            System.out.println("4. Cancel course");
            System.out.println("5. Schedule");
            System.out.println("6. Transcript");
            System.out.println("999. Log out");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 0:
                    return; // Exit the student page
                case 1:
                    student.displayInfo();
                    break;
                case 2:
                    student.updateProfile();
                    break;
                case 3:
                    CourseRegistingPage courseRegistingPage = new CourseRegistingPage(student);
                    //courseRegistingPage.run();
                    break;
//                case 4:
//                    student.removeClass();
//                    break;
//                case 5:
//                    student.displaySchedule();
//                    break;
//                case 6:
//                    student.displayTranscript();
//                    break;
                case 999:
                    return; // Log out

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
            clearScreen();
        }

    }

}

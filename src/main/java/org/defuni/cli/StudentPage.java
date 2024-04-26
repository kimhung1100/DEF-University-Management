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
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Student page:");
            System.out.println("0. EXIT");
            System.out.println("1. Display info");
            System.out.println("2. Update info");
            System.out.println("3. Register course");
            System.out.println("4. Cancel course");
            System.out.println("5. ");
            System.out.println("6. ");
            System.out.println("7. ");
            System.out.println("999. Log out");

            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 0) {
                break;
            }

            else if (choice == 1) {
                student.displayInfo();
            }

            else if (choice == 2) {
                student.updateProfile();
            }

            else if (choice == 3) {
                CourseRegistingPage courseRegistingPage = new CourseRegistingPage(student);
            }
            //
            // else if (choice == 4) {
            // student.removeClass();
            // }
            else if (choice == 5) {
                // student.displayGrades();
                // }
                // else if (choice == 6) {
                // student.();
                // }
                // else if (choice == 7) {
                // student.();
                // }
            } else if (choice == 999) {
                return;
            }

            else {
                System.out.println("Uwwooooooh ToT");
            }

            clearScreen();
        }
        scanner.close();
    }

}

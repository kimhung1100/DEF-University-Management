package org.defuni.cli;

import org.defuni.account.EducationManager;
import org.defuni.account.Lecturer;
import org.defuni.account.Student;

import java.util.Scanner;

public class LoginPage {
    public LoginPage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("DEF University Management System");
        System.out.println("Log in using your account on:");
        System.out.println("1. Student");
        System.out.println("2. Lecturer");
        System.out.println("3. Education Manager");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        while (true) {
            clearScreen();

            System.out.println("LOGIN PAGE");
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            switch (choice) {
                case 1:
                    Student student = new Student();
                    student.login(username, password);
                    break;
                case 2:
                    Lecturer lecturer = new Lecturer();
                    // if (lecturer.login(username, password) != null) { //hình như chưa đồng bộ đc
                    // bên ggfb?
                    // LecturerPage lecturerPage = new LecturerPage(lecturer);
                    // lecturerPage.run();
                    // }

                    LecturerPage lecturerPage = new LecturerPage(lecturer);
                    lecturerPage.run();
                    break;
                case 3:
                    EducationManager manager = new EducationManager();
                    manager.login(username, password);
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }

        }

    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

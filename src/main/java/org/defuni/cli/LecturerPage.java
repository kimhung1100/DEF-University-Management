package org.defuni.cli;

import java.util.Scanner;

import org.defuni.account.*;
import com.google.cloud.firestore.*;
import java.util.*;

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
            System.out.println("4. Regist class");
            System.out.println("111. Test ground!");
            System.out.println("999. Log out");

            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 0) {
                break;
            }

            else if (choice == 1) {
                lecturer.displayInfo();
            }

            else if (choice == 2) {
                lecturer.updateProfile();
            }

            else if (choice == 3) {
                CourseCreatePage courseCreatePage = new CourseCreatePage(lecturer);
            }

            else if (choice == 4) {

            }

            else if (choice == 111) {
                Manager manager = Manager.getInstance();
                Firestore firebase = manager.connect();

                Map<String, Object> studentInfo = manager.findDocument("students", "testUser");
                Student stu = manager.convStudent(studentInfo);

                // do sth to that student ToT

            }

            else if (choice == 999) {
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

package org.defuni.cli;

import java.util.Scanner;

import org.defuni.account.Lecturer;

public class LecturerPage {
    Lecturer lecturer;

    public LecturerPage(Lecturer lecturer) {
        this.lecturer = lecturer;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Lecture page:");
            System.out.println("0. EXIT");
            System.out.println("1. Display info");
            System.out.println("2. Update info");
            System.out.println("3. Create a course");

            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 0) {
                break;
            }

            else if (choice == 1) {
                System.out.println("Yea");
                lecturer.displayInfo();
            }

            else if (choice == 2) {
                lecturer.updateProfile();
            }

            else if (choice == 3) {
                lecturer.courseCreate();
            }

            else {
                System.out.println("Uwwooooooh ToT");
            }

        }

        scanner.close();
    }

}

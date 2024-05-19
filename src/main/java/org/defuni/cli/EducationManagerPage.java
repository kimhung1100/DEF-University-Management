package org.defuni.cli;

import java.util.Scanner;

import org.defuni.account.UserAccount;

public class EducationManagerPage {
    public EducationManagerPage() {
    }

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

            System.out.println("Comfirm creating classes for available courses?");
            System.out.println("0. EXIT");
            System.out.println("1. Confirm");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();

                if (choice == 0) {
                    return; // Exit the student page
                } else if (choice == 1) {
                    // Confirm creating classes for available courses
                    System.out.println("Creating classes for available courses...");
                    sleep(1500);
                    System.out.println("Classes created successfully!");
                    sleep(1500);
                    return;
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

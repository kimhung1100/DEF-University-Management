package org.defuni.cli;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.WriteResult;
import org.defuni.account.Manager;
import org.defuni.account.UserAccount;

public class CourseRemovingPage {
    UserAccount student;

    public CourseRemovingPage(UserAccount student) {
        this.student = student;
        this.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        Manager manager = Manager.getInstance();

        while (true) {
            clearScreen();
            System.out.println("Course Remove Page");

            Manager.displayDocs("course");

            System.out.println("Enter courseID exactly or EXIT to cancel:");
            String courseID = scanner.nextLine();

            if (courseID.equalsIgnoreCase("exit")) {
                break;
            }
            if (courseID.isEmpty()) {
                System.out.println("Invalid input");
                sleep(1500);
                continue;
            }

            Map<String, Object> documentData = manager.findDocument("course", courseID);
            if (documentData != null) {
                System.out.println("Document found:");
                // Iterate over map entries and print key-value pairs
                for (Map.Entry<String, Object> entry : documentData.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
            } else {
                System.out.println("Document not found.");
                sleep(1500);
                continue;
            }

            System.out.println("\n");
            System.out.println("1. Confirm remove course");
            System.out.println("0. Cancel remove course");

            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                if (input == 1) {
                    // Content here
                    List<String> studentIds = (List<String>) documentData.get("studentRegisters");

                    if (studentIds.contains(student.getUserName())) { // Student is in this course
                        studentIds.remove(student.getUserName());
                        manager.updateDocument("course", (String) documentData.get("courseID"), "studentRegisters",
                                studentIds);

                        System.out.println("You have been removed from the course.");
                        sleep(1500);

                    } else { // Student is not in this course
                        System.out.println("You are not registed this course.");
                        sleep(1500);
                        break;
                    }

                } else if (input == 0) {
                    System.out.println("Course registration cancelled.");
                    sleep(1500);
                    scanner.nextLine();

                    continue;
                } else {
                    System.out.println("invalid input");
                    scanner.nextLine();
                    sleep(1500);
                    continue;
                }
            } else {
                System.out.println("Invalid input");
                scanner.nextLine();
                sleep(1500);
                continue;
            }

            // System.out.println("Course Remove End.");
            scanner.nextLine(); // Clear out all the string input previously
            break;
        }

    }

    private void sleep(int milisec) {
        try {
            Thread.sleep(milisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
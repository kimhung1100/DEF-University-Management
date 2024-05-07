package org.defuni.cli;

import static org.defuni.Main.clearScreen;

import java.util.List;
import java.util.Map;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.WriteResult;
import org.defuni.account.Manager;
import org.defuni.account.Student;
import org.defuni.account.UserAccount;
import org.defuni.course.Course;
import org.defuni.cli.*;

public class CourseRegistingPage {
    private UserAccount student;

    public CourseRegistingPage(UserAccount student) {
        this.student = student;
        this.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        Manager manager = Manager.getInstance();

        while (true) {
            clearScreen();
            System.out.println("Course Registration Page");

            // Pop up available Courses

            System.out.println("\nEnter courseID exactly:");
            String courseID = scanner.nextLine();

            Map<String, Object> documentData = Manager.findDocument("course", courseID);
            if (documentData != null) {
                System.out.println(String.format("Course %s found: ", documentData.get("courseID")));
                // Iterate over map entries and print key-value pairs
                for (Map.Entry<String, Object> entry : documentData.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
            }

            else {
                System.out.println("Document not found.");
                sleep(1500);
                continue;
            }

            System.out.println("\n");
            System.out.println("1. Confirm register course");
            System.out.println("0. Cancel register course");

            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                if (input == 1) {
                    // Content here
                    List<String> studentIds = (List<String>) documentData.get("studentRegisters");
                    studentIds.add(student.getUserName());
                    manager.updateDocument("course",
                            (String) documentData.get("courseID"),
                            "studentRegisters",
                            studentIds);

                    System.out.println("You have been added to course: " + (String) documentData.get("courseID"));
                    sleep(1500);

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

            // System.out.println("Course Registration End.");
            scanner.nextLine(); // Clear out all the string input previously
            break;
        }
    }

    public void sleep(int milisec) {
        try { // Sleep 1000ms before clear screen
            Thread.sleep(milisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

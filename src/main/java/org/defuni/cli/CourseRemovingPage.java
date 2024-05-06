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
    public CourseRemovingPage(UserAccount student) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Course Remove Page");
        System.out.println("Enter courseID exactly:");
        String courseID = scanner.nextLine();

        Manager manager = Manager.getInstance();
        Map<String, Object> documentData = manager.findDocument("course", courseID);
        if (documentData != null) {
            System.out.println("Document found:");
            // Iterate over map entries and print key-value pairs
            for (Map.Entry<String, Object> entry : documentData.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } else {
            System.out.println("Document not found.");
            return; // Exit if no document is found
        }

        System.out.println("1.Confirm remove course");
        System.out.println("0. Cancel remove course");
        int choice = scanner.nextInt();
        if (choice == 1) {
            List<String> studentIds = (List<String>) documentData.get("studentRegister");
            studentIds.remove(student.getUserName());
            manager.updateDocument("course", (String) documentData.get("courseID"), "studentRegister", studentIds);
            System.out.println("You have been removed from the course.");
        }
        System.out.println("Course Remove End.");
    }
}
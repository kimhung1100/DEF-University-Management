package org.defuni.cli;
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
    public CourseRegistingPage(UserAccount student) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Course Registration Page");
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
        }

        System.out.println("1.Confirm register course");
        System.out.println("0. Cancel register course");
        int choice = scanner.nextInt();
        if (choice == 1) {
//            Course course = new Course();
//            course.fromMap(documentData);
            List<String> studentIds = (List<String>) documentData.get("studentRegister");
            studentIds.add(student.getUserName());
            manager.updateDocument("course", (String) documentData.get("courseID"), "studentRegister", studentIds);
        }
        System.out.println("Course Registration End.");

    }
}
